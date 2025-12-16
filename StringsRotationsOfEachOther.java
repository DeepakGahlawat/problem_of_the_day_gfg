/*
    ============================================================
            STRINGS ROTATIONS OF EACH OTHER
    ============================================================

    🧠 INTUITION
    ------------------------------------------------------------
    Two strings s1 and s2 are rotations of each other if and only if:
        s2 is a substring of (s1 + s1)

    Example:
        s1 = "ABCD"
        s2 = "CDAB"

        s1 + s1 = "ABCDABCD"
        s2 exists inside it → TRUE

    Instead of using built-in substring search,
    we use the KMP (Knuth–Morris–Pratt) algorithm
    for efficient pattern matching.

    ------------------------------------------------------------
    🧩 APPROACH
    ------------------------------------------------------------
    1️⃣ If lengths are different → NOT rotations
    2️⃣ Concatenate s1 with itself → s = s1 + s1
    3️⃣ Use KMP algorithm to check if s2 exists in s
       - Precompute LPS (Longest Prefix Suffix) array for s2
       - Perform pattern matching on s

    ------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    ------------------------------------------------------------
    Time Complexity:
        • LPS computation: O(m)
        • KMP search: O(n)
        • Overall: O(n + m)

    Space Complexity:
        • LPS array: O(m)

    ------------------------------------------------------------
    ✔ Efficient and optimal solution (better than brute force)
    ============================================================
*/

import java.util.*;

public class StringsRotationsOfEachOther {

    public boolean areRotations(String s1, String s2) {

        // If lengths differ, they cannot be rotations
        if (s1.length() != s2.length()) return false;

        // Concatenate s1 with itself
        String s = s1 + s1;

        // Precompute LPS array for pattern s2
        int[] lps = computeLPS(s2);

        int i = 0; // pointer for s (text)
        int j = 0; // pointer for s2 (pattern)

        int n = s.length();
        int m = s2.length();

        // KMP pattern matching
        while (i < n) {

            if (s.charAt(i) == s2.charAt(j)) {
                i++;
                j++;

                // Full match found
                if (j == m) return true;
            }
            else {
                // Mismatch after some matches
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return false;
    }

    // Builds LPS (Longest Prefix which is also Suffix) array
    int[] computeLPS(String pat) {

        int m = pat.length();
        int[] lps = new int[m];

        int len = 0; // length of previous longest prefix suffix
        int i = 1;

        while (i < m) {

            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else {
                if (len != 0) {
                    len = lps[len - 1];
                }
                else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    // ============================================================
    //                         MAIN METHOD
    // ============================================================
    public static void main(String[] args) {

        StringsRotationsOfEachOther obj = new StringsRotationsOfEachOther();

        String s1 = "ABCD";
        String s2 = "CDAB";

        System.out.println("Are rotations? " + obj.areRotations(s1, s2));
    }
}
