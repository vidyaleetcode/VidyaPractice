package com.vidya.amazon;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1235. Maximum Profit in Job Scheduling
 *
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
 *
 * You're given the startTime , endTime and profit arrays, you need to output the maximum profit you can take such that there are no 2 jobs in the subset with overlapping time range.
 *
 * If you choose a job that ends at time X you will be able to start another job that starts at time X.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * Output: 120
 * Explanation: The subset chosen is the first and fourth job.
 * Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
 * Example 2:
 *
 *
 *
 *
 * Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 * Output: 150
 * Explanation: The subset chosen is the first, fourth and fifth job.
 * Profit obtained 150 = 20 + 70 + 60.
 * Example 3:
 *
 *
 *
 * Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
 * Output: 6
 *
 *
 * Constraints:
 *
 * 1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
 * 1 <= startTime[i] < endTime[i] <= 10^9
 * 1 <= profit[i] <= 10^4
 */
public class MaximumProfitInJobScheduling {

    public static void main(String[] args) {
        int[] startTime = {1,2,3,3}, endTime = {3,4,5,6}, profit = {50,10,40,70};
        System.out.println(jobScheduling(startTime,endTime,profit));
    }

    static int jobScheduling(int[] startTime, int[] endTime, int[] profit){
        int n = startTime.length;

        //dp[i] holds the profit that we can make upto job i
        int[] dp = new int[n];

        JobSchedule[] jobSchedules = new JobSchedule[n];
        for(int i = 0; i< n;i++){
            jobSchedules[i] = new JobSchedule(startTime[i],endTime[i],profit[i]);
        }

//        Comparator<JobSchedule> comp = (a,b)-> (a.startTime - b.startTime==0)?
//                (a.endTime-b.endTime):(a.startTime-b.startTime);
//        Arrays.sort(jobSchedules,comp);

        Arrays.sort(jobSchedules,(a,b)-> (a.startTime-b.startTime== 0)?(a.endTime-b.endTime):a.startTime-b.startTime);

        dp[n-1] = jobSchedules[n-1].profit;

        for(int i = n-2; i >=0; i--){
            int index = binarySearch(jobSchedules,jobSchedules[i].endTime,i);
            int pro = index == -1?0: dp[index];
            dp[i] = Math.max(dp[i+1], pro + jobSchedules[i].profit);
        }
        return dp[0];
    }

    private static int binarySearch(JobSchedule[] jobSchedules, int val, int start) {

        int end = jobSchedules.length-1;
        int ans = -1;
        while (start<=end){
            int mid = start + (end-start)/2;
            if(jobSchedules[mid].startTime >= val){
                ans = mid;
                end = mid-1;
            }else{
                start= mid+1;
            }
        }
        return ans;
    }


    static int jobScheduling1(int[] startTime, int[] endTime, int[] profit) {
        int l = endTime.length;
        for (int i = 1; i < l; i++) {
            int e = endTime[i], s = startTime[i], p = profit[i], j = i - 1;
            while (j >= 0 && endTime[j] > e) {
                endTime[j + 1] = endTime[j];
                startTime[j + 1] = startTime[j];
                profit[j + 1] = profit[j];
                j--;
            }
            endTime[j + 1] = e;
            startTime[j + 1] = s;
            profit[j + 1] = p;
        }

        int[] dp = new int[l];

        for (int i = 0; i < l; i++) {
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (max == 0)
                    max = dp[j];
                if (endTime[j] <= startTime[i]) {
                    max = Math.max(max, profit[i] + dp[j]);
                    break;
                }
            }
            dp[i] = Math.max(max, profit[i]);
        }
        return dp[l - 1];
    }
}

class JobSchedule{

    int startTime;
    int endTime;
    int profit;

    JobSchedule(int startTime, int endTime, int profit){
        this.startTime = startTime;
        this.endTime = endTime;
        this.profit = profit;
    }
}