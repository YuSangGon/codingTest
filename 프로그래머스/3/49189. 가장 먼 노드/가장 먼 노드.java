import java.util.*;
import java.util.stream.IntStream;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        ArrayList<Integer>[] list = new ArrayList[n+1];

        for(int[] e : edge) {
            int x = e[0];
            int y = e[1];

            if(list[x] == null) list[x] = new ArrayList<>();
            list[x].add(y);
            if(list[y] == null) list[y] = new ArrayList<>();
            list[y].add(x);
        }

        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visit = new boolean[n+1];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        dist[1] = 0;

        while(!queue.isEmpty()) {
            int q = queue.poll();
            if(visit[q]) continue;

            for(int i = 0; i < list[q].size(); i++){
                if(list[q].size() == 0) continue;
                int num = list[q].get(i);
                dist[num] = Math.min(dist[num], dist[q] + 1);
                queue.add(num);
            }
            list[q].clear();
            visit[q] = true;
        }

        int[] dist_cnt = new int[n+1];
        int max = Arrays.stream(dist, 1, n+1).max().getAsInt();
        answer = Math.toIntExact(Arrays.stream(dist, 1, n+1).filter(i -> i==max).count());
        return answer;
    }
}