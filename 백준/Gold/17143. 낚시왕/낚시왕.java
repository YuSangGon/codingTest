import java.io.*;
import java.util.*;

public class Main {
	static class Shark {
		int s;
		int d;
		int z;

		Shark(int s, int d, int z) {
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}

	public static void main(String[] args) throws Exception {
// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer str = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(str.nextToken());
		int c = Integer.parseInt(str.nextToken());
		int m = Integer.parseInt(str.nextToken());

		HashMap<String, List<Shark>> map = new HashMap<>();
		for (int i = 0; i < m; i++) {
			str = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(str.nextToken());
			int y = Integer.parseInt(str.nextToken());
			int s = Integer.parseInt(str.nextToken());
			int d = Integer.parseInt(str.nextToken());
			int z = Integer.parseInt(str.nextToken());
			String st = Integer.toString(x - 1) + " " + Integer.toString(y - 1);
			List<Shark> l = new ArrayList<>();
			l.add(new Shark(s, d, z));
			map.put(st, l);
		}

		int total = 0;
		for (int t = 0; t < c; t++) {
			int shark_x = r;
			int shark_y = t;
			boolean fish = false;
			for (String key : map.keySet()) {
				str = new StringTokenizer(key, " ");
				int x = Integer.parseInt(str.nextToken());
				int y = Integer.parseInt(str.nextToken());

				if (y == t) {
					shark_x = Math.min(shark_x, x);
					fish = true;
				}
			}

			if (fish) {
				String xy = Integer.toString(shark_x) + " " + Integer.toString(shark_y);
				total += map.get(xy).get(0).z;
				map.remove(xy);
			}
			

			HashMap<String, List<Shark>> move = new HashMap<>();
			for (String key : map.keySet()) {
				str = new StringTokenizer(key, " ");
				int x = Integer.parseInt(str.nextToken());
				int y = Integer.parseInt(str.nextToken());
				Shark k = map.get(key).get(0);
				if(k.d <= 2) {
					k.s %= (r - 1) * 2;
				} else {
					k.s %= (c - 1) * 2;
				}
				for (int i = 0; i < k.s; i++) {
					if (k.d == 1) {
						if (x - 1 < 0) {
							x++;
							k.d = 2;
						} else
							x--;
					} else if (k.d == 2) {
						if (x + 1 >= r) {
							x--;
							k.d = 1;
						} else
							x++;
					} else if (k.d == 3) {
						if (y + 1 >= c) {
							y--;
							k.d = 4;
						} else
							y++;
					} else if (k.d == 4) {
						if (y - 1 < 0) {
							y++;
							k.d = 3;
						} else
							y--;
					}
				}
				String st = Integer.toString(x) + " " + Integer.toString(y);
				if (move.containsKey(st)) {
					move.get(st).addAll(map.get(key));
				} else {
					move.put(st, map.get(key));
				}
			}

			for (String key : move.keySet()) {
				while (move.get(key).size() > 1) {
					Shark a = move.get(key).get(0);
					Shark b = move.get(key).get(1);
					if (a.z < b.z)
						move.get(key).remove(0);
					else
						move.get(key).remove(1);
				}
			}
			map.clear();
			map = move;
		}

		bw.write(total + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}