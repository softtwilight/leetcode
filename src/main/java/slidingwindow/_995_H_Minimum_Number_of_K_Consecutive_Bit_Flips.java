package slidingwindow;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Author:   softtwilight
 * Date:     2021/01/16 22:33
 *
 * https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/
 */
public class _995_H_Minimum_Number_of_K_Consecutive_Bit_Flips {
    private static final _995_H_Minimum_Number_of_K_Consecutive_Bit_Flips instance = new _995_H_Minimum_Number_of_K_Consecutive_Bit_Flips();

    public static void main(String[] args) {
        int[] A = {0,1};
        System.out.println(instance.minKBitFlips(A, 2));
    }

    /**
     * pass all case, but time out
     *
     * there are too many unnecessary flip, because in the window, only n % 2 is effect flip
     *
     * so, use some method to track the flip number may work
     */
    public int minKBitFlips2(int[] A, int K) {

        int lo = 0; int hi = K - 1;
        int count = 0;

        // O (n * k)
        while (hi < A.length) {
            if (A[lo] == 0) {
                if (hi + 1 < A.length && A[lo + 1] == 1 && K != 1) {
                        A[hi + 1] = A[hi + 1] == 0 ? 1 : 0;
                        count++;
                } else {
                    //flip
                    for (int i = lo; i <= hi; i++) {
                        A[i] = A[i] == 0 ? 1 : 0;
                    }
                }
                count++;
            }
            lo++;
            hi++;
        }

        for(int i = lo; i < A.length; i++) {
            if (A[i] == 0) return -1;
        }

        return count;
    }


    /**
     * use queue to track the flip number
     * (the size of queue is current flip number of left index of window)
     * (and we offer index of right window, when i >= queue.peek(), pop)
     */
    public int minKBitFlips3(int[] A, int K) {
        Queue<Integer> flips = new ArrayDeque<>();
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == flips.size() % 2) {
                result++;
                flips.add(i + K - 1);
            }

            if (!flips.isEmpty() && i >= flips.peek()) {
                flips.poll();
            }
        }

        return flips.isEmpty() ? result : -1;

    }
    public int minKBitFlips(int[] A, int K) {

        int result = 0, flip = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == flip % 2) {
                if (i > A.length - K) return - 1;
                result++;
                flip++;
                A[i] -= 2;
            }

            //  same as pop element from queue.
            if (i >= K - 1 && A[i - K + 1] < 0) {
                flip--;
                A[i - K + 1] += 2;
            }
        }
        return result;
    }
}
