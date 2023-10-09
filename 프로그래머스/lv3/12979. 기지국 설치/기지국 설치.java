import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int end = 0;
        ArrayList<Integer> area = new ArrayList<>();

        for(int i = 0; i < stations.length; i++) {
            int start = stations[i] - w - 1;
            if(start > end) {
                area.add(start - end);
            }
            end = stations[i] + w;
        }
        if(n - end > 0) {
            area.add(n - end);
        }

        int scope = w * 2 + 1;
        for(int a : area) {
            answer += a / scope;
            if(a % scope != 0)
                answer++;
        }

        return answer;
    }
}