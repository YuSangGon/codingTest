import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int a;
		int b;

		Point(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer str = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(str.nextToken());
		int m = Integer.parseInt(str.nextToken());
		int t = Integer.parseInt(str.nextToken());

		int[][] nlist = new int[n][m];
		int[] head = new int[n];

		for (int i = 0; i < n; i++) {
			str = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++)
				nlist[i][j] = Integer.parseInt(str.nextToken());
		}

		for (int i = 0; i < t; i++) {
			str = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(str.nextToken());
			int d = Integer.parseInt(str.nextToken());
			int k = Integer.parseInt(str.nextToken());
			// 회전
			int idx = d == 0 ? m - k : k;
			for (int j = 0; j < n; j++) {
				if ((j + 1) % x == 0) {
					head[j] = (head[j] + idx) % m;
				}
			}
			// 인접한거 삭제 및 없으면 평균
			Queue<Point> q = new LinkedList<>();
			boolean visit = false;
			for (int j = 0; j < n; j++) {
				int cnt = 0;
				for (int l = head[j]; cnt < m; l = (l + 1) % m, cnt++) {
					if (nlist[j][l] == 0) {
						continue;
					}
					if (nlist[j][l] == nlist[j][(l + 1) % m]) {
						q.add(new Point(j, l));
						visit = !visit ? true : visit;
					} else if (nlist[j][l] == nlist[j][(l - 1) < 0 ? m-1 : l - 1]) {
						q.add(new Point(j, l));
						visit = !visit ? true : visit;
					} else if (j < n - 1
							&& nlist[j + 1][(head[j + 1] + (l - head[j] < 0 ? m + (l - head[j]) : l - head[j]))
									% m] == nlist[j][l]) {
						q.add(new Point(j, l));
						visit = !visit ? true : visit;
					} else if (j > 0 && nlist[j - 1][(head[j - 1] + (l - head[j] < 0 ? m + (l - head[j]) : l - head[j]))
							% m] == nlist[j][l]) {
						q.add(new Point(j, l));
						visit = !visit ? true : visit;
					}
				}
			}

			while (!q.isEmpty()) {
				Point p = q.poll();
				nlist[p.a][p.b] = 0;
			}

			if (!visit) {
				float median = 0;
				for (int j = 0; j < n; j++) {
					median += Arrays.stream(nlist[j]).reduce(0, (a, b) -> a + b);
					for (int l = 0; l < m; l++)
						if (nlist[j][l] != 0)
							q.add(new Point(j, l));
				}
				if(!q.isEmpty())
					median = median / q.size();
				while (!q.isEmpty()) {
					Point p = q.poll();
					if (nlist[p.a][p.b] > median) {
						nlist[p.a][p.b]--;
					} else if (nlist[p.a][p.b] < median){
						nlist[p.a][p.b]++;
					}
				}
			}
		}

		int sum = 0;
		for (int j = 0; j < n; j++)
			sum += Arrays.stream(nlist[j]).reduce(0, (a, b) -> a + b);

		bw.write(sum + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

}
