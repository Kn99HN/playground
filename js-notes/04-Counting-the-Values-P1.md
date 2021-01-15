# Counting the Values (Part 1)

## Undefined
There is only one value of the type - undefined. it IS a value.

```
let person = undefined;
console.log(person.mood) //TypeError

=> Cannot read a property from undefined
```

It represents the concept of an *unintentionally* missing value. It commonly "occurs naturally". It shows up in situations where JavaScript doesn't know what value you wanted.

```
let bandersnatch;
console.log(bandersnatch); //undefined
```

## Null
Similar to undefined in the way that it behaves similarly. It will throw an error if you try to access its properties.

```
let mimsy = null;
console.log(mimsy.mood); //TypeError!
```

**null is the only value of its own type**. Due to a [bug](https://2ality.com/2013/10/typeof-null.html?ck_subscriber_id=1114055276) in JavaScript, it pretends to be an object:

```
console.log(typeof(null)); // "object"
```

However, it is **NOT** an object. it IS a **PRIMITIVE VALUE**. It doesn't behave like an object. `typeof(null)` is a historical accident that exist in JavaScript. `null` is used for *intentionally* missing values. 

## Booleans
There are only 2 boolean values: **true** and **false**.

```
let isSad = true;
let isHappy = !isSad;
let isFeeling = isSad || isHappy;
let isConfusing = isSad && isHappy

=> isFeeling is True and isConfusing is False
```

## Numbers
```
console.log(typeof(28)); //number
console.log(typeof(3.14)); // number
```

JavaScript numbers don't behave exactly the same way as regular mathematical numbers do.

```
console.log(0.1 + 0.2 === 0.3) //false;
console.log(0.1 + 0.2 === 0.30000000000000004); //true
```

This is not broken but rather a *floating point math*. JavaScript treats numbers as having a limited precision. The closer we are to 0, the more precision numbers have, and the closer they "sit" to each other.

### Special Numbers
Floating point math includes a few special numbers such as `NaN, Infinity, -Infinity, -0`. They exist because sometimes you might execute operations like 1 / 0, and JavaScript needs to represent their result somehow. 

```
let scale = 0;
let a = 1 / scale; // Infinity
let b= 0 / scale // NaN
let c = -a; // -Infinity;
let d = 1/c; // -0
```

`NaN` stands for "not a number". It is a numeric value and of type number. It's not null, undefined, a string or some other type. 


