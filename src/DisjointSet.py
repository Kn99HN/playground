class DisjoinSet():
  def __init__(self):
    self.arr = []
    self.dict = {}

  def add(self, value):
    self.arr.append(value)
    self.dict[value] = 1

  def find(self, value):
    if value >= 0 or value <= len(self.arr) - 1:
      val = self.arr[value]
      while val != self.arr[val]:
        parent = self.arr[val]
        self.arr[value] = self.arr[val]
        val = self.arr[parent]
      return val

  def parent(self, value):
    parent = value
    while parent != self.arr[value]:
      parent = self.arr[value]
    return parent

  def union(self, left, right):
    left_parent = self.parent(left)
    right_parent = self.parent(right)
    left_height = self.dict[left_parent]
    right_height = self.dict[right_parent]
    if left_height < right_height:
      self.arr[left] = right_parent
      self.dict[right_parent] = self.dict.get(right_parent) + 1
    else:
      self.arr[right] = left_parent
      self.dict[left_parent] = self.dict.get(left_parent) + 1

  def same_set(self, left, right):
    left_parent = left
    while self.arr[left] != left:
      left = self.arr[left]
      left_parent = self.arr[left]
    right_parent = right
    while self.arr[right] != right:
      right = self.arr[right]
      right_parent = self.arr[right]
    return left_parent == right_parent

disjoint_set = DisjoinSet()
for i in range(10):
  disjoint_set.add(i)

disjoint_set.union(0, 1)
disjoint_set.union(6,8)
disjoint_set.union(4, 6)

print(disjoint_set.same_set(4, 6))

disjoint_set.union(3, 7)
disjoint_set.union(2, 9)
disjoint_set.union(9 , 3)
disjoint_set.find(7)

for val in disjoint_set.arr:
  print(val)
