class Solution {
    
    public long[][] dp;
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;

        dp = new long[n+1][m+1];
        dp[1][1] = 1;

        for(int[] a : puddles)
            dp[a[1]][a[0]] = -1;

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(i == 1 && j == 1) continue;
                if(dp[i][j] == -1) continue;

                if(dp[i][j-1] == -1 && dp[i-1][j] == -1) {
                    dp[i][j] = -1;
                } else if(dp[i][j-1] == -1) {
                    dp[i][j] = dp[i-1][j];
                } else if(dp[i-1][j] == -1) {
                    dp[i][j] = dp[i][j-1];
                } else {
                    dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1_000_000_007;
                }
            }
        }
        answer = Math.toIntExact(dp[n][m]);

        return answer;
    }
}