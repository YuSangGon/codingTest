import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        Set<String> set = new HashSet<>(Arrays.asList(gems));
        int n = set.size();

        int length = Integer.MAX_VALUE, left = 0;

        HashMap<String, Integer> hmap = new HashMap<>();
        for(int right = 0; right < gems.length; right++) {
            hmap.put(gems[right], hmap.getOrDefault(gems[right], 0) + 1);

            while(hmap.get(gems[left]) > 1) {
                hmap.put(gems[left], hmap.get(gems[left]) - 1);
                left++;
            }

            if(hmap.size() == n && length > (right - left)){
                length = right - left;
                answer[0] = left + 1;
                answer[1] = right + 1;
            }
        }

        return answer;
    }
}