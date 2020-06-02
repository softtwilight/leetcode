package dfs;

import datastructure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/path-sum-ii/
 *
 * Author:   softtwilight
 * Date:     2020/06/03 0:50
 */
public class _113_M_Path_Sum_II {
    private static final _113_M_Path_Sum_II instance = new _113_M_Path_Sum_II();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 2 ms	42.2 MB
     * 这个要为path 创建太多list， 每一个leaf都会有一个list
     * 实际上不需要这么多list，用一个list来记录path
     * 在list中添加当前节点，然后遍历left, right
     * 然后从list中删掉当前节点，就可以了。
     * 最后在满足情况的条件下再拷贝list，这样只会创建必要的list。
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<Integer> path = new ArrayList<>();
        dfs(root, sum, path);
        return result;
    }

    private void dfs(TreeNode root, int sum, List<Integer> path) {
        if (root == null) return;
        if (root.right == null && root.left == null) {
            if (sum == root.val) {
                result.add(path);
            }
        }
        path.add(root.val);
        dfs(root.left, sum - root.val, new ArrayList<>(path));
        dfs(root.right, sum - root.val, new ArrayList<>(path));
    }
    List<List<Integer>> result = new ArrayList<>();

    public void hasPathSum(TreeNode root, int sum, List<List<Integer>> result, List<Integer> current) {
        if(root == null) return;
        sum -= root.val;
        current.add(root.val);
        if(root.left == null && root.right == null && sum == 0){
            result.add( new ArrayList<>(current));
            // return;
        }
        hasPathSum(root.left, sum, result,current);
        hasPathSum(root.right, sum, result, current);
        current.remove(current.size()-1);
    }

    public List<List<Integer>> pathSum2(TreeNode root, int sum) {

        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        hasPathSum(root, sum, result, new ArrayList<>());
        return result;
    }
}
