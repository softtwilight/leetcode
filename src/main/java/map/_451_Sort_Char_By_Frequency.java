package map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * Given a string, sort it in decreasing order based on the frequency of characters.
 *
 * Example 1:
 *
 * Input:
 * "tree"
 *
 * Output:
 * "eert"
 *
 * Explanation:
 * 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 *
 *
 * Author:   softtwilight
 * Date:     2020/04/26 21:51
 */
public class _451_Sort_Char_By_Frequency {

    public static void main(String[] args) {
        String input = "tree";
        String input2 = "cccaaa";
        String input3 = "Aabb";
        System.out.println(frequencySort2(input));
        System.out.println(frequencySort2(input2));
        System.out.println(frequencySort2(input3));
    }



    /**
     * 这两道题可以分为两步:
     *  1.统计各个字符的频率
     *  2.根据频率依次输出（排序
     * 要解出来还是很简单的 18 ms  +   40.5 MB
     *
     * 看了别的思路，统计字符的优化又用256长度的数组来做的（假设都是ASCII的编码）
     * 降序频率输出的地方，需要排序，利用字符长度又限，且频率是整数，可以利用基数排序来排序。
     */
    public static String frequencySort(String s) {
        if (s.length() <= 1) {
            return s;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        StringBuilder sb = new StringBuilder(s.length());
        map.entrySet().stream()
                    .sorted((e1, e2) -> e2.getValue() - e1.getValue())
                    .forEach(e -> {
                        for (int i = 0; i < e.getValue(); i++) {
                            sb.append(e.getKey());
                        }
                     });
        return sb.toString();
    }
     /**
      * 基数排序版本 9 ms + 40.2 MB
      */
    public static String frequencySort2(String s) {
        if (s.length() <= 1) {
            return s;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        StringBuilder sb = new StringBuilder(s.length());

        List<Character>[] bucket = new List[s.length() + 1];

        for (Map.Entry<Character, Integer> e : map.entrySet()) {
            int pr = e.getValue();
            if (bucket[pr] == null) {
                bucket[pr] = new ArrayList<>();
            }
            bucket[pr].add(e.getKey());
        }
        for (int pos = s.length(); pos >= 0; pos--) {
            if (bucket[pos] != null) {
                for (char c : bucket[pos]) {
                    for (int j = 0; j < pos; j++) {
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }
}
