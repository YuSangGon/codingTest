import java.util.*;
import java.util.stream.IntStream;

class Solution {
    class Job {
        int start;
        int cost;

        Job(int start, int cost) {
            this.start = start;
            this.cost = cost;
        }
    }

    public int solution(int[][] jobs) {
        int answer = 0;

        PriorityQueue<Job> heap = new PriorityQueue<>((o1, o2) -> {
            if(o1.start != o2.start)
                return o1.start - o2.start;
            else
                return o1.cost - o2.cost;
        });
        IntStream.range(0, jobs.length)
                .forEach(i -> heap.add(new Job(jobs[i][0], jobs[i][1])));
        PriorityQueue<Job> wlist = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

        int end = 0;

        while(true) {
            Job j = null;
            if(!wlist.isEmpty()) {
                j = wlist.poll();
            } else {
                if(heap.isEmpty()) break;
                j = heap.poll();
                end = j.start;
            }

            end += j.cost;
            answer += end - j.start;

            while(!heap.isEmpty() && heap.peek().start <= end){
                wlist.add(heap.poll());
            }
        }

        return answer/ jobs.length;
    }
}