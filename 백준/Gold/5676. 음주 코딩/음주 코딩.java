import java.util.*;
import java.io.*;

public class Main {
    static class SegmentTree {
        public long[] st;

        SegmentTree(int n){
            double height = Math.ceil(Math.log(n)/Math.log(2))+1;
            long node = Math.round(Math.pow(2, height));
            st = new long[Math.toIntExact(node)];
        }
        long init(long[] arr, int node, int start, int end) {
            if(start == end) {
                return st[node] = (arr[start] > 0) ? 1 : (arr[start] < 0) ? -1 : 0;
            } else {
                return st[node] = init(arr, node*2, start, (start+end)/2) *
                    init(arr, node * 2 + 1, (start + end)/2 + 1, end);
            }
        }

        long mul(int node, int start, int end, int left, int right) {
            if(end < left || right < start) {
                return 1;
            } else if(left <= start && end <= right) {
                return st[node];
            } else {
                return mul(node * 2, start, (start+end)/2, left, right) *
                        mul(node*2 + 1, (start + end)/2+1, end, left, right);
            }
        }

        long update(int node, int start, int end, int index, long changeValue) {
            if(index < start || end < index) {
                return st[node];
            } else if(start == index && end == index) {
                return st[node] = changeValue > 0 ? 1 : changeValue < 0 ? -1 : 0;
            } else {
                return st[node] = update(node * 2, start, (start + end) / 2, index, changeValue) *
                        update(node * 2 + 1, (start + end)/2 + 1, end, index, changeValue);
            }
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer str;
        String s = "";

        while((s = br.readLine()) != null) {
            str = new StringTokenizer(s);
            int n = Integer.parseInt(str.nextToken());
            int m = Integer.parseInt(str.nextToken());

            SegmentTree tree = new SegmentTree(n);
            long[] arr = new long[n+1];

            str = new StringTokenizer(br.readLine());

            for(int i = 1; i <= n; i++) {
                arr[i] = Long.parseLong(str.nextToken());
            }

            tree.init(arr, 1, 1, n);

            for(int i = 0; i < m; i++) {
                str = new StringTokenizer(br.readLine());

                switch (str.nextToken()) {
                    case "C" :
                        tree.update(1, 1, n, Integer.parseInt(str.nextToken()), Integer.parseInt(str.nextToken()));
                        break;
                    case "P" :
                        long ans = tree.mul(1,1,n,Integer.parseInt(str.nextToken()), Integer.parseInt(str.nextToken()));
                        switch ((int)ans) {
                            case 1 : bw.write("+"); break;
                            case 0 : bw.write("0"); break;
                            case -1 : bw.write("-"); break;
                        }
                        break;
                }
            }
            bw.write("\n");
            bw.flush();
        }


        br.close();
        bw.close();
    }
}
