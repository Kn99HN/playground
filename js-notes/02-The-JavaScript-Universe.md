# Code and Values

A value is a thing in the JavaScript universe.

Numbers, objects and functions are values. However, many things, such as `if` statement or a variable declaration, are not values. There are 2 kinds of values

## Primitive Values
Primitive values are numbers and strings, among other things. There's nothing in the code that would affect them.

## Objects and Functions
Objects and functions are also values, but not primitive. They can be manipulated from the code.

# Expressions
Questions that JavaScript can answer. JavaScript answers expressions in the only way it knows how - with values. Expressions always result in a single value.

# Checking a Type
Values of same type behave similarly. 

```
console.log(typeof(2)); // number
console.log(typeof("hello")); // string
console.log(typeof(undefined)); // undefined
```

# Types of Values

## Primitive Values
- `Undefined(undefined)`, used for unintentionally missing values.
- `Null(null)` used for intentionally missing values.
- `Booleans(true and false)`, used for logical operations.
- `Numbers(-100, 3.14, and others)` used for math calculations.
- `Strings("hello")` used for text.
- `Symbols(uncommon)`, used to hide implementation details.
- `BigInts`(uncommon and new), used for math on big numbers.

## Objects and Functions
- `Object({} and others)` used to group related data code.
- `Functions(x => x * 2 and others)` used to refer to code.

## No Other Types
In JavaScript, there are no other fundamental values types other than the ones above. The rest are all objects!