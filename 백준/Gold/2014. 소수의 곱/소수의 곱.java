import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer str = new StringTokenizer(br.readLine(), " ");
		int k = Integer.parseInt(str.nextToken());
		int n = Integer.parseInt(str.nextToken());

		long[] data = new long[k+1];
		PriorityQueue<Long> nlist = new PriorityQueue<>();
		HashSet<Long> set = new HashSet<>();

		str = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= k; i++) {
			long j = Long.parseLong(str.nextToken());
			data[i] = j;
			nlist.add(j);
			set.add(j);
		}
		
		int cnt = 0;
		long max = data[k];
		while(!nlist.isEmpty()) {
			long num = nlist.poll();
			cnt++;
			if(n == cnt) {
				bw.write(num + "\n");
				break;
			}
			
			
			for(int i = 1; i <= k; i++) {
				long next = data[i] * num;
				if(nlist.size() + cnt + 1 > n && max < next) break;
				if(!set.contains(next)) {
					max = Math.max(max, next);
					set.add(next);
					nlist.add(next);
				}
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}
}
