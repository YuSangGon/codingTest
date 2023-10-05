import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        Arrays.sort(works);
        long[] cnt = new long[works[works.length-1] + 1];

        long sum = 0;
        for(int a : works) {
            sum += a;
            cnt[a]++;
        }

        if(sum <= n) return answer;

        for(int i = cnt.length-1; i > 1; i--) {
            if(cnt[i] < n) {
                cnt[i-1] += cnt[i];
                n -= cnt[i];
                cnt[i] = 0;
            } else {
                cnt[i] -= n;
                cnt[i-1] += n;
                n = 0;
                break;
            }
        }
        if(n!=0)
            cnt[1] -= n;

        for(int i = 1; i < cnt.length; i++) {
            if(cnt[i] == 0) continue;
            answer += i * i * cnt[i];
        }

        return answer;
    }
}