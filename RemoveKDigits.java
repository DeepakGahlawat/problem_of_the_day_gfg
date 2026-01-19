import java.util.*;

/*
Class Name: Remove K Digits

--------------------------------------------------
Problem Summary:
--------------------------------------------------
You are given a non-negative integer represented as a string `s`
and an integer `k`. You must remove exactly `k` digits from `s`
such that the resulting number is the smallest possible.

Rules:
- Relative order of remaining digits must be preserved.
- The resulting number must not have leading zeros.
- If all digits are removed, return "0".

--------------------------------------------------
Intuition:
--------------------------------------------------
To make the number as small as possible:
- We want smaller digits to appear as early as possible.
- If a digit on the left is larger than a digit on the right,
  removing the left digit gives a smaller number.

This is a classic **monotonic stack (increasing stack)** problem.

--------------------------------------------------
Approach:
--------------------------------------------------
1. Traverse digits from left to right.
2. Maintain a stack that keeps digits in increasing order.
3. If the current digit is smaller than the top of the stack
   and we still can remove digits (k > 0), pop the stack.
4. Push the current digit.
5. If k > 0 after traversal, remove remaining digits from the end.
6. Build the result from the stack.
7. Remove leading zeros.
8. Handle empty result.

--------------------------------------------------
Time Complexity:
- O(n), where n is the length of the string.

Space Complexity:
- O(n), due to stack usage.
--------------------------------------------------
*/

class RemoveKDigits {

    public String removeKdig(String s, int k) {

        Stack<Character> st = new Stack<>();
        int n = s.length();

        // Step 1 & 2: Build monotonic increasing stack
        for (int i = 0; i < n; i++) {
            char curr = s.charAt(i);

            // Step 3: Remove larger digits from stack if possible
            while (!st.isEmpty() && k > 0 && st.peek() > curr) {
                st.pop();
                k--;
            }
            st.push(curr);
        }

        // Step 4: If removals still left, remove from end
        while (k > 0) {
            if (st.isEmpty()) return "0";
            st.pop();
            k--;
        }

        // Step 5: Build result string
        StringBuilder res = new StringBuilder();
        while (!st.isEmpty()) {
            res.append(st.pop());
        }
        res.reverse();

        // Step 6: Remove leading zeros
        int idx = 0;
        while (idx < res.length() && res.charAt(idx) == '0') {
            idx++;
        }

        // Step 7: Handle empty result
        return idx == res.length() ? "0" : res.substring(idx);
    }

    // Main method for testing
    public static void main(String[] args) {
        RemoveKDigits obj = new RemoveKDigits();

        String s1 = "4325043";
        int k1 = 3;
        System.out.println(obj.removeKdig(s1, k1)); // Expected: 2043

        String s2 = "765028321";
        int k2 = 5;
        System.out.println(obj.removeKdig(s2, k2)); // Expected: 221

        String s3 = "10";
        int k3 = 2;
        System.out.println(obj.removeKdig(s3, k3)); // Expected: 0
    }
}
