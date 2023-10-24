class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        
        int n = sequence.length;
        
        long[] dp_pos = new long[n];
        long[] dp_neg = new long[n];
        
        dp_pos[0] = sequence[0];
        dp_neg[0] = -sequence[0];
        
        answer = Math.max(dp_pos[0], dp_neg[0]);
        
        for(int i = 1; i < n; i++) {
            if(i % 2 == 0) {
                dp_pos[i] = Math.max(dp_pos[i-1] + sequence[i], sequence[i]);
                dp_neg[i] = Math.max(dp_neg[i-1] - sequence[i], -sequence[i]);
            } else {
                dp_pos[i] = Math.max(dp_pos[i-1] - sequence[i], -sequence[i]);
                dp_neg[i] = Math.max(dp_neg[i-1] + sequence[i], sequence[i]);
            }
            
            answer = Math.max(answer, Math.max(dp_pos[i], dp_neg[i]));
        }
        
        return answer;
    }
}