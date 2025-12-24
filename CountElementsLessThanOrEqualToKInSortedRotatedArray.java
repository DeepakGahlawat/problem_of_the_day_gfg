/*
    =====================================================================
    COUNT ELEMENTS LESS THAN OR EQUAL TO K IN A SORTED ROTATED ARRAY
    =====================================================================

    🧠 INTUITION
    ---------------------------------------------------------------------
    The array is:
        - Initially sorted (distinct elements)
        - Rotated at an unknown pivot

    After rotation, the array consists of TWO sorted parts:
        1️⃣ Left part  : arr[0 ... pivot-1]
        2️⃣ Right part : arr[pivot ... n-1]

    To count elements ≤ x:
        - Count in left sorted part
        - Count in right sorted part
        - Add both counts

    ---------------------------------------------------------------------
    🧩 KEY OBSERVATIONS
    ---------------------------------------------------------------------
    1️⃣ Pivot is the index of the minimum element
    2️⃣ Each part is individually sorted
    3️⃣ Binary search can be used to count elements ≤ x
        in O(log n)

    ---------------------------------------------------------------------
    🧮 APPROACH
    ---------------------------------------------------------------------
    1️⃣ Find pivot using binary search
    2️⃣ Count elements ≤ x in [0 ... pivot-1]
    3️⃣ Count elements ≤ x in [pivot ... n-1]
    4️⃣ Return sum of both counts

    ---------------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    ---------------------------------------------------------------------
    Time Complexity:
        O(log n)
        - Pivot search: O(log n)
        - Two binary searches: O(log n)

    Space Complexity:
        O(1)

    ---------------------------------------------------------------------
    ✔ Efficient solution for large arrays
      Common rotated-array interview problem
    =====================================================================
*/

import java.util.*;

public class CountElementsLessThanOrEqualToKInSortedRotatedArray {

    int n;

    public int countLessEqual(int[] arr, int x) {

        n = arr.length;

        // Step 1: Find pivot (index of minimum element)
        int pivot = findPivot(arr);

        // Step 2: Count in both sorted halves
        int countLeft = countInSorted(arr, 0, pivot - 1, x);
        int countRight = countInSorted(arr, pivot, n - 1, x);

        return countLeft + countRight;
    }

    // Finds index of minimum element (pivot)
    int findPivot(int[] arr) {

        int low = 0;
        int high = arr.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] > arr[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low; // pivot index
    }

    // Counts elements ≤ x in a sorted subarray [left, right]
    int countInSorted(int[] arr, int left, int right, int x) {

        if (left > right) return 0;

        int l = left, r = right;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] <= x) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        // Number of elements ≤ x
        return l - left;
    }

    // ============================================================
    //                         MAIN METHOD
    // ============================================================
    public static void main(String[] args) {

        CountElementsLessThanOrEqualToKInSortedRotatedArray solver =
                new CountElementsLessThanOrEqualToKInSortedRotatedArray();

        int[] arr1 = {4, 5, 8, 1, 3};
        int x1 = 6;
        System.out.println(
                "Count ≤ " + x1 + " = " +
                        solver.countLessEqual(arr1, x1)
        );

        int[] arr2 = {6, 10, 12, 15, 2, 4, 5};
        int x2 = 14;
        System.out.println(
                "Count ≤ " + x2 + " = " +
                        solver.countLessEqual(arr2, x2)
        );
    }
}
