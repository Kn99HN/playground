# Properties

```
let sherlock = {
    surname: 'Holmes',
    address: {
        city: 'London'
    }
}

let john = {
    surname: 'Watson',
    address: sherlock.address
}

john.surname = 'Lennon';
john.address.city = 'Malibu';

console.log(sherlock.surname); // Holmes
console.log(sherlock.address.city); // Malibu
console.log(john.surname); // Lennon
console.log(john.address.city); // Malibu
```

## Properties
```
let sherlock = {};
```

We create a new object. Objects are primarily useful to group related data together. *properties* belong to a particular object. **In our JavaScript universe, both variables and properties act like wires**. However, the wires of properties *start from objects rather than from our code. The object has 2 **properties**. Properties don't *contain* values - they point at them. 

## Property Names
Properties have names. A single object can't have 2 properties with the same name. The property names are always case-sensitive!

## Assigning to a Property
We just change the wire to the new property.

## Missing Properties
What happens if we read a property that doesn't exists:

```
let sherlock = {
    surname: 'Holmes',
    age: 64
}
console.log(sherlock.boat);
```

These rules look like this:
- Figure out the value of the part before the dot(.).
- If that value is null or undefined, throw an error immediately.
- Check whether a property with that name exists in our object:
    - If it exists, answer with the value this property points to.
    - If it doesn't exists, answer with the undefined value

```
let sherlock = {
    surname: 'Holmes',
    age: 64
}
console.log(sherlock.boat); //undefined
console.log(sherlock.boat.name); //TypeError!
```