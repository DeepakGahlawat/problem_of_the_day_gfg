/*
    ============================================================
              COUNT SUBARRAYS WITH EXACTLY K ODDS
    ============================================================

    🧠 INTUITION
    ------------------------------------------------------------
    Directly counting subarrays with EXACTLY k odd numbers is
    tricky.

    Instead, we use a classic trick:
        subarrays with EXACTLY k odds
            = subarrays with AT MOST k odds
            - subarrays with AT MOST (k - 1) odds

    So the problem reduces to efficiently counting subarrays
    with at most k odd numbers.

    ------------------------------------------------------------
    🧩 KEY IDEA (SLIDING WINDOW – AT MOST K)
    ------------------------------------------------------------
    Use a sliding window:
        - Expand the window using right pointer
        - Count how many odd numbers are in the window
        - If odd count exceeds k, shrink from the left
        - For every valid window ending at r,
          add (r - l + 1) subarrays

    This counts all subarrays ending at r with at most k odds.

    ------------------------------------------------------------
    🧮 APPROACH
    ------------------------------------------------------------
    1️⃣ Define helper function find(arr, k):
         → counts subarrays with at most k odd numbers

    2️⃣ Final Answer:
         find(arr, k) - find(arr, k - 1)

    ------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    ------------------------------------------------------------
    Time Complexity:
        O(n)

    Space Complexity:
        O(1)

    ------------------------------------------------------------
    ✔ Very common interview pattern
      (used in LeetCode "Nice Subarrays")
    ============================================================
*/

public class CountSubarrayWithKOdds {

    public int countSubarrays(int[] arr, int k) {
        // Exactly k odds = atMost(k) - atMost(k-1)
        return find(arr, k) - find(arr, k - 1);
    }

    // Counts subarrays with AT MOST k odd numbers
    int find(int[] arr, int k) {

        int l = 0, r = 0;
        int count = 0;
        int oddCount = 0;
        int n = arr.length;

        while (r < n) {

            // Include current element
            if (arr[r] % 2 == 1) oddCount++;

            // Shrink window if odd count exceeds k
            while (oddCount > k) {
                if (arr[l] % 2 == 1) oddCount--;
                l++;
            }

            // All subarrays ending at r with valid odd count
            count += (r - l + 1);
            r++;
        }

        return count;
    }

    // ============================================================
    //                         MAIN METHOD
    // ============================================================
    public static void main(String[] args) {

        CountSubarrayWithKOdds solver =
                new CountSubarrayWithKOdds();

        int[] arr1 = {2, 5, 6, 9};
        int k1 = 2;
        System.out.println(
                solver.countSubarrays(arr1, k1)
        ); // 2

        int[] arr2 = {2, 2, 5, 6, 9, 2, 11};
        int k2 = 2;
        System.out.println(
                solver.countSubarrays(arr2, k2)
        ); // 8
    }
}
