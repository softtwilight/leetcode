package dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Author:   softtwilight
 * Date:     2021/01/14 21:53
 *
 * https://leetcode.com/problems/maximum-profit-in-job-scheduling/
 */
public class _1235_H_Maximum_Profit_in_Job_Scheduling {
    private static final _1235_H_Maximum_Profit_in_Job_Scheduling instance = new _1235_H_Maximum_Profit_in_Job_Scheduling();

    public static void main(String[] args) {
        int[] startTime = {4,2,4,8,2};
        int[] endTime = {5,5,5,10,8};
        int[] profit = {1,2,8,10,4};
        System.out.println(instance.jobScheduling2(startTime, endTime, profit));
        System.out.println(instance.jobScheduling(startTime, endTime, profit));
    }


    /**
     * more efficient way to dp.
     * need not compare n result, just compare two.
     */
    public int jobScheduling2(int[] startTime, int[] endTime, int[] profit) {
        jobs = new Job[startTime.length];
        for (int i = 0; i < startTime.length; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(jobs, Comparator.comparingInt(j -> j.startAt));
        int[] memo = new int[profit.length];
        return dp2(0, memo);
    }

    private int dp2(int lo, int[] memo) {
        if (lo >= memo.length) return 0;
        if (memo[lo] != 0) return memo[lo];
        int takeLo = jobs[lo].pro + dp2(findNext(lo, jobs[lo].endAt), memo);
        int notTakeLo = dp2(lo + 1, memo);
        memo[lo] = Math.max(takeLo, notTakeLo);
        return memo[lo];
    }

    private int findNext(int lo, int endAt) {
        for (int i = lo + 1; i < jobs.length; i++) {
            if (jobs[i].startAt >= endAt) {
                return i;
            }
        }
        return  jobs.length;
    }


    class Job {
        int startAt;
        int endAt;
        int pro;

        public Job(int startAt, int endAt, int pro) {
            this.startAt = startAt;
            this.endAt = endAt;
            this.pro = pro;
        }
    }

    /**
     * O(n^2) dp
     */
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {

        jobs = new Job[startTime.length];
        for (int i = 0; i < startTime.length; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(jobs, Comparator.comparingInt(j -> j.startAt));
        int[] memo = new int[profit.length];
        return dp(0, memo);
    }



    Job[] jobs;
    private int dp(int lo, int[] memo) {
        if (lo >= memo.length) return 0;
        if (memo[lo] != 0) return memo[lo];
        int endAt = jobs[lo].endAt;
        int newLo = binarySearch(endAt);
        int result = jobs[lo].pro + dp(newLo, memo);
        for (int i = lo + 1; i < newLo; i++) {
            result = Math.max(result, dp(i, memo));
        }
        memo[lo] = result;
        return result;
    }

    private int binarySearch(int endAt) {
        int lo = 0;
        int hi = jobs.length;
        while (lo < hi) {
            int mid = (hi - lo) / 2 + lo;
            if (jobs[mid].startAt < endAt) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }







}
