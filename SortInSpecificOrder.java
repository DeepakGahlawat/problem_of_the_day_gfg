/*
    ============================================================
                    SORT IN SPECIFIC ORDER
    ============================================================

    🧠 INTUITION
    ------------------------------------------------------------
    The problem requires sorting an array in a specific order:
        1️⃣ All ODD numbers first (in descending order)
        2️⃣ All EVEN numbers next (in ascending order)

    Example:
        Input : [1, 2, 3, 5, 4]
        Output: [5, 3, 1, 2, 4]

    ------------------------------------------------------------
    🧩 KEY TRICK (SIGN FLIP)
    ------------------------------------------------------------
    - Multiply all ODD numbers by -1
    - Sort the entire array normally
    - Convert negative values back to positive

    Why does this work?
        • Odd numbers become negative → come first after sorting
        • Larger odd numbers become more negative → descending order
        • Even numbers remain positive → appear later in ascending order

    ------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    ------------------------------------------------------------
    Time Complexity:
        O(n log n)

    Space Complexity:
        O(1)  (in-place apart from sorting)

    ------------------------------------------------------------
    ✔ Smart trick-based sorting problem
    ============================================================
*/

import java.util.*;

public class SortInSpecificOrder {

    public void sortIt(int[] arr) {

        // Step 1: Flip sign of all odd numbers
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 1) {
                arr[i] = -arr[i];
            }
        }

        // Step 2: Sort entire array
        Arrays.sort(arr);

        // Step 3: Convert negative numbers back to positive
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                arr[i] = -arr[i];
            }
        }
    }

    // ============================================================
    //                         MAIN METHOD
    // ============================================================
    public static void main(String[] args) {

        SortInSpecificOrder obj = new SortInSpecificOrder();

        int[] arr = {1, 2, 3, 5, 4, 7, 6};

        System.out.println("Original Array:");
        System.out.println(Arrays.toString(arr));

        obj.sortIt(arr);

        System.out.println("Sorted in Specific Order:");
        System.out.println(Arrays.toString(arr));
    }
}
