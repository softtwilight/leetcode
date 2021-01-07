package binarysearch;

/**
 * Author:   softtwilight
 * Date:     2021/01/07 21:45
 *
 * https://leetcode.com/problems/koko-eating-bananas/
 */
public class _875_M_Koko_Eating_Bananas {
    private static final _875_M_Koko_Eating_Bananas instance = new _875_M_Koko_Eating_Bananas();

    public static void main(String[] args) {
        int[] piles = {312884470};
        int H = 968709470;
        System.out.println(instance.minEatingSpeed(piles, H));
    }

    /**
     *
     */
    public int minEatingSpeed(int[] piles, int H) {
        int max = 0, sum = 0;
        for (int pile : piles) {
            max = Math.max(pile, max);
            sum += pile;
        }

        // sum / H 可能等于0
        int lo = Math.max(1, sum / H), hi = max;
        while (lo < hi) {
            int mid = (hi - lo) / 2 + lo;
            if (eatUp(mid, piles, H)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private boolean eatUp(int speed, int[] piles, int h) {
        for (int pile : piles) {
            h -= (pile - 1) / speed + 1;
            if (h < 0) return false;
        }
        return true;
    }


}
