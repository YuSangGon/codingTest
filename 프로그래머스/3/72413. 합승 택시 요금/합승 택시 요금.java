import java.util.*;

class Solution {
    ArrayList<Node>[] graph;
    int[] dist;
    public void Dijkstra(int n, int start) {
        boolean[] check = new boolean[n+1];

        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            int nowVertex = pq.poll().index;

            if(check[nowVertex]) continue;
            check[nowVertex] = true;

            for(Node next : graph[nowVertex]) {
                if(dist[next.index] > dist[nowVertex] + next.cost) {
                    dist[next.index] = dist[nowVertex] + next.cost;
                    pq.offer(new Node(next.index, dist[next.index]));
                }
            }
        }
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        graph = new ArrayList[n+1];
        for(int i = 0; i <=n; i++) graph[i] = new ArrayList<>();

        for(int[] fare : fares) {
            int v = fare[0];
            int w = fare[1];
            int cost = fare[2];

            graph[v].add(new Node(w, cost));
            graph[w].add(new Node(v, cost));
        }

        boolean[] checked = new boolean[n+1];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(s, 0));
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            if(checked[cur.index]) continue;
            checked[cur.index] = true;

            dist = new int[n+1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            Dijkstra(n, cur.index);

            answer = Math.min(dist[s] + dist[a] + dist[b], answer);

            for(Node next : graph[cur.index]) {
                queue.offer(next);
            }

        }

        return answer;
    }
}

class Node implements Comparable<Node>{
    int index;
    int cost;

    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}