/*
    ============================================================
              MAX SUM SUBARRAY OF SIZE K
    ============================================================

    🧠 INTUITION
    ------------------------------------------------------------
    We need to find the maximum sum of any contiguous subarray
    of fixed size k.

    A brute force approach would calculate the sum of every
    subarray of size k, which would be O(n * k) and too slow
    for large arrays.

    ------------------------------------------------------------
    🧩 KEY IDEA (SLIDING WINDOW)
    ------------------------------------------------------------
    Instead of recalculating the sum every time:
        - Maintain a window of size k
        - Slide it one step to the right
        - Add the new element entering the window
        - Remove the element leaving the window

    This allows us to process the array in ONE PASS.

    ------------------------------------------------------------
    🧮 APPROACH
    ------------------------------------------------------------
    1️⃣ Use two pointers: l (left) and r (right)
    2️⃣ Expand window by moving r and adding arr[r] to sum
    3️⃣ If window size exceeds k:
         - Remove arr[l] from sum
         - Move l forward
    4️⃣ When window size == k:
         - Update maximum sum
    5️⃣ Continue until end of array

    ------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    ------------------------------------------------------------
    Time Complexity:
        O(n)

    Space Complexity:
        O(1)

    ------------------------------------------------------------
    ✔ Classic sliding window problem
      Very common interview question
    ============================================================
*/

import java.util.*;

public class MaxSumSubarrayOfSizeK {

    public int maxSubarraySum(int[] arr, int k) {

        int maxSum = 0;
        int windowSum = 0;
        int l = 0;

        // Expand the window using r
        for (int r = 0; r < arr.length; r++) {

            windowSum += arr[r];

            // If window size exceeds k, shrink from left
            if (r - l + 1 > k) {
                windowSum -= arr[l];
                l++;
            }

            // If window size is exactly k, update max
            if (r - l + 1 == k) {
                maxSum = Math.max(maxSum, windowSum);
            }
        }

        return maxSum;
    }

    // ============================================================
    //                         MAIN METHOD
    // ============================================================
    public static void main(String[] args) {

        MaxSumSubarrayOfSizeK solver =
                new MaxSumSubarrayOfSizeK();

        int[] arr1 = {100, 200, 300, 400};
        System.out.println(
                solver.maxSubarraySum(arr1, 2)
        ); // 700

        int[] arr2 = {1, 4, 2, 10, 23, 3, 1, 0, 20};
        System.out.println(
                solver.maxSubarraySum(arr2, 4)
        ); // 39

        int[] arr3 = {100, 200, 300, 400};
        System.out.println(
                solver.maxSubarraySum(arr3, 1)
        ); // 400
    }
}
