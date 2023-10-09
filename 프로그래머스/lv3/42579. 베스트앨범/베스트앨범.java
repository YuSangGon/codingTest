import java.util.*;
import java.util.stream.Collectors;
class Solution {
    public static  class Music {
        int order;
        int plays;

        Music(int order, int plays) {
            this.order = order;
            this.plays = plays;
        }
    }
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};

        HashMap<String, ArrayList<Music>> orderInGenre = new HashMap<>();
        HashMap<String, Integer> countPlays = new HashMap<>();

        for(int i = 0; i < genres.length; i++) {
            if(countPlays.containsKey(genres[i])){
                countPlays.put(genres[i], countPlays.get(genres[i]) + plays[i]);
                orderInGenre.get(genres[i]).add(new Music(i, plays[i]));
            } else {
                countPlays.put(genres[i], plays[i]);
                orderInGenre.put(genres[i], new ArrayList<>());
                orderInGenre.get(genres[i]).add(new Music(i, plays[i]));
            }
        }

        var entries = countPlays.entrySet().stream().sorted(Map.Entry.comparingByValue(((o1, o2) -> o2-o1))).collect(Collectors.toList());

        ArrayList<Integer> result = new ArrayList<>();
        for(var entry : entries) {
            orderInGenre.get(entry.getKey()).sort((o1, o2) -> o2.plays - o1.plays);
            result.add(orderInGenre.get(entry.getKey()).get(0).order);
            if(orderInGenre.get(entry.getKey()).size() > 1) {
                result.add(orderInGenre.get(entry.getKey()).get(1).order);
            }
        }
        answer = result.stream().mapToInt(Integer::intValue).toArray();

        return answer;
    }
}