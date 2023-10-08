import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s = br.readLine().split(" ");
		int r = Integer.parseInt(s[0]);
		int c = Integer.parseInt(s[1]);
		int t = Integer.parseInt(s[2]);

		int[][] dust = new int[r][c];
		int[][] origin = new int[r][c];

		int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		int n = 0;
		for (int i = 0; i < r; i++) {
			s = br.readLine().split(" ");
			for (int j = 0; j < c; j++) {
				origin[i][j] = dust[i][j] = Integer.parseInt(s[j]);
			}
			if (origin[i][0] == -1)
				n = i;
		}

		while (t-- > 0) {
			for (int i = 0; i < r; i++)
				for (int j = 0; j < c; j++)
					origin[i][j] = dust[i][j];
			// 확산
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					int v = origin[i][j];
					if (v != 0 && v != -1 && v / 5 > 0) {
						v /= 5;
						int cnt = 0;
						for (int k = 0; k < 4; k++) {
							int di = i + delta[k][0];
							int dj = j + delta[k][1];
							if (di >= 0 && di < r && dj >= 0 && dj < c && dust[di][dj] != -1) {
								dust[di][dj] += v;
								cnt++;
							}
						}
						dust[i][j] = dust[i][j] - v * cnt;
					}
				}
			}
			// 순환
			for (int i = n - 2; i > 0; i--)
				dust[i][0] = dust[i - 1][0];
			for (int i = 0; i < c - 1; i++)
				dust[0][i] = dust[0][i + 1];
			for (int i = 0; i < n - 1; i++)
				dust[i][c - 1] = dust[i + 1][c - 1];
			for (int i = c - 1; i >= 2; i--)
				dust[n - 1][i] = dust[n - 1][i - 1];
			dust[n - 1][1] = 0;

			for (int i = n + 1; i < r - 1; i++)
				dust[i][0] = dust[i + 1][0];
			for (int i = 0; i < c - 1; i++)
				dust[r - 1][i] = dust[r - 1][i + 1];
			for (int i = r - 1; i > n; i--)
				dust[i][c - 1] = dust[i - 1][c - 1];
			for (int i = c - 1; i >= 2; i--)
				dust[n][i] = dust[n][i - 1];
			dust[n][1] = 0;
		}
		int total = 0;
		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				total += dust[i][j];
		System.out.println(total + 2);
	}
}
