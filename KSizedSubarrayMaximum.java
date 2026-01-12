/*
========================================================
Class Name: K Sized Subarray Maximum
========================================================

Intuition:
-----------
We need to find the maximum element in every contiguous
subarray (window) of size k.

A brute-force approach would take O(n * k), which is too slow
for large inputs.

Optimal Idea (Sliding Window + Deque):
--------------------------------------
We use a Deque (double-ended queue) to store indices of elements.

Key properties of the deque:
1. Indices are stored in decreasing order of values.
   → The front of the deque always holds the index of the maximum element.
2. Remove elements from the front if they are outside the current window.
3. Remove elements from the back if they are smaller than the current element
   (they can never be the maximum again).

This ensures:
- Each element is added and removed at most once.
- Overall time complexity is linear.

Approach:
---------
1. Traverse the array index by index.
2. Maintain the deque to keep potential maximum candidates.
3. Once we reach index >= k - 1, record the maximum for that window.

Time Complexity:
----------------
O(n) — each element enters and leaves the deque once.

Space Complexity:
-----------------
O(k) — deque stores at most k elements.

========================================================
*/

import java.util.*;

class KSizedSubarrayMaximum {

    public ArrayList<Integer> maxOfSubarrays(int[] arr, int k) {
        Deque<Integer> dq = new ArrayDeque<>();
        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {

            // Remove smaller elements from the back
            while (!dq.isEmpty() && arr[dq.peekLast()] <= arr[i]) {
                dq.pollLast();
            }

            // Remove elements that are out of the current window
            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            // Add current index
            dq.addLast(i);

            // Add maximum for the window
            if (i >= k - 1) {
                ans.add(arr[dq.peekFirst()]);
            }
        }
        return ans;
    }

    // Main method for testing
    public static void main(String[] args) {
        KSizedSubarrayMaximum sol = new KSizedSubarrayMaximum();

        int[] arr1 = {1, 2, 3, 1, 4, 5, 2, 3, 6};
        int k1 = 3;
        System.out.println(sol.maxOfSubarrays(arr1, k1)); // [3, 3, 4, 5, 5, 5, 6]

        int[] arr2 = {5, 1, 3, 4, 2};
        int k2 = 1;
        System.out.println(sol.maxOfSubarrays(arr2, k2)); // [5, 1, 3, 4, 2]
    }
}
