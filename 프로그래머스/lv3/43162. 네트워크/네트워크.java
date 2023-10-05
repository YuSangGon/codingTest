import java.io.*;
import java.util.*;

class Solution {
    public static int[] parent = new int[200];

    public boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b)
            return false;

        if(a < b)
            parent[b] = a;
        else
            parent[a] = b;

        return true;
    }

    public int find(int a) {
        if(a == parent[a]) return a;
        return find(parent[a]);
    }
    public int solution(int n, int[][] computers) {
        int answer = 0;

        for(int i = 0; i < parent.length; i++)
            parent[i] = i;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) continue;

                if(computers[i][j] == 1){
                    union(i, j);
                }
            }
        }

        ArrayList<Integer> plist = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            int parent = find(i);
            if(!plist.contains(parent)){
                plist.add(parent);
            }
        }
        answer = plist.size();
        return answer;
    }
}