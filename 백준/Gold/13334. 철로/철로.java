import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Station[] stations = new Station[n];

        int start, end;

        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            stations[i] = new Station(start, end);
        }

        int length = Integer.parseInt(br.readLine());

        Arrays.sort(stations, new Comparator<Station>() {
            @Override
            public int compare(Station arg0, Station arg1) {
                return arg0.getEnd() > arg1.getEnd() ? 1 : (arg0.getEnd() == arg1.getEnd() ? 0 : -1);
            }});

        int maxCount = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>(n+1);

        int railStart = 0;

        for(int i=0; i<n; i++) {
            railStart = stations[i].getEnd() - length;

            while(!queue.isEmpty() && queue.peek() < railStart)
                queue.poll();

            if(stations[i].getStart() >= railStart)
                queue.add(stations[i].getStart());

            maxCount = Math.max(maxCount, queue.size());
        }

        System.out.println(maxCount);

        br.close();
    }
}

class Station {
    private int start;
    private int end;

    public Station(int start, int end) {

        if(start > end) {
            int temp = end;
            end = start;
            start = temp;
        }

        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}