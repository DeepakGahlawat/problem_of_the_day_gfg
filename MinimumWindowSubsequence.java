/*
========================================================
Class Name: Minimum Window Subsequence
========================================================

Intuition:
-----------
We want to find the smallest substring of s such that
string t appears as a SUBSEQUENCE inside it.

A subsequence means:
- Characters of t must appear in order
- They do NOT need to be contiguous

Brute Force Idea (Used Here):
-----------------------------
1. Use two pointers (l, r) to generate all possible substrings of s.
2. For each substring s[l..r], check whether t is a subsequence of it.
3. Keep track of the smallest valid substring found.
4. If multiple substrings have the same minimum length, pick the leftmost one.

Why this works:
---------------
- Any valid answer must be a substring of s.
- By checking all substrings and validating subsequence order,
  we ensure correctness.

Time Complexity:
----------------
Let n = length of s
Let m = length of t

- Total substrings ≈ O(n²)
- Checking subsequence takes O(n)

Overall Time Complexity: O(n³)
Space Complexity: O(1)

========================================================
*/

class MinimumWindowSubsequence {

    public String minWindow(String s, String t) {
        int l = 0, r = 0;
        int minLength = Integer.MAX_VALUE;
        int startIndex = -1;
        int n = s.length();

        // Expand window using right pointer
        while (r < n) {

            // Try shrinking from left while substring is valid
            while (isValid(s.substring(l, r + 1), t)) {

                // Update minimum window
                if (r - l + 1 < minLength) {
                    startIndex = l;
                    minLength = r - l + 1;
                }
                l++; // shrink window
            }
            r++; // expand window
        }

        // Return result
        return startIndex == -1 ? "" : s.substring(startIndex, startIndex + minLength);
    }

    // Checks if t is a subsequence of s
    boolean isValid(String s, String t) {
        int i = 0, j = 0;

        while (i < s.length()) {
            if (j == t.length()) return true;
            if (s.charAt(i) == t.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == t.length();
    }

    // Main method for testing
    public static void main(String[] args) {
        MinimumWindowSubsequence sol = new MinimumWindowSubsequence();

        System.out.println(sol.minWindow("geeksforgeeks", "eksrg")); // eksforg
        System.out.println(sol.minWindow("abcdebdde", "bde"));      // bcde
        System.out.println(sol.minWindow("ad", "b"));               // ""
    }
}
