package top_k_elements;

import javax.swing.*;
import java.util.Arrays;
import java.util.Random;

/**
 * https://leetcode.com/problems/k-closest-points-to-origin/
 *
 * Author:   softtwilight
 * Date:     2020/07/07 21:53
 */
public class _973_M_K_Closest_Points_to_Origin {
    private static final _973_M_K_Closest_Points_to_Origin instance = new _973_M_K_Closest_Points_to_Origin();

    public static void main(String[] args) {
        System.out.println(instance);
    }

    /**
     * 6 ms	115.7 MB
     * 标准的top k解法
     */
    public int[][] kClosest(int[][] points, int K) {

        halfSort(points, 0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    private void halfSort(int[][] points, int lo, int hi, int k) {
        if (lo >= hi) return;
        Random ra = new Random();
        int pivotIndex = lo + ra.nextInt(hi - lo + 1);
        int pi = partition(points, lo, hi, k, pivotIndex);
        if (pi == k) return;
        if (pi > k) {
            halfSort(points, lo, pi - 1, k);
        } else {
            halfSort(points, pi + 1, hi, k);
        }
    }

    private int partition(int[][] points, int lo, int hi, int k, int pivotIndex) {

        int storeIndex = lo;
        swap(points, hi, pivotIndex);
        int square = getSquare(points[hi]);
        for (int i = lo; i < hi - 1; i++) {
            if (getSquare(points[i]) < square) {
                swap(points, i, storeIndex);
                storeIndex++;
            }
        }
        swap(points, hi, storeIndex);
        return storeIndex;


    }

    private int getSquare(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    private void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }
}
