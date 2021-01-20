// Flattening

const combineArray = (array) => {
  return array.reduce((a, b) => a.concat(b));
}

let arrays = [[1,2,3], [4,5], [6]];
console.log(combineArray(arrays));

// Your own loop

const loop = (value, test, update, body) => {
  let range = value;
  for(let i = 0; i <= range; i++) {
    if(test(i)) {
      body(value);
      value = update(value);
    }
  }
}

loop(3, n => n > 0, n => n - 1, console.log);

// Everything

function every(array, test) {
  for(let value of array) {
   if(!test(value)) return false; 
  }
  return true;
}

function everySome(array, test) {
  return !array.some(element => !test(element));
}

console.log(every([1, 3, 5], n => n < 10));
console.log(everySome([1, 3, 5], n => n < 10));
// → true
console.log(every([2, 4, 16], n => n < 10));
console.log(everySome([2, 4, 16], n => n < 10));
// → false
console.log(every([], n => n < 10));
console.log(everySome([], n => n < 10));
// → true
// → true
