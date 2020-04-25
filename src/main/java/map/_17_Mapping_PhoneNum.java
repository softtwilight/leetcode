package map;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * Given a string containing digits from 2-9 inclusive, return all possible letter
 * combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Note that 1 does not map to any letters.
 *
 * Example:
 *
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * Author:   softtwilight
 * Date:     2020/04/25 20:54
 */
public class _17_Mapping_PhoneNum {

    /**
     * 这道题目，一个类似笛卡尔积的题，1 ms	38.6 MB
     * 因为下一个字符会依赖当前字符所产生的结果，所以循环每进行一步，就替换一下中间结果就行
     *
     * 还可以做一些优化，但是都不是思路上的，把map换为一个二维数组，应该会更快一些。
     */
    public List<String> letterCombinations(String digits) {

        if (digits == null || digits.length() == 0)
            return new ArrayList<>();

        Map<Character, List<String>> map = new HashMap<>();
        map.put('2', Arrays.asList("a", "b", "c"));
        map.put('3', Arrays.asList("d", "e", "f"));
        map.put('4', Arrays.asList("g", "h", "i"));
        map.put('5', Arrays.asList("j", "k", "l"));
        map.put('6', Arrays.asList("m", "n", "o"));
        map.put('7', Arrays.asList("p", "q", "r", "s"));
        map.put('8', Arrays.asList("t", "u", "v"));
        map.put('9', Arrays.asList("w", "x", "y", "z"));

        List<String> result = Arrays.asList("");
        for (char c : digits.toCharArray()) {
            List<String> mc = map.get(c);
            List<String> nr = new ArrayList<>(result.size() * mc.size());
            for (String s : mc) {
                for (String r : result) {
                    nr.add(r + s);
                }
            }
            result = nr;
        }
        return result;
    }
}
