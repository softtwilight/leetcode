package array;

/**
 *
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 *
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 *
 * Example:
 *
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 *
 * Author:   softtwilight
 * Date:     2020/04/19 20:35
 */
public class _42_Trapping_Rain_Water {

    public static void main(String[] args) {
        int[] input = {0,1,0,2,1,0,1,3,2,1,2,1};
        _42_Trapping_Rain_Water instance = new _42_Trapping_Rain_Water();
        System.out.println(instance.trap(input));
    }

    /**
     * 这道题的思路是这样的，只有低谷会蓄水，也就是下降然后上升。
     * 我们可以观察到，从左边开始，只有当i是0～i的最高点时，i才可能作为蓄水的左边。
     * 而只要我们依次向右，当出现j， j的高度大于i时， i j 之间就能蓄水了，而蓄水的高度取决于比较低的i点。
     * 这时候j点又作为新的左边最高点，接着往右边找。
     *
     * 但是只找 高度大于leftMax的点其实是不够的，因为我们知道比如 {5, 2, 4}其实也是可以蓄水的，可以蓄2。
     * 但是处理这种情况会很难，你需要继续往前，知道4是其右边最高的点。而且蓄水量的计算也会变得复杂。
     * 从左到右4 < 5, 也意味着从右到左5 > 4。 用一样的逻辑从右边开始遍历，就可以cover住{5, 2, 4}这中情况了。
     * 然后在某一个方向上也包括相等的情况就可以了。
     *
     * 记录以下最高点的位置，第二次遍历的时候，遍历到最高点就可以了。
     *
     * 这个过程还可以优化，因为第一次遍历，在最高点右边的遍历都无效了，在第二次遍历时才发生作用。
     *
     * ps。 最开始没有进行参数判断，错误提交了一次。 always记得检验参数！
     */
    public int trap(int[] height) {

        if (height.length == 0) return 0;
        int leftMax = height[0];
        int result = 0;
        int tempSum = 0;
        int maxIndex = 0;
        for (int i = 1; i <= height.length - 1; i++) {
            if (height[i] >= leftMax) {
                result += tempSum;
                tempSum = 0;
                leftMax = height[i];
                maxIndex = i;
            } else {
                tempSum += leftMax - height[i];
            }
        }

        int rightMax = height[height.length - 1];
        tempSum = 0;
        for (int i = height.length - 2; i >= maxIndex; i--) {
            if (height[i] > rightMax) {
                result += tempSum;
                tempSum = 0;
                rightMax = height[i];
            } else {
                tempSum += rightMax - height[i];
            }
        }
        return result;
    }

    /**
     * 这个地方我们可以用双指针来解决上面第二次重复遍历的问题。
     * 这里似乎有个小窍门，那就是需要二次遍历的时候，能可以尝试多加一个指针的方法来避免。
     *
     * 我们通过上面的分析知道，实际上在最高点的左边，是以leftMax来蓄水，最高点的右边是以rightMax来蓄水。
     * 现在我们通过两个指针，分别从左和右往中间遍历。依次保存leftMax 和 rightMax。
     * 我们判断leftMax和rightMax的大小，比如说leftMax < rightMax, 我们就知道leftMax一定不是最高点
     * 所以用左边来蓄水是安全的，同时移动左边指针，因为left还没有达到最高点，所以应该右移。
     * 同样的rightMax < leftMax, 用右边来蓄水是安全的。
     * 相等的情况，用两边都可以。最终两个指针会定在最高点上。
     *
     * 当然这个优化是理论上的，实际运行的结果没有什么差别。
     *
     */
    public int trap2(int[] height) {

        int start = 0;
        int end = height.length - 1;
        int result = 0;
        int leftMax = 0, rightMax = 0;
        while (start < end) {
            leftMax = Math.max(leftMax, height[start]);
            rightMax = Math.max(rightMax, height[end]);
            if (leftMax < rightMax) {
                result += leftMax - height[start];
                start++;
            } else {
                result += rightMax - height[end];
                end--;
            }
        }
        return result;

    }
}
