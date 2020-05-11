package slidingwindow;

import java.util.*;

/**
 * You are given a string, s, and a list of words, words, that are all of the same length.
 * Find all starting indices of substring(s) in s that is a concatenation of each word in words
 * exactly once and without any intervening characters.
 *
 *
 *
 * Example 1:
 *
 * Input:
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
 * The output order does not matter, returning [9,0] is fine too.
 *
 * Author:   softtwilight
 * Date:     2020/05/10 22:49
 */
public class _30_H_Substring_with_Concatenation {
    private static final _30_H_Substring_with_Concatenation instance = new _30_H_Substring_with_Concatenation();

    public static void main(String[] args) {
        String input = "barfoothefoobarman";
        String[] words = {"foo","bar"};
        String input2 = "wordgoodgoodgoodbestword";
        String[] words2 = {"word","good","best","good"};

//        System.out.println(instance.findSubstring(input, words));
        System.out.println(instance.findSubstring(input2, words2));

    }




    /**
     * 479 ms	40.3 MB
     * 往前的元素不再检查每一个元素后变成
     * 	96 ms	40.2 MB
     *
     * 	这道问题本身挺复杂的，光实现就废了挺多功夫。没有专注到sliding windows
     */

    Set<Character> chars = new HashSet<>();
    Map<String, Integer> wordsMap = new HashMap<>();
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || words == null) {
            return result;
        }
        int length = 0;
        int wordLength = 0;
        for (String word : words) {
            int times = wordsMap.getOrDefault(word, 0);
            wordsMap.put(word, times + 1);
            wordLength = word.length();
            length += word.length();
            for (char c : word.toCharArray()) {
                chars.add(c);
            }
        }
        for (int i = 0; i < s.length() - length + 1; ) {
            if (chars.contains(s.charAt(i))) {
                i = lookForward(s, i, length, wordLength, result);
            } else {
                i++;
            }
        }
        return result;
    }

    int lookForward(String s, int i, int length, int wordLength, List<Integer> result) {
        Map<String, Integer> map = new HashMap<>();
        int j = i;
        while (j < length + i) {
            String word = s.substring(j, j + wordLength);
            if (!wordsMap.containsKey(word)) {
                return i + 1;
            }
            if (map.containsKey(word)) {
                if (map.get(word) == wordsMap.get(word)) {
                    return i + 1;
                }
            }
            int times = map.getOrDefault(word, 0);
            map.put(word, times + 1);
            j += wordLength;
        }
        result.add(i);
        return i + 1;
    }


    /**
     * 这是参考别人的解答
     * 5 ms	40.1 MB
     * sliding window的优点在于不用再回头检查已经检查过的数据
     * 在第一个解法里，每一次的步长是移动1， 所以其实是在每一点前瞻检查的，所以这里面有很多重复的操作。
     * 可是这个题，马上想到如何构造sliding window是不容易的
     *
     * 这里window 装的是word为单位，所以我们去构建sliding的时候也应该尽量往word上凑
     * 在第一个解法里，是一个字符一个字符的移动的，实际是在每个字符前瞻len * n，不能用
     * 滑动窗口的原因就在于  当前a 可能 不符合， 但是只能跳一格， 因为不确定 b是否符合。
     *
     * 滑动窗口很像是进一些元素，超过了限制条件，然后开始从尾部挤掉一些元素。
     * 连续 + windows的结构，可以在遍历一次选出符合条件的solution。
     * 这个题的window是一个包含频次的map。而单位是word。
     * 我们可以通过 0 ， 0 + len， 0 + 2*len ....
     *            1,  1 + len,  1 + 2*len .....
     *            len-1, 2*len - 1, 3*len-1 ....
     *            这样的方式来遍历数组，就可以构建word来滑动窗口啦。
     */
    public List<Integer> findSubstring2(String s, String[] words) {

        ArrayList<Integer> result = new ArrayList<Integer>();
        if(s==null||s.length()==0||words==null||words.length==0){
            return result;
        }

        //frequency of words
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(String w: words){
            if(map.containsKey(w)){
                map.put(w, map.get(w)+1);
            }else{
                map.put(w, 1);
            }
        }

        int len = words[0].length();

        // {1, 4, 7} {2, 5, 8} {3, 6, 9} 这样去遍历
        // 这样的好处就是我们只用感觉length长度的单词在不在word里
        // 每次的步长变为len， 这样的好处的可以用滑动窗口去移动start
        for(int j=0; j<len; j++){
            HashMap<String, Integer> currentMap = new HashMap<String, Integer>();
            int start = j;//start index of start
            int count = 0;//count totoal qualified words so far

            for(int i=j; i<=s.length()-len; i=i+len){
                String sub = s.substring(i, i+len);
                if(map.containsKey(sub)){
                    //set frequency in current map
                    currentMap.put(sub, currentMap.getOrDefault(sub,0)+1);
                    count++;

                    while(currentMap.get(sub)>map.get(sub)){
                        String left = s.substring(start, start+len);
                        currentMap.put(left, currentMap.get(left)-1);

                        count--;
                        start = start + len;
                    }


                    if(count==words.length){
                        result.add(start); //add to result

                        //shift right and reset currentMap, count & start point
                        String left = s.substring(start, start+len);
                        currentMap.put(left, currentMap.get(left)-1);
                        count--;
                        start = start + len;
                    }
                }else{
                    currentMap.clear();
                    start = i+len;
                    count = 0;
                }
            }
        }

        return result;
    }

}
