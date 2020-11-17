package src;
import java.util.Map;
import java.util.HashMap;

class Node {
  Map<Character, Node> children;
  int value;

  public Node(Map<Character, Node> children, int value) {
    this.children = children;
    this.value = value;
  }

  public Node() { children = new HashMap<>(); }
}

public class Trie {
  /** Initialize your data structure here. */
  Node node;
  public Trie() {
      this.node = new Node();
  }

  /** Inserts a word into the trie. */
  public void insert(String word) {
      Node matchedNode = getNode(word);
      if(matchedNode == null) {
        Node tmp = node;
        for(int i = 0; i < word.length(); i++) {
            
        }
      } else {
          
      }
  }

  public Node getNode(String word) {
      Node tmp = node;
      for(int i = 0; i < word.length(); i++) {
        char curr = word.charAt(i);
        if(tmp.children.containsKey(curr)) {
            tmp = tmp.children.get(curr);
        } else {
            return null;
        }
    }
    return tmp;
  }

  /** Returns if the word is in the trie. */
  public boolean search(String word) {
      Node tmp = node;
      for(int i = 0; i < word.length(); i++) {
          char curr = word.charAt(i);
          if(tmp.children.containsKey(curr)) {
              tmp = tmp.children.get(curr);
          } else {
              return false;
          }
      }
      return true;
  }

  /**
   * Returns if there is any word in the trie that starts with the given prefix.
   */
  public boolean startsWith(String prefix) {
      return this.search(prefix); //double check?
  }
}