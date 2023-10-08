import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int n, m;
	static int[][] area;
	static int min = Integer.MAX_VALUE;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	static class Point{
		int x;
		int y;
		int d;
		public Point(int x, int y, int d) {
			this.x = x; 
			this.y = y; 
			this.d = d;
		}
	}
	
	static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(0, 0, 1));
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			if(p.x == n-1 && p.y == m-1) {
				min = Math.min(min, p.d);
				continue;
			}
			
			if(area[p.x][p.y] == 0) continue;
			
			for(int i = 0; i < 4; i++) {
				int dx = p.x + delta[i][0];
				int dy = p.y + delta[i][1];
				
				if(dx < 0 || dx >= n || dy < 0 || dy >= m) continue;
				
				if(area[dx][dy] == 1) {
					queue.add(new Point(dx, dy, p.d + 1));
					area[p.x][p.y] = 0;
				}
			}
		}
		return ;
	}
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		area = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			String str = br.readLine();
			for(int j = 0; j < m; j++) {
				area[i][j] = str.charAt(j) - '0';
			}
		}
		
		bfs();
		System.out.println(min);

	}

}
