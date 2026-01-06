/*
    ============================================================
            MAX XOR SUBARRAY OF SIZE K
    ============================================================

    🧠 INTUITION
    ------------------------------------------------------------
    We need to compute the maximum XOR value among all
    contiguous subarrays of FIXED size k.

    A brute force approach would compute XOR for every
    subarray of size k → O(n * k), which is too slow.

    ------------------------------------------------------------
    🧩 KEY IDEA (SLIDING WINDOW + XOR PROPERTY)
    ------------------------------------------------------------
    XOR has a very useful property:
        a ^ a = 0
        a ^ 0 = a

    This allows us to:
        - Add a new element to window using XOR
        - Remove an element from window using XOR

    Hence, we can maintain a running XOR for the window
    while sliding it across the array.

    ------------------------------------------------------------
    🧮 APPROACH
    ------------------------------------------------------------
    1️⃣ Use two pointers l (left) and r (right)
    2️⃣ Expand window by XOR-ing arr[r]
    3️⃣ If window size exceeds k:
         - Remove arr[l] using XOR
         - Move l forward
    4️⃣ When window size == k:
         - Update maximum XOR
    5️⃣ Continue until r reaches end

    ------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    ------------------------------------------------------------
    Time Complexity:
        O(n)

    Space Complexity:
        O(1)

    ------------------------------------------------------------
    ✔ Efficient sliding window XOR solution
      Common array + bitwise interview problem
    ============================================================
*/

import java.util.*;

public class MaxXorSubarrayOfSizeK {

    public int maxSubarrayXOR(int[] arr, int k) {

        int res = Integer.MIN_VALUE;
        int xor = 0;

        int l = 0;

        // Expand window using r
        for (int r = 0; r < arr.length; r++) {

            // Add new element to window XOR
            xor ^= arr[r];

            // If window size exceeds k, remove left element
            if (r - l + 1 > k) {
                xor ^= arr[l];
                l++;
            }

            // If window size is exactly k, update result
            if (r - l + 1 == k) {
                res = Math.max(res, xor);
            }
        }

        return res;
    }

    // ============================================================
    //                         MAIN METHOD
    // ============================================================
    public static void main(String[] args) {

        MaxXorSubarrayOfSizeK solver =
                new MaxXorSubarrayOfSizeK();

        int[] arr1 = {2, 5, 8, 1, 1, 3};
        System.out.println(
                solver.maxSubarrayXOR(arr1, 3)
        ); // 15

        int[] arr2 = {1, 2, 4, 5, 6};
        System.out.println(
                solver.maxSubarrayXOR(arr2, 2)
        ); // 6
    }
}
