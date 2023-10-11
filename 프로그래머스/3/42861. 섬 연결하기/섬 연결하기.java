import java.util.*;
import java.util.stream.IntStream;

class Solution {
    public int[] parent;
    public class Bridge {
        int x;
        int y;
        int c;

        Bridge(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }
    public boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) return false;

        if(a > b)
            parent[b] = a;
        else
            parent[a] = b;

        return true;
    }

    public int find(int x) {
        if(parent[x] == x) return x;
        return find(parent[x]);
    }
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n];
        IntStream.range(0, n).forEach(i -> parent[i] = i);

        ArrayList<Bridge> blist = new ArrayList<>();
        IntStream.range(0, costs.length)
                .forEach(i -> blist.add(new Bridge(costs[i][0], costs[i][1], costs[i][2])));
        blist.sort(((o1, o2) -> o1.c - o2.c));

        for(Bridge b : blist) {
            if(union(b.x, b.y)) {
                answer += b.c;
            }
        }

        return answer;
    }
}