import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public class Stone {
        int idx;
        int num;

        Stone(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }
    public int solution(int[] stones, int k) {
        int answer = stones[0];
        Deque<Stone> dq = new LinkedList<>();
        
        dq.add(new Stone(0, stones[0]));
        for(int i = 1; i < stones.length; i++) {
            if(dq.peekLast().num <= stones[i]) {
                if(dq.peekFirst().num <= stones[i]) {
                    dq.clear();
                } else {
                    while(dq.peekLast().num <= stones[i]){
                        dq.pollLast();
                    }
                }
            }
            dq.add(new Stone(i, stones[i]));
            if(dq.peekFirst().idx <= i - k) {
                dq.pollFirst();
            }

            if(i < k)
                answer = Math.max(answer, stones[i]);
            else
                answer = Math.min(answer, dq.peekFirst().num);
        }

        return answer;
    }
}