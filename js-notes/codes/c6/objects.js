// A Vec Type

class Vec{
  constructor(x, y) {
    this.x = x;
    this.y = y;
  }

   plus(vector) {
    return new Vec(this.x + vector.x, this.y + vector.y);
  }

   minus(vector) {
    return new Vec(this.x - vector.x, this.y - vector.y);
  }

  get length() {
    return Math.sqrt(this.x ** 2 + this.y ** 2);
  }
}

console.log(new Vec(1,2).plus(new Vec(2,3)));
console.log(new Vec(1,2).minus(new Vec(2,3)));
console.log(new Vec(3,4).length);

// Groups
class Group{
  constructor() {
    this.obj = [];
    this.idx = 0;
  }

  has(member) {
    return this.obj.indexOf(member) >= 0;
  }

  add(member) {
    if(!this.has(member)) this.obj.push(member); 
  }

  delete(member) {
    this.obj = this.obj.filter(v => v !== member);
  }

  static from(iterable) {
    const group = new Group();
    iterable.forEach(m => group.add(m));
    return group;
  }
}

let group = Group.from([10, 20]);
console.log(group.has(10));
// → true
console.log(group.has(30));
// → false
group.add(10);
group.delete(10);
console.log(group.has(10));
// → false

class GroupIterator {
  constructor(group) {
    this.idx = 0;
    this.group = group;
  }
  next() {
    if(this.idx === this.group.obj.length) {
      return {done: true};
    }
    let value = {value: this.group.obj[this.idx]};
    this.idx++;
    return {value, done: false}
  }

}

Group.prototype[Symbol.iterator] = function() {
  return new GroupIterator(this);
};

for (let value of Group.from(["a", "b", "c"])) {
  console.log(value);
}
// → a
// → b
// → c

// Borrowing a method
let map = {one: true, two: true, hasOwnProperty: true};

// call will use the object as a param hence bind this to it so we can access
// the param object's property
console.log(Object.getPrototypeOf(map).hasOwnProperty.call(map, "one"));
