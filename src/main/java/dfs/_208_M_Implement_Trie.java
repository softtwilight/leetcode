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
     * 	solution里Node是用26长度的数组来放child，这个可以优化一下。
     * 	还有就是内部变量c在这里也不是必要的，因为map的key就是c，不知道c是不能找到Node的
     * 	sreach 和 sreachPrefix的搜索部分有部分代码可以公用的。
     *
     * solution的代码写得很好，可以参考
     * https://leetcode.com/problems/implement-trie-prefix-tree/solution/
     *
     */
    class Trie {

        class Node {
            Map<Character, Node> child = new HashMap<>();
            boolean isFinish = false;

            public Node getChild(char c) {
                return child.get(c);
            }
        }
        Node head = new Node();

        /** Initialize your data structure here. */
        public Trie() {
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Node cur = head;
            for (int i = 0; i < word.length(); i++) {
                Node no = cur.child.computeIfAbsent(word.charAt(i), c -> new Node());
                cur = no;
            }
            cur.isFinish = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Node cur = head;
            for (int i = 0; i < word.length(); i++) {
                cur = cur.getChild(word.charAt(i));
                if (cur == null) return false;
            }
            return cur.isFinish;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Node cur = head;
            for (int i = 0; i < prefix.length(); i++) {
                cur = cur.getChild(prefix.charAt(i));
                if (cur == null) return false;
            }
            return true;
        }
    }
}
