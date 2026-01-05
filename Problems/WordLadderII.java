package Problems;

import java.util.*;

public class WordLadderII {

    public static void main(String[] args) {

        String beginWord = "hit";
        String endWord = "cog";

        List<String> wordList = Arrays.asList(
                "hot", "dot", "dog", "lot", "log", "cog"
        );

        Solution solver = new Solution();
        List<List<String>> result = solver.findLadders(beginWord, endWord, wordList);

        System.out.println("All shortest transformation sequences:");
        for (List<String> path : result) {
            System.out.println(path);
        }
    }
}

class Solution {

    public List<List<String>> findLadders(String beginWord,
                                          String endWord,
                                          List<String> wordList) {

        List<List<String>> result = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return result;

        Map<String, List<String>> graph = new HashMap<>();

        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);

        boolean found = false;
        boolean reverse = false;

        while (!beginSet.isEmpty() && !endSet.isEmpty() && !found) {

            // Always expand smaller set
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
                reverse = !reverse;
            }

            dict.removeAll(beginSet);
            Set<String> nextLevel = new HashSet<>();

            for (String word : beginSet) {
                char[] arr = word.toCharArray();

                for (int i = 0; i < arr.length; i++) {
                    char old = arr[i];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == old) continue;
                        arr[i] = c;
                        String next = new String(arr);

                        if (!dict.contains(next)) continue;

                        if (endSet.contains(next)) found = true;
                        nextLevel.add(next);

                        String parent = reverse ? next : word;
                        String child = reverse ? word : next;

                        graph.computeIfAbsent(parent, k -> new ArrayList<>()).add(child);
                    }
                    arr[i] = old;
                }
            }

            beginSet = nextLevel;
        }

        if (!found) return result;

        List<String> path = new ArrayList<>();
        path.add(beginWord);
        dfs(beginWord, endWord, graph, path, result);

        return result;
    }

    private void dfs(String word,
                     String endWord,
                     Map<String, List<String>> graph,
                     List<String> path,
                     List<List<String>> result) {

        if (word.equals(endWord)) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (String next : graph.getOrDefault(word, Collections.emptyList())) {
            path.add(next);
            dfs(next, endWord, graph, path, result);
            path.remove(path.size() - 1);
        }
    }
}
