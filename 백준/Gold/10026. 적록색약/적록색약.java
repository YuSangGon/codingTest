import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int n;
	static char[][] color;
	static char[][] abnor;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
	static int cnt = 0;
	
	public static class Point{
		int x;
		int y;
		char c;
		public Point(int x, int y, char c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		color = new char[n][n];
		abnor = new char[n][n];
		
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0; j < n; j++) {
				color[i][j] = s.charAt(j);
				if(color[i][j] == 'G')
					abnor[i][j] = 'R';
				else
					abnor[i][j] = color[i][j];
			}
		}
		
		int[][] mark = new int[n][n];
		
		Queue<Point> q = new LinkedList<>();
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(mark[i][j] == 0) {
					q.add(new Point(i, j, color[i][j]));
					cnt++;
				}
				
				while(!q.isEmpty()) {
					Point p = q.poll();
					mark[p.x][p.y] = 1;
					
					for(int k = 0; k < 4; k++) {
						int dx = p.x + delta[k][0];
						int dy = p.y + delta[k][1];
						if(dx < 0 || dx >= n || dy < 0 || dy >= n) continue;
						
						if(color[dx][dy] == p.c && mark[dx][dy] != 1) {
							q.add(new Point(dx, dy, p.c));
							mark[dx][dy] = 1;
						}
					}
				}
			}
		}
		
		System.out.print(cnt + " ");
		cnt = 0;
		
		for(int i = 0; i < n; i++)
			Arrays.fill(mark[i], 0);
		
		q.clear();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(mark[i][j] == 0) {
					q.add(new Point(i, j, abnor[i][j]));
					cnt++;
				}
				
				while(!q.isEmpty()) {
					Point p = q.poll();
					mark[p.x][p.y] = 1;
					
					for(int k = 0; k < 4; k++) {
						int dx = p.x + delta[k][0];
						int dy = p.y + delta[k][1];
						if(dx < 0 || dx >= n || dy < 0 || dy >= n) continue;
						
						if(abnor[dx][dy] == p.c && mark[dx][dy] != 1) {
							q.add(new Point(dx, dy, p.c));
							mark[dx][dy] = 1;
						}
					}
				}
			}
		}
		System.out.println(cnt);
		
	}

}
