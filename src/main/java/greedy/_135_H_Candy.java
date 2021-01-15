package greedy;

import java.util.Arrays;

/**
 * Author:   softtwilight
 * Date:     2021/01/15 22:25
 */
public class _135_H_Candy {
    private static final _135_H_Candy instance = new _135_H_Candy();

    public static void main(String[] args) {
        int[] ratings = {1, 2, 2};
        System.out.println(instance.candy(ratings));
    }

    /**
     * 2282 ms, faster than 5.01%
     *
     * 通过了 但是好慢
     */
    public int candy(int[] ratings) {
        if (ratings.length == 1) return 1;
        int n = ratings.length;
        int[] candies = new int[n];
        boolean[] counted = new boolean[n];
        boolean[] copy = new boolean[n];
        boolean change = true;
        for (int k = 1; change; k++) {
            change = false;
            for (int i = 0; i < ratings.length ; i++) {
                if (counted[i]) continue;
                if (i == 0) {
                    if (counted[i + 1] || ratings[i] <= ratings[i + 1]) {
                        candies[i] = k;
                        copy[i] = true;
                        change = true;
                    }
                } else if (i == ratings.length - 1) {
                    if (counted[i - 1] || ratings[i] <= ratings[i - 1]) {
                        candies[i] = k;
                        copy[i] = true;
                        change = true;
                    }
                } else {
                    if ((counted[i - 1] || ratings[i] <= ratings[i - 1])
                            && (counted[i + 1] || ratings[i] <= ratings[i + 1])) {
                        candies[i] = k;
                        copy[i] = true;
                        change = true;
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                counted[i] = copy[i];
            }
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += candies[i];
        }
        return sum;

    }

}
