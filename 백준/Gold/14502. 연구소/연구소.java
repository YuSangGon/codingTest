import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int n, m;
	static int max = -1;
	static int[][] virus;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void bfs() {
		int[][] copy = new int[n][m];
		
		for(int i = 0; i   < n; i++)
			copy[i] = virus[i].clone();
		
		Queue<Point> v = new LinkedList<>();
		for(int i = 0; i   < n; i++) {
			for(int j = 0; j < m; j++) {
				if(copy[i][j] == 2)
					v.add(new Point(i, j));
			}
		}
		
		while(!v.isEmpty()) {
			Point a = v.poll();
			
			for(int i = 0; i < 4; i++) {
				int dx = a.x + delta[i][0];
				int dy = a.y + delta[i][1];
				
				if(dx < 0 || dx >= n || dy < 0 || dy >= m) continue;
				
				if(copy[dx][dy] != 1 && copy[dx][dy] != 2) {
					copy[dx][dy] = 2;
					v.add(new Point(dx,dy));
				}
			}
		}
		
		int cnt = 0;
		
		for(int i = 0; i   < n; i++) {
			for(int j = 0; j < m; j++) {
				if(copy[i][j] == 0)
					cnt++;
			}
		}
		
		max = Math.max(cnt, max);
		return;
	}

	static void dfs(int cnt) {
		if (cnt == 3) {
			bfs();
			return;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (virus[i][j] == 0) {
					virus[i][j] = 1;
					dfs(cnt + 1);
					virus[i][j] = 0;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);

		virus = new int[n][m];

		for (int i = 0; i < n; i++) {
			s = br.readLine().split(" ");
			for (int j = 0; j < m; j++)
				virus[i][j] = Integer.parseInt(s[j]);
		}
		dfs(0);
		System.out.println(max);
	}

}
