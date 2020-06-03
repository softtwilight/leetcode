package dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:   softtwilight
 * Date:     2020/06/03 22:31
 */
public class _208_M_Implement_Trie {
    private static final _208_M_Implement_Trie instance = new _208_M_Implement_Trie();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 	39 ms	57.6 MB
     */
    class Trie {

        class Node {
            Character c;
            Map<Character, Node> child = new HashMap<>();
            boolean isFinish = false;
            public Node(Character c) {
                this.c = c;
            }
        }
        Node head = new Node('0');

        /** Initialize your data structure here. */
        public Trie() {
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Node cur = head;
            for (int i = 0; i < word.length(); i++) {
                Node no = cur.child.computeIfAbsent(word.charAt(i), Node::new);
                cur = no;
            }
            cur.isFinish = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Node cur = head;
            for (int i = 0; i < word.length(); i++) {
                cur = cur.child.get(word.charAt(i));
                if (cur == null) return false;
            }
            return cur.isFinish;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Node cur = head;
            for (int i = 0; i < prefix.length(); i++) {
                cur = cur.child.get(prefix.charAt(i));
                if (cur == null) return false;
            }
            return true;
        }
    }
}
