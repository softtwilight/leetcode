package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:   softtwilight
 * Date:     2021/01/17 22:21
 */
public class _133_M_Clone_Graph {
    private static final _133_M_Clone_Graph instance = new _133_M_Clone_Graph();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * use dfs. the normal dfs problem.
     */
    Map<Integer, Node> meet = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        if (meet.containsKey(node.val)) {
            return meet.get(node.val);
        }
        Node result = new Node(node.val);
        meet.put(node.val, result);
        List<Node> neighbors = new ArrayList<>();
        for (Node nei : node.neighbors) {
            neighbors.add(cloneGraph(nei));
        }
        result.neighbors = neighbors;


        return result;
    }

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
