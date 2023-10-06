import java.util.*;

class Solution {
    class Point {
        int start;
        int end;
        Point(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public int solution(int[][] routes) {
        int answer = 0;
        Queue<Point> cam = new LinkedList<>();

        ArrayList<Point> routeList = new ArrayList<>();
        for(int[] route : routes) {
            int start = route[0];
            int end = route[1];
            routeList.add(new Point(start, end));
        }

        routeList.sort((o1, o2) -> o1.end - o2.end);

        ArrayList<Point> result = new ArrayList<>();
        int last = -30_001;
        for(int i = 0; i < routeList.size(); i++) {
            if(routeList.get(i).start > last) {
                last = routeList.get(i).end;
                result.add(routeList.get(i));
            }
        }
        answer = result.size();
        return answer;
    }
}