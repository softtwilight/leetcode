package array;

/**
 * Author:   softtwilight
 * Date:     2020/10/30 22:28
 */
public class _473_M_Matchsticks_to_Square {
    private static final _473_M_Matchsticks_to_Square instance = new _473_M_Matchsticks_to_Square();

    public static void main(String[] args) {
        int[] input = {5,5,5,5,4,4,4,4,3,3,3,3};
        System.out.println(instance.makesquare(input));
    }

    /**
     *
     */
    public boolean makesquare(int[] nums) {

        if (nums.length == 0) return false;
        int[] dis = new int[16];
        int sum = 0;
        for (int num : nums) {
            sum += num;
            dis[num]++;
        }
        if (sum % 4 != 0) return false;
        int len = sum / 4;

        for (int i = 0; i < 3; i++) {
            int leftLen = len;
            while (leftLen > 0) {
                int j = Math.min(leftLen, 15);
                for (; j >= 1; j--) {
                    if (dis[j] > 0) {
                        leftLen -= j;
                        dis[j]--;
                        break;
                    }
                }
                if (j == 0) return false;
            }
        }
        return true;

    }
}
