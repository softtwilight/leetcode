package dfs;

import datastructure.TreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * Author:   softtwilight
 * Date:     2020/05/31 22:39
 */
public class _105_M_Construct_Binary_Tree {
    private static final _105_M_Construct_Binary_Tree instance = new _105_M_Construct_Binary_Tree();

    public static void main(String[] args) {

        TreeNode input = TreeNode.createByArray(new Integer[] {3,9,20,null, null, 15,7});

        List<Integer> container = new ArrayList<>();
        instance.inorder(input, container);
        System.out.println(container);
    }

    /**
     * 这道题没什么思路啊
     * preorder 和 inorder的代码可以知道，右边的遍历是一样的，
     * 所以如果最右边两个数相等，那么他一定是属于右子节点
     * 这个信息也不太够， 不足以构建一个tree。
     *
     * 换个思路，我们知道preorder的第一个是head， 因为节点都是唯一的，所以我们就可以找到
     * head在inorder中的位置，而在inorder中的位置也意味着，head.left 的size；
     * 那么我们也知道了head.rigth 的size。
     *
     * 这样我们对两个分段 left， right递归求解。
     * head.left = buildTree(left part);
     * head.right = buildTree(right part)
     * return head;
     * 这样我们就可以得到一个递归的方式。
     * 然后我们再看边界情况，什么情况下放回null.
     *
     * 1 ms	+ 39.7 MB
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        for (int i = 0; i < inorder.length; i++) {
            memo.put(inorder[i], i);
        }
        return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length -1);

    }

    Map<Integer, Integer> memo = new HashMap<>();

    private TreeNode helper(int[] preorder, int plo, int phi, int[] inorder, int ilo, int ihi) {

        if (plo > phi || phi >= preorder.length || ilo > ihi) return null;
        TreeNode head = new TreeNode(preorder[plo]);
        int headIndex = memo.get(preorder[plo]);
        int leftSize = headIndex - ilo;
        TreeNode left = helper(preorder, plo + 1, plo + leftSize, inorder, ilo, headIndex - 1);
        TreeNode right = helper(preorder, plo + leftSize + 1, phi, inorder, headIndex + 1, ihi);
        head.left = left;
        head.right = right;
        return head;
    }



    private int in = 0;
    private int pre = 0;

    /**
     * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/discuss/34543/Simple-O(n)-without-map
     * 这个解法很棒，但是还没有完全理解他的正确性。
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        return build(preorder, inorder, Integer.MIN_VALUE);
    }

    private TreeNode build(int[] preorder, int[] inorder, int stop) {
        if (pre >= preorder.length) return null;
        if (inorder[in] == stop) {
            in++;
            return null;
        }
        TreeNode node = new TreeNode(preorder[pre++]);
        node.left = build(preorder, inorder, node.val);
        node.right = build(preorder, inorder, stop);
        return node;
    }

    private void preorder(TreeNode node, List<Integer> re) {
        if (node == null) return;
        re.add(node.val);
        preorder(node.left, re);
        preorder(node.right, re);
    }

    private void inorder(TreeNode node, List<Integer> re) {
        if (node == null) return;
        preorder(node.left, re);
        re.add(node.val);
        preorder(node.right, re);
    }

}
