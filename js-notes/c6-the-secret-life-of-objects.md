# The Secret Life of Objects

## Encapsulation
The core idea in OOP is to divide programs into smaller pieces and make each 
piece responsible for managing its own state.

The way a piece of program works can be kept local to that piece. Whenever these
local details change, only the code directly around it needs to be updated.

Different pieces of such a program interact with each other through interfaces, 
limited sets of functions or bindings that provide useful functionality at a more
abstract level, hiding their precise implementation.

Such program pieces are modeled using objects. Their interface consists of a 
specific set of methods and properties. Properties that are part of the interface
are called public. The others, which outside code should not be touching, are 
called private.

JS doesn't have support for the distinction between *public* and *private*. 
However, JS programmers are successfully using this idea. Typically, the 
available interface is described in the documentation or comments. It's also
common to put an underscore character at the start of property names to indicate
that those properties are private. 

## Methods
Methods are nothing more than properties that hold function values.

```
let rabbit = {};
rabbit.speak = function(line) {
  console.log(`The rabbit says '${line}'`);
};

rabbit.speak("I'm alive.");
```

Usually a method needs to do something with the object it was called on. When 
a function is called as a method - looked up as a property and immediately called,
as in `object.method()` - the binding called this in its body automatically points
at the object that it was called on

```
function speak(line) {
  console.log(`The ${this.type} rabbit says ${line}`);
}
let whiteRabbit = {type: "white", speak};
let hungryRabbit = {type: "hungry", speak};

whiteRabbit.speak("Oh my ears and whiskers, " +
                  "how late it's getting!");
// → The white rabbit says 'Oh my ears and whiskers, how
//   late it's getting!'
hungryRabbit.speak("I could use a carrot right now.");
// → The hungry rabbit says 'I could use a carrot right now.'
```

You can think of this as an extra param that is passed in a different way. If 
you want to pass it explicitly, you can use a function's `call` method, which 
takes `this` value as its first arg and treats further args as normal param.

```
speak.call(hungryRabbit, "Burp!");
```

Each function then has its own `this` binding, whose value depends on the way 
it's called, you cannot refer to `this` of the wrapping scope in a regular
function defined with the `function` keyword.

Arrow functions are different - they don't bind their own `this` but can see `this`
binding of the scope around them. Thus, you can do something lik the following 
code, which references `this` from inside a local function:

```
function normalize() {
  console.log(this.coords.map(n => n / this.length));
}
normalize.call({coords: [0,2,3], length:5};
// -> [0, 0.4, 0.6]
```

## Prototypes
```
let empty = {}
console.log(empty.toString);
// -> Function
console.log(empty.toString());
// -> [object Object]
```

In addtion to their set of properties, most objects also have a `prototype`. A 
prototype is another object that's used as a fallback source of properties. When
an object gets a request for a property that it doesn't have, its prototype will
be searched for the property, then the prototype's prototype and so on.

The entity behind all objects is `Object.prototype`. The prototype relations of
JS objects form a tree-shaped structure and at the root of this structure sits
`Object.prototype`. It provides a few methods that show up in all objects, such
as `toString`, which converts an object to a string representation.

Many objects don't directly have `Object.prototype` such as functions derive from
`Function.prototype`, and arrays derive from `Array.prototype`. 

```
console.log(Object.getPrototypeOf(Math.max) == Function.prototype);
// -> true
console.log(Object.getPrototypeOf([]) == Array.prototype);
// -> true
```

We can use `Object.create` to create an object with a specific prototype.

```
let protoRabbit = {
  speak(line) {
    console.log(`The ${this.type} rabbit says '${line}'`
  }
};
let killerRabbit = Object.create(protoRabbit);
killerRabbit.type = "killer";
killerRabbit.speak("SKREEEEE!");
```

This creates a property called `speak` and gives it a function as its value.

## Classes
JS's prototype system can be interpreted as a somewhat informal take on an 
OOP concept `classes`. A class defines the shape of a type of object - what methods
and properties it has. Such an object is called an `instance` of the class.

We have to make an obj that derives from the proper prototype, but also have to make
sure it, itself, has the properties that `instances` of this class are supposed to have.

```
function makeRabbit(type) {
  let rabbit = Object.create(protoRabbit);
  rabbit.type = type;
  return rabbit;
}
```

If you put the keyword `new` in front of a function call, the function is treated
as a constructor. 

```
function Rabbit(type) {
  this.type = type;
}
Rabbit.prototype.speak = function(line) {
  console.log(`The ${this.type} rabbit says ${line}`);
}

let weirdRabbit = new Rabbit("weird");
```

Constructors (all functions, in fact) automatically get a property named `prototype`,
which holds a plain object that derives from `Object.prototype`.

## Polymorphism
When you call the `String` function on an object, it will call the `toString`
method on that object to create a meaningful string from it.

```
Rabbit.prototype.toString = function() {
  return `a ${this.type} rabbit`;
}
```

When code is written to work with objs that have certain interface, any kind of 
obj that happens to support this interface can be plugged into the code and it'll
just work.

`Polymorphism`. Polymorphic code can work with value of different shapes, as long 
as they support the interface it expects. A `for/of` loop can loop over several
kinds of ds. Such loops expect the data structure to expose a specific interface.

## Symbols
It's possible for multiple interfaces to use same property name for different things.
Symbols are values created with the `Symbol` function. Unlike strings, newly created
symbols are unique - you cannot create same symbol twice.

## The Iterator Interface
The obj given to a `for/of` loop is expected to be `iterable`. This means it has 
a method named with the `Symbol.iterator` symbol. 

When called, that method should return an object that provides a second interface,
`iterator`. It has a `next` method that returns the next result. That result should
be an object with a `value` property that provides the next value, if there's one, 
and a `done` property, which should be true when there are no more results and
`false` otherwise.
