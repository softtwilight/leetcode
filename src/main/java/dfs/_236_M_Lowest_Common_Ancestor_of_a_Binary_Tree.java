package dfs;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * Author:   softtwilight
 * Date:     2020/06/07 0:04
 */
public class _236_M_Lowest_Common_Ancestor_of_a_Binary_Tree {
    private static final _236_M_Lowest_Common_Ancestor_of_a_Binary_Tree instance = new _236_M_Lowest_Common_Ancestor_of_a_Binary_Tree();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 8 ms	45.7 MB
     *
     * 递归感觉很像inductive 的推理， 我们假设这个function是work的，
     * 需要证明base case 和 P（n + 1）的case
     *
     * 而递归也是，假设function是work的， 我们运用这个function在更低的级别上，level - 1层，
     * 然后我们用子问题的解加起来， 以某种方式组合成这一层（level层）的解。
     *
     * 这个解法还是挺容易理解的，找左边的分支和右边的分支，
     * 找的是p或q 的最小的祖先，提前返回。
     * 如果left right 都有p或q的祖先， 那么p q一定是分别在左右分支上， name root就是我们要找的。
     * 如果只有left 或 right 有返回值， 我们假设返回的是p （对称性， q也一样），那么q一定是p的子节点。
     * 而p就是我们找的root。
     *
     * 可是有点难理解的是，这里的function 有两层含义 ：
     *  1.p 和 q的最小祖先。（也就是我们题目需要的
     *  2.p 或 q的最小祖先。（是我们在递归中运用的
     *
     *  这里的tricky的点在于，题目保证了root是有解的，也就是p 和 q一定是有最小祖先的。
     *  但是root.left   root.right 并不一定有解， 这种没有解的情况，我们返回p 或 q的祖先。
     *
     *  所以这时候function真实的含义是， 如果root 有解， 我们返回 p 和 q 的最小祖先
     *  如果root 没有解， 我们返回 p 或 q 的最小祖先（也就是 p 或 q）本身。
     *
     *  我们扩充了function的含义，相当于利用strong inductive，利用一个更强的命题来证明。
     *  然后利用了题目的限制，我们在求解总问题的时候， 只会限制在返回 p 和 q 的最小祖先
     *  在递归时，却是含义更广的function。
     *
     * 所以这个解法是利用了隐含条件， root有p q 两个节点， 如果没有这个隐含条件，我们在搜索到
     * p 或 q的时候是不能直接放回的， 而是要接着往下搜索， 确定p q 是不是子节点。
     *
     * 而在235里使用的方法，找parent的路径可能是更通用的解法。
     *
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.left, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

}
