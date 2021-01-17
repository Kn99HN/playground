# Mutation

## Step 1: Declaring the sherlock variable

```
let sherlock = {
  surname: 'Holmes',
  address: {
    city: 'London'
  }
}
```

There's a sherlock variable pointing at an object. That object has 2 properties.
Its `surname` property points at 'Holmes' string value. Its `address` property
points at another object. That other object has one property called `city`.
That property points to 'London' string value.

## No Nested Objects

We have not one, but *two* completely separate objects. Two pairs of curly braces
means two objects.

**Objects might appear "nested" in our code, but in our universe each object is
completely separate.An object cannot be "inside" of other object!**

## Step 2: Declaring the john Variable

```
let john = {
  surname: 'Watson',
  address: sherlock.address
}
```

It points at an object with two properties. Its `address` property points at 
the same object that `sherlock.address` is already pointing at. Its `surname`
property points at the "Watson" string.

## Properties Always Point at Values

It's tempting to think that John's address property points at the Sherlock's
address property. This is misleading

**Remember: a property always points at a value! It can't point at another
property or a variable. In general, all wires in our universe point at values.

## Step 3: Changing the Properties
```
john.surname = 'Lennon';
john.address.city = 'Malibu';
``

The object that `john` variable points at now has its `surname` property
poiting at the "lennon" string value. More interestingly, the object that both
`john` and `sherlock's address` properties are pointing at now has a different
`city` value.

## Mutation

It's a fancy way of saying "change". It's not bad though the word has that
connotation. We need to be very intentional about it. By mutating an object used
elsewhere in the program, we've made a mess.

### Possible Solution: Mutating another object
```
john.surname = 'Lennon';
john.address = { city: 'Malibu' };
```

The wire has now changed since we create a new object. We are mutating the
`address` property of the object that `john` points at. In other words, we are
only mutating the object represeting John's data. Sherlock's data remains unchanged

### Alternative Solution: No Object Mutation
```
# Replacing variable assignment
john = {
  surname: 'Lennon',
  address: { city: 'Malibu' }
}
```

## Let vs Const
```
const shrek = { species: 'ogre' };
```

The `const` keyword lets you create read-only variables - also known as
constants. Once created, we can't point it at a different value;

```
shrek = fiona; //TypeError
```

**We can still mutate the object const points at**

```
shrek.species = 'human';
console.log(shrek.species); // 'human'
```

Only the `shrek` variable is read-only. It points at an object - that object's
properties can be mutated!


