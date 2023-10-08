import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);

		s = br.readLine().split(" ");
		int r = Integer.parseInt(s[0]);
		int c = Integer.parseInt(s[1]);
		int d = Integer.parseInt(s[2]);

		int[][] nlist = new int[n][m];
		int[][] slist = new int[n][m];

		for (int i = 0; i < n; i++) {
			s = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				nlist[i][j] = Integer.parseInt(s[j]);
			}
		}

		int cnt = 0;
		boolean clear = false;
		while (!clear) {
			if (slist[r][c] != 1) {
				slist[r][c] = 1;
				cnt++;
			}

			boolean find = false;

			for (int i = 0; i < 4; i++) {
				d = d - 1 < 0 ? 3 : d - 1;
				if (d == 0) {
					if(slist[r-1][c] == 0 && nlist[r-1][c] != 1) {
						find = true;
						r--;
						break;
					}
				} else if (d == 1) {
					if(slist[r][c+1] == 0 && nlist[r][c+1] != 1) {
						find = true;
						c++;
						break;
					}
				} else if (d == 2) {
					if(slist[r+1][c] == 0 && nlist[r+1][c] != 1) {
						find = true;
						r++;
						break;
					}
				} else if (d == 3) {
					if(slist[r][c-1] == 0 && nlist[r][c-1] != 1) {
						find = true;
						c--;
						break;
					}
				}
			}

			if (!find) {
				if (d == 0) {
					r++;
				} else if (d == 1) {
					c--;
				} else if (d == 2) {
					r--;
				} else if (d == 3) {
					c++;
				}
				
				if(nlist[r][c] == 1) {
					clear = true;
				}
			}

		}
		System.out.println(cnt);
	}

}
