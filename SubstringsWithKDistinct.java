/*
    ==============================================================
                SUBSTRINGS WITH K DISTINCT CHARACTERS
    ==============================================================

    🧠 INTUITION
    --------------------------------------------------------------
    We need to count all substrings of a string `s` that contain
    EXACTLY `k` distinct characters.

    Counting "exactly k" directly is difficult.
    Instead, we use a standard trick:

        substrings with exactly k distinct
            = substrings with at most k distinct
            - substrings with at most (k - 1) distinct

    --------------------------------------------------------------
    🧩 KEY IDEA (SLIDING WINDOW + HASHMAP)
    --------------------------------------------------------------
    Use a variable-size sliding window to count substrings with
    AT MOST `k` distinct characters.

    For each right index `r`:
      - Expand the window by including s[r]
      - If distinct characters exceed `k`, shrink from left
      - Number of valid substrings ending at `r` = (r - l + 1)

    --------------------------------------------------------------
    🧮 APPROACH
    --------------------------------------------------------------
    1️⃣ Implement helper function `find(s, k)`:
         → counts substrings with at most k distinct characters

    2️⃣ Final Answer:
         find(s, k) - find(s, k - 1)

    --------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    --------------------------------------------------------------
    Time Complexity:
        O(n), where n = length of string

    Space Complexity:
        O(k), HashMap stores at most k characters

    --------------------------------------------------------------
    ✔ Classic sliding window + frequency map pattern
    ✔ Extremely common interview question
    ============================================================== 
*/

import java.util.*;

public class SubstringsWithKDistinct {

    public int countSubstr(String s, int k) {
        // Exactly k distinct = atMost(k) - atMost(k - 1)
        return find(s, k) - find(s, k - 1);
    }

    // Counts substrings with AT MOST k distinct characters
    int find(String s, int k) {

        int n = s.length();
        int l = 0, r = 0;
        int count = 0;

        HashMap<Character, Integer> freqMap = new HashMap<>();

        while (r < n) {

            // Add current character to window
            char ch = s.charAt(r);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);

            // Shrink window if distinct count exceeds k
            while (freqMap.size() > k) {
                char leftChar = s.charAt(l);
                freqMap.put(leftChar, freqMap.get(leftChar) - 1);
                if (freqMap.get(leftChar) == 0) {
                    freqMap.remove(leftChar);
                }
                l++;
            }

            // All substrings ending at r are valid
            count += (r - l + 1);
            r++;
        }

        return count;
    }

    // ==============================================================
    //                          MAIN METHOD
    // ==============================================================
    public static void main(String[] args) {

        SubstringsWithKDistinct solver =
                new SubstringsWithKDistinct();

        System.out.println(
                solver.countSubstr("abc", 2)
        ); // Output: 2

        System.out.println(
                solver.countSubstr("aba", 2)
        ); // Output: 3

        System.out.println(
                solver.countSubstr("aa", 1)
        ); // Output: 3
    }
}
