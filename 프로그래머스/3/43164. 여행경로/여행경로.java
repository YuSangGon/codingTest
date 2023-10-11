import java.util.*;
import java.util.stream.IntStream;
class Solution {
    int cnt = 0;
    List<Integer> list1 = new ArrayList<>();
    public String[] solution(String[][] tickets) {
        Map<String, Integer> indexing = new HashMap<>();
        List<String> tlist = new ArrayList<>();

        for(String[] ticket : tickets) {
            for(String t : ticket) {
                if(!tlist.contains(t))
                    tlist.add(t);
            }
        }
        cnt = tickets.length;
        tlist.sort(Comparator.naturalOrder());

        IntStream.range(0, tlist.size()).forEach(i -> {
            indexing.put(tlist.get(i), i);
        });

        int[][] arr = new int[tlist.size()][tlist.size()];
        IntStream.range(0, tickets.length).forEach(i -> {
            arr[indexing.get(tickets[i][0])][indexing.get(tickets[i][1])]++;
        });

        int start = indexing.get("ICN");
        list1.add(start);
        dfs(arr, start, 0);

        List<String> rst = new ArrayList<>();
        IntStream.range(0, list2.size())
                .forEach(i -> {
                    indexing.keySet().stream().forEach(key -> {
                        if(indexing.get(key) == list2.get(i)){
                            rst.add(key);
                        }
                    });
                });

        return rst.toArray(new String[0]);
    }
    List<Integer> list2 = new ArrayList<>();
    public void dfs(int[][] arr, int start, int count) {
        if(count == cnt && list2.size() == 0) {
            list2.addAll(list1);
            return;
        }

        for(int i = 0; i < arr[start].length; i++){
            if(arr[start][i] >= 1) {
                arr[start][i]--;
                List<Integer> l = new ArrayList<>(list1);
                list1.add(i);
                dfs(arr, i, count+1);
                arr[start][i]++;
                list1 = new ArrayList<>(l);
            }
        }
    }
}