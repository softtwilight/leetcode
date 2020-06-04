package dfs;

import java.util.*;

/**
 * https://leetcode.com/problems/word-search-ii/
 *
 * Author:   softtwilight
 * Date:     2020/06/04 22:09
 */
public class _212_H_Word_Search_II {
    private static final _212_H_Word_Search_II instance = new _212_H_Word_Search_II();

    public static void main(String[] args) {
        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain"};
        System.out.println(instance.findWords(board, words));
    }

    /**
     * 8 ms	49 MB
     *
     * 这道题需要用到208的思路，构建一个Trie 一样树状link结构。
     * 类似为finish的思路， 我们直接把word 放入到finish的节点。
     * 这样就不用保存搜索路径了， 最开始的思路是用StringBuilder。小优化， 不影响整体思路。
     *
     * 然后每一个节点搜索head， dfs：
     * 如果不存在，或者已经访问过， 放回。
     * 如果存在，是finish的节点， 则添加结果，
     * 在新找到的Node上， 对节点的 上下左右节点 进行递归搜索。
     *
     * 然后一定要注意，在4个节点的调用结束后， 意味着该点的访问结束， 放回上一层调用。
     * 所以一定要把visted 重新设置为false
     */
    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return result;
        }
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean[][] visited = new boolean[board.length][board[0].length];
                dfs(trie.head, board, i, j, visited);
            }
        }
        return result;
    }

    List<String> result = new ArrayList<>();

    private void dfs(Trie.Node node, char[][] board, int i, int j, boolean[][] visited) {
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || visited[i][j]) {
            return;
        }
        Trie.Node cur = node.getChild(board[i][j]);
        if (cur == null) return;
        visited[i][j] = true;
        if (cur.word != null) {
            result.add(cur.word);
            //这个地方错了两次， 忘记去重了。
            cur.word = null;
        }
        dfs(cur, board, i - 1, j, visited);
        dfs(cur, board, i + 1, j, visited);
        dfs(cur, board, i, j - 1, visited);
        dfs(cur, board, i, j + 1, visited);

        // 这个地方错了3次。一直没找到原因。因为这里其实是返回上一级调用，表示该节点的路径已经走完了
        // 所以需要将visited 重新标记为false
        // 看到别人的解里，是将board 标记为另外的字符，可以节省空间。
        visited[i][j] = false;
    }

    class Trie {

        class Node {
            Node[] links = new Node[26];
            public String word;
            public Node getChild(char c) {
                return links[c - 'a'];
            }
            public void addChild(char c, Node child) {
                links[c - 'a'] = child;
            }
        }
        public Node head = new Node();
        public Trie() {}

        public void insert(String word) {
            Node cur = head;
            for (int i = 0; i < word.length(); i++) {
                Node no = cur.getChild(word.charAt(i));
                if (no == null) {
                    no = new Node();
                    cur.addChild(word.charAt(i), no);
                }
                cur = no;
            }
            cur.word = word;
        }

    }
}
