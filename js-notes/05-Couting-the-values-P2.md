# Couting the Values (Part 2)

## BigInts
They are recently added. Regular numbers can't represent large integers with precision, so BigInts fill the gap.

```
let alot = 9007199254740991n; // Notice n at the end
console.log(alot + 1n); // 9007199254740992n
console.log(alot + 2n); // 9007199254740993n
console.log(alot + 3n); // 9007199254740994n
console.log(alot + 4n); // 9007199254740995n
console.log(alot + 5n); // 9007199254740996n
```

This is great for financial calculations where precision is important. However, the operations are with huge numbers which take time and resources. **There's an infinite number of BigInts**. 

## Strings
Strings represent text in JavaScript. There are 3 ways to write strings (single quotes, double quotes, and backticks). The result is the same.

```
console.log(typeof("こんにちは")); // "string"
console.log(typeof('こんにちは')); // "string"
console.log(typeof(`こんにちは`)); // "string"
```

Strings aren't objects. All strings have a few built-in properties.

```
let cat = 'Cheshire';
console.log(cat.length); //8
console.log(cat[0]); //C
console.log(cat[1]); //h
```

String properties are special and don't behave the way object properties do. Strings are primitives, and all primitives are immutable.

## Objects
This includes arrays, dates, RegExps, and other non-primitive values:


```
console.log(typeof({})); // "object"
console.log(typeof([])); // "object"
console.log(typeof(new Date())); // "object"
console.log(typeof(/\d+/)); // "object"
console.log(typeof(Math)); // "object"
```

objects are not primitive values. They're mutable. We can access their properties with . or [];

```
let rapper = { name: "Malicious" };
rapper.name = 'Malice';
rapper['name'] = 'No Malice'
```

We can always make more object. Every time we use **{}** object literal, we *create* a brand new object value:

```
let shrek = {};
let donkey= {}; 
=> 2 distinct objects
```

JavaScript is a garbage-collected language. Objects will eventually disappear if there's no variables pointing to them. JavaScript, however, doesn't offer guarantees about *when* garbage collection happens.

## Functions
### Functions are values

We define functions so that we can call them later and run code inside them. We'll think about functions as another kind of value: a number, an object and a *function*.

```
for(let i = 0; i < 7; i++) {
    console.log(2);
}
```

There is only 1 value pass to *console.log* because 2 is primitive, it doesn't change.

```
for(let i = 0; i < 7; i++) {
    console.log({});
}
```

`{}` is an object literal  so we are creating 7 distinct object values.

```
for(let i = 0; i < 7; i++) {
    console.log(function() {});
}
```

Everytime, we are creating 7 distinct functions in the loop. We can think of functions as a special kind of object.

