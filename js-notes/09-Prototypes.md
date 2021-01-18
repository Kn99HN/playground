# Prototypes

```
let pizza = {};
console.log(pizza.tast); //pineapple
```

We created an empty object with {}. We definitely didn't set any properties
on it before logging. So It seems like `pizza.taste` can point to "pineapple".
Usually, we would get `undefined`.

## Prototypes

```
let human = {
  teeth: 32
};

let gwen = {
  age: 19
};
```

`gwen` points at an object without a `teeth` property. According to the rules,
if we read it, we get `undefined`:

```
console.log(gwen.teeth); // undefined
```

We can ALSO instruct JavaScript to *continue searching* for the missing property
on another object.

```
let human = {
  teeth: 32
}

let gwen = {
  __proto__: human,
  age: 19
}
```

Any JavaScript object may choose another object as a prototype. By specifying
`__proto__`, we instruct JavaScript to continue looking for missing properties 
on that object instead.

## Prototypes in Action

```
console.log(human.age); // undefined
console.log(gwen.age); // 19

console.log(human.teeth); // 32
console.log(gwen.teeth); // 32

console.log(human.tail); // undefined
console.log(gwen.tail); // undefined
```

## The Prototype Chain 
A prototype is more like a `relationship`. An object may point at another object
as its prototype.

```
let mammal = {
  brainy: true,
};

let human = {
  __proto__: mammal,
  teeth: 32
}

let gwen = {
  __proto__: human,
  age: 19,
}

console.log(gwen.brainy) // true
```

JavaScript will search for the property on the object, then on its prototype, 
then on that object's prototype and so on. We would only get undefined if we
ran out of prototypes and still haven't found our property.

The sequence of objects to "visit" is known as our object's `prototype chain`.
However, the chain can't be circular.

## Shadowing

```
let human = {
  teeth: 32
};

let gwen = {
  __proto__: human,
  teeth: 31
}

console.log(human.teeth); // 32
console.log(gwen.teeth); // 31
```

Once we find our property, **we stop the search**

`hasOwnProperty` is a built-in function that returns `true` for "own" properties,
and does not look at the prototypes.


### Assignment

```
let human = {
  teeth: 32,
};

let gwen = {
  __proto__: human
}

gwen.teeth = 31;
```

## The Object Prototype

```
let obj = {};
```

The empty object has a hidden __proto__ wire that points at the Object Prototype
by default. This explains why the JavaScript objects seem to have "built-in"
properties:

```
let human = {
  teeth: 32,
};

console.log(human.hasOwnProperty); // (function)
console.log(human.toString); // (function)
```

These "built-in" properties are nothing more than normal properties that exists
on the Object Prototype. Our object's prototype is the Object Prototype. 

## An Object with No Prototype
```
let weirdo = {
  __proto__: null
}
```

This will produce an object that doesn't have a prototype, at all.

## Polluting the Prototype
```
let obj = {};
obj.__proto__.smell = 'banana';
```

We mutated the Object Prototype by adding a `smell` property to it. By mutating
a shared prototype, we just did something called `prototype pollution`. 

## __proto__ vs prototype
`prototype` property is unrelated to the core idea of prototypes. It's more
related to the `new` operator.

Remember that **__proto__** means an object's prototype. `__proto__` is often
used in place of class inheritance due to the lack of the feature in JavaScript

