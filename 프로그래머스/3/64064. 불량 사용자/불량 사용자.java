import java.util.*;

class Solution {
    public int cnt = 0;
    public Set<String> lst = new HashSet<>();
    public void permutation(String[] user_id,String[] banned_id, int depth, int n, int r) {
        if (depth == r) {
            boolean wrong = false;
            String[] slist = new String[r];
            for(int i = 0; i < r; i++) {
                if(banned_id[i].length() != user_id[i].length()){
                    wrong = true;
                    break;
                }
                for(int j = 0; j < banned_id[i].length(); j++) {
                    if(banned_id[i].charAt(j) == '*')
                        continue;
                    if(banned_id[i].charAt(j) != user_id[i].charAt(j)){
                        wrong = true;
                        break;
                    }
                }
                if(wrong)
                    break;
                else
                    slist[i] = user_id[i];
            }
            if(!wrong) {
                Arrays.sort(slist);
                lst.add(String.join("", slist));
            }
            return;
        }

        for (int i = depth; i < n; i++) {
            swap(user_id, depth, i);
            permutation(user_id, banned_id,depth + 1, n, r);
            swap(user_id, depth, i);
        }
    }

    public void swap(String[] arr, int depth, int i) {
        String temp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = temp;
    }
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        permutation(user_id, banned_id, 0, user_id.length, banned_id.length);
        answer = lst.size();
        return answer;
    }
}