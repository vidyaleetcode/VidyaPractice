package com.vidya.amazon;

import java.util.Arrays;

/**
 * 1335. Minimum Difficulty of a Job Schedule
 */
public class MinimumDifficultyOfAJobSchedule {

    public static void main(String[] args) {
        int[] jobDifficulty = {1,1,1};
        int d = 3;

        System.out.println(minDifficulty(jobDifficulty, d));
        System.out.println(minDifficulty1(jobDifficulty, d));
    }

    static int minDifficulty1(int[] jobDifficulty, int d) {

        //Base Cases
        if (jobDifficulty.length < d) {
            return -1;
        }
        int[][] dp = new int[d + 1][jobDifficulty.length];
        //Fill dp Array with -1

        for (int[] p : dp) {
            Arrays.fill(p, -1);
        }

        return dfs1(jobDifficulty, d, dp, 0);
    }

    static int dfs1(int[] jobDifficulty, int d, int[][] dp, int index) {

        //Base Cases
        if (d == 1) {
            int max = 0;
            while (index < jobDifficulty.length) {
                Math.max(max, jobDifficulty[index++]);
            }
            return max;
        }

        //If the value not changed
        if (dp[d][index] != -1) return dp[d][index];

        int max = 0;
        int result = Integer.MAX_VALUE;

        for (int i = index; i < jobDifficulty.length - d + 1; i++) {
            max = Math.max(max, jobDifficulty[i]);
            result = Math.min(result, max + dfs1(jobDifficulty, d - 1, dp, i + 1));
        }

        return dp[d][index] = result;
    }

    static int minDifficulty(int[] jobDifficulty, int d) {

        if(jobDifficulty.length < d){
            return -1;
        }

        int[][] dp = new int[d+1][jobDifficulty.length];

        for(int[] p: dp) Arrays.fill(p,-1);

        return dfs(jobDifficulty, d, dp, 0);
    }

    static int dfs(int[] jobDifficulty, int d, int[][] dp, int idx){

        if(d == 1){
            int max  = 0;
            while(idx < jobDifficulty.length){
                max = Math.max(max, jobDifficulty[idx++]);
            }

            return max;
        }

        if(dp[d][idx] != -1) return dp[d][idx];

        int max = 0;
        int res = Integer.MAX_VALUE;

        for(int i = idx; i < jobDifficulty.length - d + 1; i++){
            max = Math.max(max, jobDifficulty[i]);
            res = Math.min(res, max + dfs(jobDifficulty,d-1,dp,i+1));
        }

        return dp[d][idx] = res;
    }
}
