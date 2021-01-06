package dp;

import java.util.*;

/**
 * Author:   softtwilight
 * Date:     2021/01/06 20:19
 */
public class _472_H_Concatenated_Words {
    private static final _472_H_Concatenated_Words instance = new _472_H_Concatenated_Words();

    public static void main(String[] args) {
        String[] input = {"cat","dog","catdog"};
        System.out.println(instance.findAllConcatenatedWordsInADict(input));
    }

    /**
     *
     */
    public List<String> findAllConcatenatedWordsInADict(String[] words) {

        //sort the array in asc order of word length, since longer words are formed by shorter words.
        Arrays.sort(words, Comparator.comparingInt(String::length));
        List<String> result = new ArrayList<>();
        //list of shorter words
        HashSet<String> preWords = new HashSet<>(10000);
        for(int i=0; i< words.length; i++){
            //Word Break-I problem.
            if(wordBreak(words[i], preWords)) result.add(words[i]);
            preWords.add(words[i]);
        }
        return result;
    }

    // like the question #139
    private boolean wordBreak(String s, HashSet<String> preWords){
        if(preWords.isEmpty()) return false;

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for(int i = 1; i <= s.length(); i++){
            for(int j = 0; j < i; j++){
                if(dp[j] && preWords.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


}
