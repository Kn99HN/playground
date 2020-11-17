package src;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class ListNode {
int key;
  int val;
  ListNode next;
  ListNode prev;

  public ListNode(int key,int val) { 
      this.key = key;
      this.val = val; }

  public ListNode(int key, int val, ListNode next, ListNode prev) {
      this.key = key;
    this.val = val;
    this.next = next;
    this.prev = prev;
  }

  public String toString() {
      return this.val + "";
  }
}

public class LRUcache {

  private int capacity;
  private Map<Integer, ListNode> map;
  private ListNode dummyHead;
  private ListNode dummyTail;

  //   LRUCache(int capacity) Initialize the LRU cache with positive size
  //   capacity.
  public LRUcache(int capacity) {
    this.capacity = capacity;
    this.dummyHead = new ListNode(0, 0);
    this.dummyTail = new ListNode(0, 0);
    dummyHead.next = dummyTail;
    dummyTail.prev = dummyHead;
    this.map = new HashMap<>();
  }

  //   int get(int key) Return the value of the key if the key exists, otherwise
  //   return -1.
  public int get(int key) {
    if (map.containsKey(key)) {
        ListNode node = map.get(key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        ListNode head = this.dummyHead.next;
        head.prev = node;
        node.next = head;
        node.prev = this.dummyHead;
        this.dummyHead.next = node;
        return node.val;
    }
    return -1;
  }

  private void removeTail() {
    ListNode tail = this.dummyTail.prev;
    tail.prev.next = this.dummyTail;
    this.dummyTail.prev = tail.prev;
    map.remove(tail.key);
    tail = null;
  }

  private void addToHead(ListNode node) {
    ListNode head = this.dummyHead.next;
    this.dummyHead.next = node;
    node.prev = this.dummyHead;
    node.next = head;
    head.prev = node;
  }

  // void put(int key, int value) Update the value of the key if the key exists.
  // Otherwise, add the key-value pair to the cache. If the number of keys
  // exceeds the capacity from this operation, evict the least recently used
  // key.
  public void put(int key, int value) {
    if (map.containsKey(key)) {
      map.get(key).val = value;
    } else {
      // create new key-value pair
      if (map.keySet().size() + 1 > this.capacity) {
        this.removeTail();
      }
      ListNode node = new ListNode(key, value);
      this.addToHead(node);
      map.put(key, node);
    }
  }

  public String toString() {
    StringBuilder output = new StringBuilder();
    for (Map.Entry<Integer, ListNode> entry : this.map.entrySet()) {
      output.append(entry.getKey() + "=" + entry.getValue() + ",");
    }
    return output.toString();

  }
}

class Main {
  public static void main(String[] args) {
    LRUcache cache = new LRUcache(2);
    cache.put(1, 1);
    cache.put(2, 2);
    System.out.println(cache.toString());
    System.out.println(cache.get(1));
    cache.put(3, 3);
    System.out.println(cache.toString());
    System.out.println(cache.get(2));
    cache.put(4,4);
    System.out.println(cache.toString());
    System.out.println(cache.get(1));
    System.out.println(cache.get(3));
    System.out.println(cache.get(4));
  }
}