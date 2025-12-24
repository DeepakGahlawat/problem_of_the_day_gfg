/*
    ============================================================
                        ELEMENTS IN RANGE
    ============================================================

    🧠 INTUITION
    ------------------------------------------------------------
    We are given:
        - An UNSORTED array arr[]
        - Multiple queries of the form [a, b]

    For each query, we need to count how many elements
    in arr[] lie in the range:
        a ≤ x ≤ b

    Brute force for each query would be too slow.
    So we optimize using sorting + binary search.

    ------------------------------------------------------------
    🧩 KEY OBSERVATION
    ------------------------------------------------------------
    Once the array is SORTED:
        - All elements in range [a, b] form a CONTIGUOUS block
        - Count = index of first element > b
                  − index of first element ≥ a

    This can be efficiently computed using:
        lowerBound(a) and upperBound(b)

    ------------------------------------------------------------
    🧮 APPROACH
    ------------------------------------------------------------
    1️⃣ Sort the array arr[]
    2️⃣ For each query [a, b]:
         - Find lowerBound(a)
         - Find upperBound(b)
         - Answer = upperBound(b) − lowerBound(a)
    3️⃣ Store results for all queries

    ------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    ------------------------------------------------------------
    Time Complexity:
        O(n log n + q log n)
        where n = size of array, q = number of queries

    Space Complexity:
        O(1) extra (excluding output list)

    ------------------------------------------------------------
    ✔ Efficient range counting using binary search
      Very common GFG / interview problem
    ============================================================
*/

import java.util.*;



public class ElementsInRange {

    public ArrayList<Integer> cntInRange(int[] arr, int[][] queries) {

        // Step 1: Sort the array
        Arrays.sort(arr);

        ArrayList<Integer> ans = new ArrayList<>();

        // Step 2: Process each query
        for (int[] query : queries) {

            int a = query[0];
            int b = query[1];

            // Find bounds
            int left = lowerBound(arr, a);
            int right = upperBound(arr, b);

            // Number of elements in [a, b]
            ans.add(right - left);
        }

        return ans;
    }

    // Finds first index where arr[index] >= x
    int lowerBound(int[] arr, int x) {

        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] >= x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    // Finds first index where arr[index] > x
    int upperBound(int[] arr, int x) {

        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] <= x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    // ============================================================
    //                         MAIN METHOD
    // ============================================================
    public static void main(String[] args) {

        ElementsInRange solver = new ElementsInRange();

        int[] arr = {1, 4, 2, 8, 5};
        int[][] queries = {
                {1, 4},
                {3, 6},
                {0, 10}
        };

        System.out.println(
                "Elements count in each range = " +
                        solver.cntInRange(arr, queries)
        );
    }
}
