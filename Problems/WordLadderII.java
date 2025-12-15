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

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        List<List<String>> result = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);

        if (!dict.contains(endWord)) return result;

        Map<String, List<String>> parents = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        boolean found = false;

        while (!queue.isEmpty() && !found) {

            int size = queue.size();
            Set<String> levelVisited = new HashSet<>();

            for (int i = 0; i < size; i++) {

                String word = queue.poll();
                char[] arr = word.toCharArray();

                for (int j = 0; j < arr.length; j++) {
                    char original = arr[j];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) continue;

                        arr[j] = c;
                        String next = new String(arr);

                        if (dict.contains(next)) {

                            parents
                                    .computeIfAbsent(next, k -> new ArrayList<>())
                                    .add(word);

                            if (!levelVisited.contains(next)) {
                                if (next.equals(endWord)) found = true;
                                levelVisited.add(next);
                                queue.offer(next);
                            }
                        }
                    }
                    arr[j] = original;
                }
            }

            // VERY IMPORTANT: remove after finishing the level
            dict.removeAll(levelVisited);
        }

        if (!found) return result;

        List<String> path = new ArrayList<>();
        backtrack(endWord, beginWord, parents, path, result);

        return result;
    }

    private void backtrack(String word, String beginWord,
                           Map<String, List<String>> parents,
                           List<String> path,
                           List<List<String>> result) {

        path.add(word);

        if (word.equals(beginWord)) {
            List<String> temp = new ArrayList<>(path);
            Collections.reverse(temp);
            result.add(temp);
        } else {
            for (String p : parents.getOrDefault(word, Collections.emptyList())) {
                backtrack(p, beginWord, parents, path, result);
            }
        }

        path.remove(path.size() - 1);
    }
}
