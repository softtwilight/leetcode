package map;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * Given an array of strings, group anagrams together.
 *
 * Example:
 *
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * Note:
 *
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 *
 * Author:   softtwilight
 * Date:     2020/04/24 21:59
 */
public class _49_Group_Anagrams {

    public static void main(String[] args) {
        _49_Group_Anagrams instance = new _49_Group_Anagrams();
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        String[] input2 = {"", ""};
        System.out.println(instance.groupAnagrams(input));
        System.out.println(instance.groupAnagrams(input2));

    }

    /**
     * 这道题思路还是挺简单的，要找相同字符的单词，先对单词排序，结果作为k
     * value保存该排序后序列的单词。（最开始题没有理解对，用set实现的）
     * 结果是6 ms	+ 42.1 MB
     *
     * 还看到一种解法，用一个26位的数组，包含26个质数，然后用char - 'a'
     * 作为下标，将质数乘起来，将一个单词 -> 映射为一个数。
     * 略复杂，有一个不好，只能对应小写字母。而且性能提升不明显。
     *
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] cs = s.toCharArray();
            Arrays.sort(cs);
            String sortedS = new String(cs);
            map.computeIfAbsent(sortedS,  ss -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
