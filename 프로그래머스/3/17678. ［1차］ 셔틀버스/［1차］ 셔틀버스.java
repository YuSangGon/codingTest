import java.util.*;

class Solution {
    class Person implements Comparable<Person>{
        int h;
        int m;
        
        Person(int h, int m) {
            this.h = h;
            this.m = m;
        }
        
        @Override 
        public int compareTo(Person s){
            if(s.h == h) {
                return m - s.m;
            } else {
                return h - s.h;
            }
        }
    }    
    
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        
        PriorityQueue<Person> plist = new PriorityQueue<>();
        for(String time : timetable) {
            String[] tm = time.split(":");
            plist.add(new Person(Integer.parseInt(tm[0]), Integer.parseInt(tm[1])));
        }
        
        int start_hour = 9, start_min = 0;
        
        for(int i = 0; i < n; i++) {
            int cnt = 0;
            int min = 0;
            int hour = 0;
            while(!plist.isEmpty() && cnt < m) {
                if(plist.peek().h < start_hour){
                    min = plist.peek().m;
                    hour = plist.poll().h;
                    cnt++;
                } else if (plist.peek().h == start_hour && plist.peek().m <= start_min) {
                    min = plist.peek().m;
                    hour = plist.poll().h;
                    cnt++;
                } else {
                    break;
                }
            }
            
            if(i == n - 1) {
                if(cnt == m) {
                    if(min == 0) {
                        start_min = 59;
                        start_hour = hour - 1;
                    } else {
                        start_min = min - 1;
                        start_hour = hour;
                    }
                }
                answer = ((start_hour < 10) ? "0" + Integer.toString(start_hour) : Integer.toString(start_hour)) + ":" + ((start_min < 10) ? "0" + Integer.toString(start_min) : Integer.toString(start_min));
            } else {
                start_min += t;
                if(start_min == 60) {
                    start_hour += 1;
                    start_min = 0;
                }
            }
        }
        
        return answer;
    }
}