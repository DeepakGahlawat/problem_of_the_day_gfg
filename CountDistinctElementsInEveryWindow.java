/*
    ==============================================================
        COUNT DISTINCT ELEMENTS IN EVERY WINDOW
    ==============================================================

    🧠 INTUITION
    --------------------------------------------------------------
    For every contiguous subarray (window) of size k, we need to
    count how many DISTINCT elements it contains.

    A brute-force solution would check every window separately,
    but that would be too slow for large inputs.

    --------------------------------------------------------------
    🧩 OPTIMAL APPROACH (SLIDING WINDOW + HASHMAP)
    --------------------------------------------------------------
    We maintain a sliding window of size k using two pointers.

    - Use a HashMap to store frequency of elements in the window
    - HashMap size = number of distinct elements
    - Slide window one step at a time:
        • Add new element (right pointer)
        • Remove outgoing element (left pointer)
        • Update frequencies accordingly

    --------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    --------------------------------------------------------------
    Time Complexity:
        O(n), where n is the array length

    Space Complexity:
        O(k), HashMap stores at most k elements

    --------------------------------------------------------------
    ✔ This is a classic sliding window problem
    ✔ Very common in interviews (Amazon, Google, Flipkart)
    ============================================================== 
*/

import java.util.*;

public class CountDistinctElementsInEveryWindow {

    public ArrayList<Integer> countDistinct(int arr[], int k) {

        HashMap<Integer, Integer> freqMap = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();

        int l = 0; // left pointer

        // Traverse array using right pointer
        for (int r = 0; r < arr.length; r++) {

            // Add current element to map
            freqMap.put(arr[r], freqMap.getOrDefault(arr[r], 0) + 1);

            // If window size exceeds k, remove left element
            if (r - l + 1 > k) {
                freqMap.put(arr[l], freqMap.get(arr[l]) - 1);

                // Remove element completely if frequency becomes 0
                if (freqMap.get(arr[l]) == 0) {
                    freqMap.remove(arr[l]);
                }
                l++;
            }

            // When window size is exactly k, record distinct count
            if (r - l + 1 == k) {
                result.add(freqMap.size());
            }
        }

        return result;
    }

    // ==============================================================
    //                          MAIN METHOD
    // ==============================================================
    public static void main(String[] args) {

        CountDistinctElementsInEveryWindow solver =
                new CountDistinctElementsInEveryWindow();

        int[] arr1 = {1, 2, 1, 3, 4, 2, 3};
        int k1 = 4;
        System.out.println(solver.countDistinct(arr1, k1));
        // Output: [3, 4, 4, 3]

        int[] arr2 = {4, 1, 1};
        int k2 = 2;
        System.out.println(solver.countDistinct(arr2, k2));
        // Output: [2, 1]

        int[] arr3 = {1, 1, 1, 1, 1};
        int k3 = 3;
        System.out.println(solver.countDistinct(arr3, k3));
        // Output: [1, 1, 1]
    }
}
