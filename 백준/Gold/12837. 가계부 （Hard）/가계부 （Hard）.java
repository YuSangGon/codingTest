import java.io.*;
import java.util.*;

public class Main {
    static class SegmentTree {
        private  long[] tree;

        SegmentTree(int n) {
            double treeHeight = Math.ceil(Math.log(n)/Math.log(2))+1;
            long treeNodeCount = Math.round(Math.pow(2, treeHeight));
            tree = new long[Math.toIntExact(treeNodeCount)];
        }

        long init(long[] arr, int node, int start, int end) {
            if(start == end) {
                return tree[node] = arr[start];
            } else {
                return tree[node] = init(arr, node * 2, start, (start+end)/2) +
                        init(arr, node * 2 +1, (start+end)/2+1, end);
            }
        }

        long sum(int node, int start, int end, int left, int right) {
            if(end < left || right < start) {
                return 0;
            } else if(left <= start && end <= right) {
                return tree[node];
            } else {
                return sum(node*2, start, (start+end)/2, left, right)
                        + sum(node*2+1, (start+end)/2+1, end, left, right);
            }
        }

        void add(int node, int start, int end, int index, long changeValue) {
            changeValue += tree[index];
            update(node, start, end, index, changeValue);
        }
        long update(int node, int start, int end, int index, long changeValue) {
            if (index < start || end < index) {
                return tree[node];
            } else if (start == index && end == index) {
                return tree[node] = changeValue;
            } else {
                return tree[node] = update(node * 2, start, (start + end) / 2, index, changeValue) +
                        update(node * 2 + 1, (start + end) / 2 + 1, end, index, changeValue);
            }
        }

        void update1(int node, int start, int end, int index, long diff) {
            if (index < start || end < index) {
                return;
            }else {
                tree[node] = tree[node] + diff;
                if (start != end) {
                    update1(node*2, start, (start+end)/2, index, diff) ;
                    update1(node*2+1, (start+end)/2+1, end, index, diff) ;
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine());
        int day = Integer.parseInt(str.nextToken());
        int query = Integer.parseInt(str.nextToken());

        long[] arr = new long[day+1];

        for(int i = 1; i <= day; i++) {
            arr[i] = 0L;
        }

        SegmentTree st = new SegmentTree(day);
        st.init(arr, 1,1,day);

        for(int i = 0; i < query; i++) {
            str = new StringTokenizer(br.readLine());
            int mode = Integer.parseInt(str.nextToken());

            if(mode == 1) {
                int d = Integer.parseInt(str.nextToken());
                long cost = Long.parseLong(str.nextToken());
                st.update1(1, 1, day, d, cost);
            } else {
                int start = Integer.parseInt(str.nextToken());
                int end = Integer.parseInt(str.nextToken());

                long total = st.sum(1,1,day,start,end);
                bw.write(total + "\n");
                bw.flush();
            }
        }
        bw.close();
        br.close();
    }
}
