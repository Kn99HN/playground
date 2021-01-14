# Values and Variables

```
let reaction = 'yikes';
reaction[0] = 'l';
console.log(reaction);

reaction is assigned a string => 'yikes', which is an array of character.
We replace the first char with '1'
So the result is 'likes'
```

=> My understanding was wrong. The correct answer is "yikes"

# Primitive Values are Immutable
Primitive Values cannot be changed.

```
let arr = [212, 8, 506];
let str = 'hello';
```

String is a primitive values and an array is an object. First array item can be accessed, similar to string's first character. But strings ARE NOT arrays.

We can change array's first item. However string is a primitive value. It is immutable and read-only. JavaScript won't let you change a property of a primitive value. It will silently refuse your request or error depends on 'strict mode' running.

```
let pet = 'Narwhal';
pet = 'The Kraken';
console.log(pet) // 'The Kraken';
```

# Variables are wires
Primitive values can't change BUT variables can.

Variables are not values. Variables point to values. A variable is a wire. It has 2 ends and a direction: it starts from a name in the code and ends pointing at some value in the universe.

# Reading a value of a variable
It's not the variable that we usually pass to `console.log`. We can't pass variables to functions. We pass current value of pet variable.

When we write a variable name, we're asking JavaScript a question: "What is the current value of pet?"

