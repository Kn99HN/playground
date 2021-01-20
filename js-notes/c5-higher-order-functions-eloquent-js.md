# Higher-Order Functions

## Abstracting Repetition
Plain functions are a good way to build abstractions. But sometimes they fall 
short. It's common to do something like this

```
for(let i = 0; i < 10; i++) {
  console.log(i);
}
```

It's also easy to write a function "doing something N times"

```
function repeatLog(n) {
  for(let i = 0; i < n; i++) {
    console.log(i);
  }
}
```

Since "doing something" can be represented as a function and functions are 
just values, we can pass our actions as function value.

```
function repeat(n, action) {
  for(let i = 0; i < n; i++) {
    action(i);
  }
}

repeat(3, console.log);
```

We also don't have to pass a predefined function to `repeat`. Often, it's easier
to create a function value on the spot instead.

```
let labels = [];
repeat(5, i => {
  labels.push(`Unit ${i+1}`);
});
console.log(labels);
```

## Higher-order functions
Functions that operate on other functions either by taking them as args or by
returning them, are called *higher-order functions*. Higher-order functions
allow us to abstract over *actions*, not just values.

```
function greaterThan(n) {
  return m => m > n;
}
let greaterThan10 = greaterThan(10);
console.log(greaterThan10(11));
```

We can also have:

```
function noisy(f) {
  return(...args) => {
    console.log("calling with", args);
    let result = f(...args);
    console.log("called with", args, ", returned", result);
    return result;
  }
}
noisy(Math.min)(3,2,1);
// calling with [3,2,1]
// called with [3,2,1], returned 1
```

We can write functions that provide new types of control flow
```
function unless(test, then) {
  if(!test) then();
}

repeat(3, n => {
  unless(n % 2 == 1, () => {
    console.log(n, "is even");
  });
});
// -> 0 is even
// -> 2 is even
```

## Filtering Arrays
To find the scripts in the data set that are in use, this function is helpful. 
It filters out the elements in the array that don't pass a test.

```
function filter(array, test) {
  let passed = [];
  for(let element of array) {
    if(test(element)) {
      passed.push(element);
     }
  }
  return passed;
}
```

## Transforming with Map
Say we have an array of objects representing scripts, produced by filtering 
the SCRIPTS array somehow. But we want an array of names.

The `map` method transforms an array by applying a function to all of its elements 
and building a new array from the returned value. The new array will have the same 
length as the input array, but its content will have been `mapped` to a new
form by the function

```
function map(array, transform) {
  let mapped = [];
  for(let element of array) {
    mapped.push(transform(element));
  }
  return mapped;
}
```

## Summarizing with reduce
Another common thing to with arrays is to compute a single value from them.
Summing a collection of numbers for instance.

The higher-order operation that represents this pattern is called `reduce` or 
`fold`. It builts a value by repeatedly taking a single element from the array 
and combining it with the current value. When summing numbers, you'd start
with the number zero and for each element, add that to the sum.

The params for `reduce` are, apart from the array, a combining function and 
a start value.

```
function reduce(array, combine, start) {
  let current = start;
  for(let element of array) {
    current = combine(current, element);
  }
  return current;
}
console.log(reduce([1,2,3,4], (a,b) => a + b, 0));
```

The standard array method `reduce` has an added convenience. If you array contains
at least one element, you are allowed to leave off the start argument. the method
will take the first element of the array as its start value and start reducing
at the second element.

To use `reduce` (twice), we can have:
```
function charCount(script) {
  return script.ranges.reduce((count, [from, to]) => {
    return count + (to - from);
  }, 0);
}

console.log(SCRIPTS.reduce((a,b) => {
  return characterCount(a) < characterCount(b) ? b : a;
});
```

## Strings and Character Codes
Each script has an array of character code ranges associated with it. Given a 
char code, we could use a function like this to find the corresponding script:

```
function characterScript(code) {
  for(let script of SCRIPTS) {
    if(script.ranges.some(([from, to]) => {
      return code >= from && code < to;
    })) {
        return script;
      }
  }
  return null;
}
```

It's mentioned that JS strings are encoded as sequence of 16-bit number. These
are called *code units*. A Unicode character code was initially supposed to fit
within such a unit. When it's clear that wan't going to be enough, many people 
belked at the need to use more memory per character. To address the concerns,
UTF-16, the format used by JS strings, was invented. It describes most common 
characters using a single 16-bit code unit but uses a pair of 2 such units for
others.

UTF-16 is generally considered a bad idea today. It's easy to write programs 
that pretend code units and characters are the same thing. If you language
doesn't use two-unit characters, that will appear to work just fine.

```
let horseShoe = "🐴👟"
console.log(horseShoe.length); // -> 4
console.log(horseShoe[0]); // Invalid half-character
console.log(horseShoe.charCodeAt(0)); // 55357 (Code of half-character)
console.log(horseShoe.codePointAt(0)); // 128052 (Actual code for horse emoji)
```

JS's `charCodeAt` method gives you a code unit, not a full character code. The
`codePointAt` method, added later, does give a full Unicode character. So we 
could use that to get characters from a string. A `for/of` loop can also be 
used on strings. It was introduced where people were aware of the problems with
UTF-16. When you use it to loop over a string, it gives you a real characters,
not code units.

```
let roseDragon = "🌹🐉"
for(let char of roseDragon) {
  console.log(char);
}
// 🌹
// 🐉
```







