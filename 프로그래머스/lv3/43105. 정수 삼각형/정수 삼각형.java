import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;

        int[][] dp = new int[triangle.length][triangle[triangle.length-1].length];

        dp[0][0] = triangle[0][0];

        for(int i = 1; i < triangle.length; i++) {
            for(int j = 0; j < i + 1; j++) {
                if(j == 0) {
                    dp[i][j] = dp[i-1][j] + triangle[i][j];
                } else if (j == i) {
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                } else {
                    dp[i][j] = triangle[i][j] + Math.max(dp[i-1][j], dp[i-1][j-1]);
                }
            }
        }

        answer = Arrays.stream(dp[dp.length-1]).max().getAsInt();

        return answer;
    }
}