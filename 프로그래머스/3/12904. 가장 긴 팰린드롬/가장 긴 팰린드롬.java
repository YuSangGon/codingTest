class Solution
{
    public int palindrome(int right, int left,  String s) {
        while(right < s.length() && left >= 0 && s.charAt(right) == s.charAt(left)) {
            right++;
            left--;
        }

        return right - left - 1;
    }
    public int solution(String s)
    {
        int answer = 0;

        for(int i = 0; i < s.length(); i++) {
            answer = Math.max(answer, palindrome(i+1, i-1, s));
            answer = Math.max(answer, palindrome(i+1, i, s));
        }

        return answer;
    }
}