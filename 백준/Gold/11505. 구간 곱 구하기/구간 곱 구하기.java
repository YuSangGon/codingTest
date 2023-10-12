import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static class SegmentTree{
		private long[] tree;
		
		SegmentTree(int n){
			double height = Math.ceil(Math.log(n)/Math.log(2))+1;
			long size = Math.round(Math.pow(2, height));
			tree = new long[Math.toIntExact(size)];
		}
		
		long init(long[] arr, int node, int start, int end) {
			if(start == end) {
				return tree[node] = arr[start];
			}
			return tree[node] = (init(arr, node*2, start, (start+end)/2) * 
					init(arr, node*2+1, (start+end)/2+1, end)) % 1000000007;
		}
		
		long mul(int node, int start, int end, int left, int right) {
			if(left > end || right < start)
				return 1;
			else if(left <= start && end <= right) {
				return tree[node];
			}
			return (mul(node*2, start, (start+end)/2, left, right) * 
					mul(node*2+1, (start+end)/2+1, end, left, right)) % 1000000007;
		}
		
		long update(int node, int start, int end, int index, long change) {
			 if(index < start || index > end) {
				 return tree[node];
			 } else if(start == index && end == index) {
				 return tree[node] = change;
			 } 
			 return tree[node] = (update(node*2, start, (start+end)/2, index, change) *
					 update(node*2+1, (start+end)/2+1, end, index, change)) % 1000000007;
		}
	}

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer str = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(str.nextToken());
		int m = Integer.parseInt(str.nextToken());
		int k = Integer.parseInt(str.nextToken());
		
		long[] arr= new long[n+1];
		for(int i = 1; i <= n; i ++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		SegmentTree tree= new SegmentTree(n);
		tree.init(arr, 1, 1, n);
		
		for(int i = 0; i < m + k; i++) {
			str = new StringTokenizer(br.readLine(), " ");
			int t = Integer.parseInt(str.nextToken());
			int idx = Integer.parseInt(str.nextToken());
			int v = Integer.parseInt(str.nextToken());
			if(t == 1) {
				tree.update(1, 1, n, idx, v);
			} else {
				bw.write(tree.mul(1, 1, n, idx, v) + "\n");
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
