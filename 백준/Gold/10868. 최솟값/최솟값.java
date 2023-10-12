import java.io.*;
import java.util.*;

public class Main {
	static class SegmentTree{
		private long[] tree;
		
		SegmentTree(int n){
			double height = Math.ceil(Math.log(n)/Math.log(2))+1;
			long size = Math.round(Math.pow(2, height));
			tree = new long[Math.toIntExact(size)];
		}
		
		long init(long[] arr, int node, int start, int end) {
			if(start == end)
				return tree[node] = arr[start];
			return tree[node] = Math.min(init(arr, node * 2, start, (start+end)/2), 
					init(arr, node*2+1, (start+end)/2+1, end));
		}
		
		long min(int node, int start, int end, int left, int right) {
			if(right < start || left > end)
				return Long.MAX_VALUE;
			else if(left <= start && end <= right)
				return tree[node];
			return Math.min(min(node*2, start, (start+end)/2, left, right), 
					min(node*2+1, (start+end)/2+1, end, left, right));
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer str = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(str.nextToken());
		int m = Integer.parseInt(str.nextToken());
		
		long[] arr = new long[n+1];
		for(int i = 1; i <= n; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		SegmentTree st = new SegmentTree(n);
		st.init(arr, 1, 1, n);
		
		for(int i = 0; i < m; i++) {
			str = new StringTokenizer(br.readLine());
			bw.write(st.min(1, 1, n, Integer.parseInt(str.nextToken()), Integer.parseInt(str.nextToken())) + "\n");
		}
		bw.flush();
		br.close();
		bw.close();
	}

}
