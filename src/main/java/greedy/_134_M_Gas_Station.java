package greedy;

import java.beans.PropertyEditorSupport;

/**
 * Author:   softtwilight
 * Date:     2021/01/13 21:55
 *
 * https://leetcode.com/problems/gas-station/
 */
public class _134_M_Gas_Station {
    private static final _134_M_Gas_Station instance = new _134_M_Gas_Station();

    public static void main(String[] args) {

        int[] gas = {5, 8, 2, 6}, cost = {6, 5, 6, 4};
        System.out.println(instance.canCompleteCircuit(gas, cost));
    }


    /**
     * 1. If car starts at A and can not reach B. Any station between A and B
     * can not reach B.(B is the first station that A can not reach.)
     *
     * 2. If A can reach B, abilityToReachB(A) >= abilityToReachB(K), A < K < B
     *
     * If the total number of gas is bigger than the total number of cost. There must be a solution.
     */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int sumGas = 0;
        int sumCost = 0;
        int start = 0;
        int tank = 0;
        for (int i = 0; i < gas.length; i++) {
            sumGas += gas[i];
            sumCost += cost[i];
            tank += gas[i] - cost[i];
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }
        if (sumGas < sumCost) {
            return -1;
        } else {
            return start;
        }
    }


    /**
     *  先是暴力求解了， 然后试着改变了遍历的开始坐标， 从最小值之后开始遍历。 居然跑出来很快
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {

        int gasSum = 0, costSum = 0;
        int[] gap = new int[gas.length];
        int min = 0;
        for (int i = 0; i < gas.length; i++) {
            gasSum += gas[i];
            costSum += cost[i];
            gap[i] = gas[i] - cost[i];
            if (gap[i] < gap[min]) {
                min = i;
            }
        }

        if (gasSum < costSum) return -1;
//        int lo = 0, cur = 0, max = 0, start = 0;
        if (gap.length == 1) return 0;
        for (int i = min == gap.length - 1 ? 0 : min + 1; i != min; i = (i + 1) % gap.length) {
            if (gap[i] >= 0) {
                boolean first = true;
                int cur = 0, j;
                for (j = i; first || j != i; j = (j + 1) % gap.length) {
                    cur += gap[j];
                    if (cur < 0) break;
                    first = false;
                }
                if (j == i) return i;
            }

        }
        return -1;
//        for (int i = 0; i < gap.length; i++) {
//            cur += gap[i];
//            if (cur < 0) {
//                lo = i + 1;
//                cur = 0;
//            }
//            if (cur > max) {
//                max = cur;
//                start = lo;
//            }
//        }
//
//        cur = 0;
//        boolean firstTime = true;
//        for (int i = start; firstTime || i != start ; i = (i + 1) % gap.length) {
//            firstTime = false;
//            cur += gap[i];
//            if (cur < 0) {
//                return -1;
//            }
//        }


//        return start;
    }
}
