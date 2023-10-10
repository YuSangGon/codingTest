import java.util.stream.IntStream;

class Solution {
    public int solution(int sticker[]) {
        int n = sticker.length;
        if(n == 1) return sticker[0];
        else if(n == 2) return Math.max(sticker[0], sticker[1]);

        int[] tmp1 = new int[n];
        int[] tmp2 = new int[n];
        IntStream.range(0, n).forEach(i -> tmp1[i] = sticker[i]);
        IntStream.range(0, n-1).forEach(i -> tmp2[i] = sticker[i+1]);
        tmp1[n-1] = 0;
        tmp2[n-1] = 0;


        int[] dp1 = new int[n];  //스티커를 선택
        int[] dp2 = new int[n];  //스티커를 선탟하지 않음

        dp1[0] =tmp1[0];
        dp1[1] = Math.max(dp1[0], tmp1[1]);
        IntStream.range(2, n).forEach(i -> {
            dp1[i] = Math.max(dp1[i-2] + tmp1[i], dp1[i-1]);
        });
        dp2[0] =tmp2[0];
        dp2[1] = Math.max(dp2[0], tmp2[1]);
        IntStream.range(2, n).forEach(i -> {
            dp2[i] = Math.max(dp2[i-2] + tmp2[i], dp2[i-1]);
        });

        return Math.max(dp1[n-1], dp2[n-1]);
    }
}