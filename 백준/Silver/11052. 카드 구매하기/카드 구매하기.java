import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] nlist = new int[n+1];
		int[][] dp = new int[n+1][n+1];

		String[] s = br.readLine().split(" ");
		for (int i = 1; i <= n; i++) {
			nlist[i] = Integer.parseInt(s[i-1]);
		}

		int max = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = i; j <= n; j++) {
				if (j % i == 0) {
					dp[i][j] = Math.max(dp[i - 1][j], nlist[i] * (j / i));
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][i] + dp[j - i][j - i]);
				}
			}
			max = Math.max(max, dp[i][n]);
		}
		System.out.println(max);
		br.close();

	}

}
