/*
    ============================================================
        SEARCH INSERT POSITION OF K IN A SORTED ARRAY
    ============================================================

    🧠 INTUITION
    ------------------------------------------------------------
    Given a sorted array and a target value K, we need to:
        - Return the index if K already exists
        - Otherwise, return the index where K should be inserted
          to keep the array sorted

    Since the array is already sorted, Binary Search is the
    most efficient approach.

    ------------------------------------------------------------
    🧩 KEY OBSERVATION
    ------------------------------------------------------------
    After binary search ends:
        - `low` always points to the correct insertion position

    Why?
        - All elements before `low` are < K
        - All elements after `high` are > K

    ------------------------------------------------------------
    🧮 APPROACH (BINARY SEARCH)
    ------------------------------------------------------------
    1️⃣ Initialize low = 0, high = n - 1
    2️⃣ While low <= high:
         - Compute mid
         - If arr[mid] == K → return mid
         - If arr[mid] < K → search right half
         - Else → search left half
    3️⃣ If K is not found, return `low`

    ------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    ------------------------------------------------------------
    Time Complexity:
        O(log n)

    Space Complexity:
        O(1)

    ------------------------------------------------------------
    ✔ Classic binary search variant
      Frequently asked in interviews
    ============================================================
*/

import java.util.*;

public class SearchInsertPositionOfKInSortedArray {

    public int searchInsertK(int[] arr, int k) {

        int low = 0;
        int high = arr.length - 1;

        // Standard binary search
        while (low <= high) {

            int mid = low + (high - low) / 2;

            // If element found, return its index
            if (arr[mid] == k) {
                return mid;
            }

            // Move right
            if (arr[mid] < k) {
                low = mid + 1;
            }
            // Move left
            else {
                high = mid - 1;
            }
        }

        // If not found, low is the correct insert position
        return low;
    }

    // ============================================================
    //                         MAIN METHOD
    // ============================================================
    public static void main(String[] args) {

        SearchInsertPositionOfKInSortedArray solver =
                new SearchInsertPositionOfKInSortedArray();

        int[] arr = {1, 3, 5, 6};
        int k = 4;

        System.out.println(
                "Insert position of " + k + " = " +
                        solver.searchInsertK(arr, k)
        );
    }
}
