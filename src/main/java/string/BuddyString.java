package string;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author softtwilight
 * @vesion create on 2018/11/19
 *
 * Given two strings A and B of lowercase letters, return true if
 * and only if we can swap two letters in A so that the result equals B.
 */
public class BuddyString {

    public boolean buddyStrings(String A, String B) {
        int len = A.length();
        if (len != B.length() || len > 20000 || B.length() > 20000) {
            return false;
        }

        char[] as = A.toCharArray();
        char[] bs = B.toCharArray();
        int count = 0;
        char[] cache = new char[6];
        Set set = new LinkedHashSet();
        for (int i = 0; i < len && count < 3; i++) {
            set.add(as[i]);
            if (as[i] != bs[i]) {
                cache[2 * count] = as[i];
                cache[2 * count + 1] = bs[i];
                count ++;
            }
        }
        if (count == 0) {
            return set.size() < len;
        }
        if (count == 2){
            return cache[0] == cache[3] && cache[1] == cache[2];
        }
        return false;
    }


}
