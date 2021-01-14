# Code Snippet
```
let a = 10;
let b = a;
a = 0;

=> a = 0 and b = 10;
```

# Mental Model
Defintion: Approximation of how something works in our head.

Mental models should be noticed and introspected often. They are usually a combination of visual, spatial, and mechanical mental shortcuts.

These intuitions influence how we read code. Sometimes, our mental models are wrong due to reading a tutorial, which sacrificed correctness for the ease of explaning. We can also easily transferred an intuition about a particular language feature, like `this`, from another language we learned earlier. Maybe we never verfied a piece of code if it was accurate.

# Coding, Fast and Slow
In the book "Thinking, Fast and Slow", Daniel Kahneman points out that humans use 2 different "systems" when thinking.

We usually rely on the "fast" system. It's good at pattern matching and "gut reactions". But not at planning.

Humans also possess a "slow" thinking system. This "slow" system is responsible for complex step-by-step reasoning. It lets us plan future events, engage in arguments or follow proofs.
```
function duplicateSpreadsheet(original) {
  if (original.hasPendingChanges) {
    throw new Error('You need to save the file before you can duplicate it.');
  }
  let copy = {
    created: Date.now(),
    author: original.author,
    cells: original.cells,
    metadata: original.metadata,
  };
  copy.metadata.title = 'Copy of ' + original.metadata.title;
  return copy;
}
```

The function above makes a copy of the original object. However, there's a bug that changes the original title since `copy.metadata.title` references the `original.metadata.title`.

