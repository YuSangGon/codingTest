class Solution {
    public long solution(int n, int[] times) {
        long min = 1;
        long max = (long)times[0] * n;

        while(min < max) {
            long mid = (min + max) / 2;

            int cnt = 0;
            for(int i : times) {
                cnt += mid / i;
                if(cnt > n) break;
            }

            if(cnt < n) {
                min = mid + 1;
            } else {
                max = mid;
            }

        }

        return min;
    }
}