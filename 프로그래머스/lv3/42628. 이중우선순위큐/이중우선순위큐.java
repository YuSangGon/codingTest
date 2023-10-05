import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {0, 0};

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        PriorityQueue<Integer> clone = new PriorityQueue<>();

        StringTokenizer str;
        for(String oper : operations) {
            str = new StringTokenizer(oper);

            switch (str.nextToken()) {
                case "I" : {
                    queue.offer(Integer.parseInt(str.nextToken()));
                    break;
                }
                case "D" : {
                    int sort = Integer.parseInt(str.nextToken());
                    if(sort == 1) {
                        if (!queue.isEmpty()) {
                            queue.stream()
                                    .limit(queue.size() - 1)
                                    .forEach(clone::offer);
                            queue.clear();
                            clone.forEach(queue::offer);
                            clone.clear();
                        }
                    }
                    else {
                        if (!queue.isEmpty())
                            queue.poll();
                    }
                    break;
                }
            }
        }

        if(queue.isEmpty())
            return answer;

        answer[1] = queue.peek();
        while(queue.size() != 1)
                queue.poll();
        answer[0] = queue.peek();
        return answer;
    }
}