import java.io.*;
import java.util.*;

public class Main {
	static class SegmentTree {
		private long[] tree;
		
		SegmentTree(int n){
			double height = Math.ceil(Math.log(n)/Math.log(2))+1;
			long size = Math.round(Math.pow(2, height));
			tree = new long[Math.toIntExact(size)];
		}
		
		long init(long[] arr, int node, int start, int end) {
			if(start == end)
				return tree[node] = arr[start];
			return tree[node] = init(arr, node * 2, start, (start+end)/2)
					+ init(arr, node * 2 + 1, (start+end)/2 + 1, end);
		}
		
		long sum(int node, int start, int end, int left, int right) {
			if(left > end || right < start)
				return 0;
			else if(left <= start && end <= right)
				return tree[node];
			return sum(node*2, start, (start+end)/2, left, right)
					+ sum(node*2+1, (start+end)/2+1, end, left, right);
		}
		
		void update(int index, int node,  int start, int end, long diff) {
			if(index < start || index > end)
				return;
			
			tree[node] += diff;
			
			if(start != end) {
				update(index, node * 2, start, (start+end)/2, diff);
				update(index, node * 2+1, (start+end)/2+1, end, diff);
			}
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer str = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(str.nextToken());
		int m = Integer.parseInt(str.nextToken());
		int k = Integer.parseInt(str.nextToken());
		
		long[] nlist = new long[n+1];
		for(int i = 1; i <= n; i++)
			nlist[i] = Long.parseLong(br.readLine());
		
		SegmentTree st = new SegmentTree(n);
		st.init(nlist, 1, 1, n);
		
		for(int i = 0; i < m + k; i++) {
			str = new StringTokenizer(br.readLine(), " ");
			int j = Integer.parseInt(str.nextToken());
			int x = Integer.parseInt(str.nextToken());
			long y = Long.parseLong(str.nextToken());
			if(j == 1) {
				long diff = y - nlist[x];
				nlist[x] = y;
				st.update(x, 1, 1, n, diff);
			}
			else {
				bw.write(st.sum(1, 1, n, x, Math.toIntExact(y)) + "\n");
			}
		}
		bw.flush();
		br.close();
		bw.close();
	}

}
