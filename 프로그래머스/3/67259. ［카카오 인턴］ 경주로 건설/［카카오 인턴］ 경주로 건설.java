import java.util.*;

class Solution {
    public class Point {
        int x;
        int y;
        int d;
        int cost;
        Point(int x, int y, int d, int cost) {
            this.x = x;
            this.y = y;
            this.d = d; // 0 : horizontal  1 : vertical
            this.cost = cost;
        }
    }
    public int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;
        int[][][] dp = new int[2][board.length][board.length];

        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < board.length; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        Deque<Point> q = new LinkedList<>();
        q.add(new Point(0, 1, 0, 100));
        q.add(new Point(1, 0, 1, 100));

        while(!q.isEmpty()) {
            Point temp = q.pollFirst();
            int x = temp.x;
            int y = temp.y;
            int d = temp.d;
            int cost = temp.cost;

            if (x == board.length - 1 && y == board.length - 1) {
                if (answer > cost) {
                    answer = cost;
                    continue;
                }
            } else if (x < 0 || x >= board.length || y < 0 || y >= board.length) {
                continue;
            } else if (dp[d][x][y] < cost) {
                continue;
            } else if (board[x][y] == 1) {
                continue;
            }

            dp[d][x][y] = cost;

            if(d == 0) {
                q.add(new Point(x , y + 1, 0, cost + 100));
                q.add(new Point(x , y - 1, 0, cost + 100));
                q.add(new Point(x + 1, y, 1, cost + 600));
                q.add(new Point(x - 1, y, 1, cost + 600));
            } else {
                q.add(new Point(x , y + 1, 0, cost + 600));
                q.add(new Point(x , y - 1, 0, cost + 600));
                q.add(new Point(x + 1, y, 1, cost + 100));
                q.add(new Point(x - 1, y, 1, cost + 100));
            }
        }

        return answer;
    }
}