# Equality of Values

## Kinds of Equality

**Strict Equality** a === b (triple equals).
**Loose Equality** a == b (double equals).
**Same Value Equality**: Object.is(a,b)

## Same Value Equality: Object.is(a,b)

In JavaScript, *Object.is(a,b)* tells us if a and b are the same value:

```
console.log(Object.is(2,2)); //true
console.log(Object.is({}, {})); //false because both point to different objects
```

```
let dwarves = 7;
let continents = '7';
let worldWonders = 3 + 4;

console.log(Object.is(dwarves, continents)); // false
console.log(Object.is(continents, worldWonders)); // false
console.log(Object.is(worldWonders, dwarves)); // false
```

If 2 values are represented by a single shape on our diagram, it means that they aren't really 2 different values. 

## But what about objects?
```
let banana = {};
let cherry = banana;
let chocolate = cherry;
cherry = {};
```

```
console.log(Object.is(banana, cherry)); //false;
console.log(Object.is(cherry, chocolate)); //false;
console.log(Object.is(chocolate, banana)); //true
```

Recall that `{}` creates new object.

## Strict Equality a === b

```
console.log(2 === 2); // true
console.log({} === {}); // false
```

Same Value Equality has the same intuition as strict equality. However, there are *two rare cases* where the behavior of === is different.

Consider these cases as exceptions to the rule:

- **NaN === NaN is false**, although they are the same value
- **-0 === 0 and 0 === -0 are true**, although they are different values

### First Special Case: NaN

```
let width = 0 / 0; // NaN
```

Further calculations with `NaN` will give you `NaN` again:

```
let height: width * 2; // NaNs
```

Recall that **NaN === NaN is always false**:

However, **NaN is the same value as NaN**:

```
console.log(Object.is(width, height)); //true
```

The reason for it being `false` is largely historical so we should accept it as part of life.

### Second Special Case: -0

**Both 0 === -0 and -0 === 0 are always true**

```
let width = 0; // 0
let height = -width; // -0
console.log(width === height) // true
```

However, 0 is different value from -0:

```
console.log(Object.is(width, height)); //false
```

### Coding Exercise
```
function strictEquals(a, b) {
    if(a.isNaN() && b.isNaN()) {
        return false;
    } else if((Object.is(a, -0) && Object.is(b, 0)) || (Object.is(a, 0) && Object.is(b, -0))) {
        return true;
    } else {
        return Object.is(a,b);
    }
}
```

### Loose Equality
**Loose Equality** 

```
console.log([[]] == ''); //true
console.log(true == [1]); //true
```

The rules of **Loose Equality**(also called "abstract equality") are confusing. Loose equality compares two values for equality, *after* converting both values to a common type. After conversions, the final equality comparison is performed exactly as `===`.

