import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        if(n > s) {
            return new int[] {-1};
        }else if (n == s) {
            Arrays.fill(answer, 1);
        } else {
            Arrays.fill(answer, s / n);
            s = s % n;

            for(int i = n-s; i < n; i++) {
                answer[i]++;
            }
        }
        return answer;
    }
}