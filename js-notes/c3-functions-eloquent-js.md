# Functions

## Bindings and scopes
Each binding has a *scope*, which is part of the program in which the binding
is visible. For bindings defined outside of any function or block, the scope
is the whole program. These are *global*.

Bindings craeted for function params or declared inside a function can be
referenced only in that function, so they are known as *local* binding. Every
time the function is called, new instances of these bindings are created. 
This provides some isolation between functions.

When multiple bindings have the same name - in that case, code can see only the
innermost one. For example, when the code inside the *halve* function refers
to n, it's seeing its own n, not the global n.

```
const halve = function(n) {
  return n / 2;
}

let n = 10;
console.log(halve(100));
// -> 50
console.log(n);
// -> 10
```

The code inside a function can see the binding from the outer function. But its local bindings, such as unit or ingredientAmount, are not visible in the outer function.

The set of bindings visible inside a block is determined by the place of the block in the program text. Each local scope can see all the local scopes that contain it, and all scropes can see the global scope. This usually called **lexical scoping**

## Declaration notation
```
function square(x) {
  return x * x;
}
```

This is a *function declaration*. There's a subtlety with this form:

```
console.log("The future says:", future());

function future() {
  return "You'll never have flying cars";
}
```

The code works, even though the function is defined *below* the code that uses it. The function declarations are not part of the regulater top-to-bottom flow of control. They are concenptually moved to the top of their scope and can be used by all the code in that scope. 

## Optional Arguments

The following code is allowed and executes without any problem:

```
function square(x) {
  return x * x;
}

console.log(square(4,true,"hedgehod"));
```

The extra args are ignored. If you write an = operator after a param, followed by an expression, the value of that expression will replace the argument when it's not given.

```
function power(base, exponent = 2) {
  let result = 1;
  for(let count = 0; counter < exponent; count++) {
    result *= base;
  }
  return result;
}

console.log(power(4));
// 16
console.log(power(2,6));
// 64
```

## Closure

What happens to local bindings when the function call that created them is no longer active?

```
function wrapValue(n) {
  let local = n;
  return () => local;
}

let wrap = wrapValue(1);
let wrap = wrapValue(2);

console.log(wrap1()); // 1
console.log(wrap2()); // 2
```

Both instances of the bindings can still be accessed. This situation is a good demo of the fact that local bindings are created anew for every call, and different calls can't trample on one another's local bindings.

This feature - being able to reference a specific instance of local binding in an enclosing scope - is called *close*. A function that references bindings from local scopes around it is called a closure.

```
function multiplier(factor) {
  return number => number * factor;
}

let twice = multiplier(2);
console.log(twice(5));
// 10
```

Think of function values as containing both the code in their body and the env in which they're created. When called, the function body sees the environment in which it was created, not the environment in which it's called.

In the example, `multiplier` is called and creates an environment in which its `factor` param is bound to 2. The function value it returns, which is stored in `twice`, remembers this env. So when that is called, it multiplies its arg by 2.


