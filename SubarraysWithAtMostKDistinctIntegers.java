/*
    ==============================================================
      SUBARRAYS WITH AT MOST K DISTINCT INTEGERS
    ==============================================================

    🧠 INTUITION
    --------------------------------------------------------------
    We are asked to count the number of contiguous subarrays
    whose number of DISTINCT elements is AT MOST k.

    Brute force approach would generate all subarrays and
    count distinct elements for each → O(n²), too slow.

    --------------------------------------------------------------
    🧩 OPTIMAL APPROACH (SLIDING WINDOW + HASHMAP)
    --------------------------------------------------------------
    Use a variable-size sliding window:

    - Maintain a window [l, r]
    - Use a HashMap to store frequencies of elements in the window
    - HashMap size = number of distinct elements

    Window rules:
    - Expand r → add arr[r]
    - If distinct count exceeds k → shrink from left (l)
    - At every r, number of valid subarrays ending at r:
          (r - l + 1)

    --------------------------------------------------------------
    🧮 WHY (r - l + 1)?
    --------------------------------------------------------------
    For a fixed r, all subarrays starting from:
        l, l+1, ..., r
    are valid and have at most k distinct elements.

    --------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    --------------------------------------------------------------
    Time Complexity:
        O(n)

    Space Complexity:
        O(k)  (HashMap stores at most k distinct elements)

    --------------------------------------------------------------
    ✔ Classic sliding window problem
    ✔ Frequently asked in interviews
    ============================================================== 
*/

import java.util.*;

public class SubarraysWithAtMostKDistinctIntegers {

    public int countAtMostK(int arr[], int k) {
        return find(arr, k);
    }

    // Helper function to count subarrays with at most k distinct elements
    int find(int[] arr, int k) {

        int l = 0, r = 0;
        int count = 0;
        int n = arr.length;

        HashMap<Integer, Integer> freqMap = new HashMap<>();

        while (r < n) {

            // Add current element to the window
            freqMap.put(arr[r], freqMap.getOrDefault(arr[r], 0) + 1);

            // Shrink window if distinct count exceeds k
            while (freqMap.size() > k) {
                freqMap.put(arr[l], freqMap.get(arr[l]) - 1);
                if (freqMap.get(arr[l]) == 0) {
                    freqMap.remove(arr[l]);
                }
                l++;
            }

            // Count all valid subarrays ending at r
            count += (r - l + 1);
            r++;
        }

        return count;
    }

    // ==============================================================
    //                          MAIN METHOD
    // ==============================================================
    public static void main(String[] args) {

        SubarraysWithAtMostKDistinctIntegers solver =
                new SubarraysWithAtMostKDistinctIntegers();

        int[] arr1 = {1, 2, 2, 3};
        int k1 = 2;
        System.out.println(solver.countAtMostK(arr1, k1));
        // Output: 9

        int[] arr2 = {1, 1, 1};
        int k2 = 1;
        System.out.println(solver.countAtMostK(arr2, k2));
        // Output: 6

        int[] arr3 = {1, 2, 1, 1, 3, 3, 4, 2, 1};
        int k3 = 2;
        System.out.println(solver.countAtMostK(arr3, k3));
        // Output: 24
    }
}
