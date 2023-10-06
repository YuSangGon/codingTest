class Solution {
    public int min = 10;
    public void bfs(String begin, String target, String[] words, int cnt){
        if(cnt > min) return ;
        if(begin.equals(target)) {
            if(cnt < min) {
                min = cnt;
            }
            return ;
        }
        boolean[] visit = new boolean[words.length];
        for(int i = 0; i < words.length; i++) {
            if(visit[i]) continue;

            String word = words[i];
            int c = 0;
            for(int j = 0; j < begin.length(); j++) {
                if(begin.charAt(j) != word.charAt(j)){
                    c++;
                }
                if(c > 1)break;
            }
            if(c == 1) {
                visit = new boolean[words.length];
                visit[i] = true;
                bfs(word, target, words, cnt + 1);
            }
        }
    }

    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        String str = String.join("", words);

        for(char c :  target.toCharArray()) {
            if(!str.contains(String.valueOf(c)))
                return answer;
        }
        bfs(begin, target, words, 0);
        return min;
    }
}