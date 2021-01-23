# Regular Expression

Regular expression are a way to describe patterns in string data. They form a 
small separate language that is part of JavaScript and many other languages and 
systems.

## Creating a Regular Expression
A regular expression is a type of object. It can be either constructed with 
`RegExp` constructor or written as a literal value by enclosuing a pattern in 
forward slash (/) characters.

```
let re1 = new RegExp("abc);
let re2 = /abc/;
```

Both of the regex objects represent the same pattern: an `a` char followed by 
a `b` followed by a `c`. 

When using the RegExp constructor, the patter is written as a normal strings so 
the usual rules apply for backslashes.

The second notation, where the pattern appears between slash characters, treats 
backslashes somewhat differently. Since a forward slash ends the pattern, we need 
to put a backslash before any forward slash that we want to be part of the pattern. 
In addition, backslashes that aren't part of special character codes (like `\n`) 
will be *preserved*, rather than ignored as in string and change the meaning of 
the pattern. Some characters, such as question marks and plus signs, have special 
meaning in regular expressions and must be preceded by a backslash if they are meant 
to represent the character itself.

```
let eighteenPlus = /eighteen\+/;
```

## Testing for matches
Regular expression objects have a number of methods. The simplest one is `test`.
It returns a Boolean telling you whether the string contains a match of the pattern 
in the expression.

```
console.log(/abc/.test("abcde"));
// true 
console.log(/abc/.test("abxde"));
// false
```

A regex consisting of only nonspecial characters simply represents sequence of char.
If `abc` occurs anywhere in the string we are testing against (not just at the start),
`test` will return `true`.

## Sets of Characters
Putting a set of characters between square brackets makes that part of the expression 
match any of the characters between the brackets.

Both of the following expressions match all strings that contain a digit:
```
console.log(/[0123456789]/.test("in 1992"));
// true
console.log(/[0-9]/.test("in 1992"));
// true
```

Within square brackets, a hyphen (-) between 2 chars can be used to indicate 
a arange of characters, where the ordering is determined by the character's 
Unicode number. Characters 0 to 9 sit right next to each other in this ordering 
(48 to 57).

A number of common cha group have their own built-in shortcuts. Digits are one of 
them: `\d` means the same thing as `[0-9]`.

```
\d      Any digit char
\w      An alphanumeric character("word character")
\s      Any whitespace character(space, tab, newline and similar)
\D      A character that is not a digit
\W      A nonalphanumeric character
\S      A nonwhitespace character 
.       Any character excpt for newline
```
So you could match a date time format like `01-30-2003 15:20` with the following:
```
let datetime = /\d\d-\d\d-\d\d\d\d \d\d:\d\d/;
console.log(datetime.test("01-30-2003 15:20"));
// true
console.log(datetime.test("01-jan-2003 15:20"));
```

The backslash codes can be used inside square brackets. For instance, `[\d.\` 
means any digit or a period character. But the period ifself, between square brackets,
loses its special meaning. The same goes for other special characters, such as 
`+`.

To `invert` a set of characters - that is, to express that you want to match 
any character *except* the ones in the set - you can write `^`

```
let notBinary = /[^01]/
console.log(notBinary.test("111100111"));
// false
console.log(notBinary.test("11120"));
// true
```

## Repeating parts of a pattern
When we put `+` after something in a regular expression, it indicates that the 
element may be repeated more than once. Thus, `/\d+/` matches one or more digit 
characters.

```
console.log(/'\d+'/.test("'123'"));
// true
console.log(/'\d+'/.test("''"));
// false
console.log(/'\d*'/.test("'123'"));
// true
console.log(/'\d*'/.test("''"));
// false
```

The `*` has a similar meaning but also allows the pattern to match zero times. 
A question mark makes a part of the pattern *optional*, meaning it may occur zero
times or one time. In the following example, the `u` char is allowed to occur, 
but the pattern also matches when it's missing.

```
let neighbor = /neighbou?r/;
console.log(neighbor.test("neighbour"));
// true
console.log(neighbor.test("neighbor"));
// true
```

To indicate that a pattern should occur a precise number of times, use braces. 
Putting `{4}` after an element, for instance, requires it to occur exactly four 
times. It's also possible to specify a range this way: `{2:4}` means the element 
must occur at least twice and at most four times.

```
let dateTime = /\d{1,2}-\d{1,2}-\d{4} \d{1,2}:\d{2}/;
console.log(dateTime.test("1-30-2003 8:45"));
// true
```

## Grouping Subexpressions
To use an operator like `*` or `+` on more than 1 element at a time, use parentheses.
A part of the regular expression that is enclosed in parentheses counts as a single 
element as far as the operators following it are concerned

```
let cartoonCrying = /boo+(hoo+)+/i;
console.log(cartoonCrying.test("Boohoooohoohooo"));
// → true
```
The first and second `+` char apply only to the second `o` in `boo` and `hoo`,
respectively. The third `+` applies to the whole group (`hoo+`) matching one 
or more sequences like that. The `i` at the end makes this regex case insensitive

## Matches and Groups
Regex also have an `exec` method that will return `null` if no match was found 
and return an object with info about the match otherwise.

```
let match = /\d+/.exec("one two 100");
console.log(match);
// ["100"]
console.log(match.index);
// 8
```

An object returned from exec has an `index` property that tells us `where` in the 
string the successful match begins. Other than that, the object is a string array.
When the regex contains subexpressions grouped with parentheses, the text that matched 
those groups will also show up in the array. The whole match is always the 1st 
element. The next element is the part matched by the 1st group (the one whose 
opening parenthesis comes first in the expression), then the second group, and 
so on.

```
let quotedText = /'([^']*)'/;
console.log(quotedText.exec("She said 'hello'"));
// ["'hello'", "hello"]
```

When a group does not end up being matched at all, its position in the output array 
is `undefined`. Similarly, when a group is matched multiple times, only the last 
match ends up in the array.

```
console.log(/bad(ly)?/.exec("bad"));
// ["bad", undefined]
console.log(/(\d)+/.exec("123"));
// ["123, "3"]
```

Groups can be useful for extracting parts of a string.

## The Date class
JS has a standard class for representing dates or points in time. It's called 
`Date`. If you simply create a date object using a `new`, you get current 
date and time.

JS uses a convention where month numbers start at zero, yet day numbers start 
at one. Timestamps are stored as the number of milliseconds since the start 
of 1970, in the UTC time zone. This follows a convention set by "Unix time",
which was invented around that time. You can use negative numbers for times 
before 1970. 

We can create a date object from a string
```
function getDate(string) {
  let [_, month, day, year] = /(\d{1,2})-(\d{1,2})-(\d{4})/.exec(string);
  return new Date(year, month - 1, day);
}
console.log(getDate("1-30-2003"));
//// → Thu Jan 30 2003 00:00:00 GMT+0100 (CET)
```

The `_` binding is ignored and used only to skip the full match element in the 
array.

## Choice Patterns
Say we want to know whether a piece of text contains not only a number but a number 
followed by one of the words `pig,cow, or chicken`. We can use pipe (|) char 
denotes a choice between the pattern to its left and the pattern to its right:

```
let animalCount = /\b\d+ (pig|cow|chicken)s?\b/;
console.log(animalCount.test("15 pigs"));
// true
console.log(animalCount.test("15 pigchickens"));
// false
```

## The Mechanics of Matching
When you use `exec` or `test`, the regex engine looks for a match in the string 
by trying to match the expression first from the start of the string, then from 
the second char, and so on, until it finds a match or reaches the end of the string.
It'll either return the first match that can be found or fail to find any match 
at all.

To do the actual matching, the engine treats a regex like a flow diagram. Our 
expression matches if we can find a path from left side of the diagram to the 
right side. We keep a current position in the string and every time we move 
through a box, we verify that part of the string after our current position 
matches that box.

## Backtracking
The regex `/\b([01]_b|[\da-f\+h|\d+)\b/` matches either a binary number followed 
by a `b`, a hex number followed by an `h` or a regular decimal number with no 
suffix char.
