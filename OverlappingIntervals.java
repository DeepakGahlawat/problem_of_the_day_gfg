/*
    ============================================================
                    OVERLAPPING INTERVALS
    ============================================================

    🧠 INTUITION
    ------------------------------------------------------------
    When intervals overlap, they should be merged into a single
    interval that covers the full range.

    Key observation:
        - If intervals are sorted by start time,
          overlapping intervals will appear next to each other.

    Example:
        [1,3], [2,6], [8,10]

    After sorting:
        [1,3], [2,6] → overlap → merge to [1,6]
        [8,10]       → no overlap → separate interval

    ------------------------------------------------------------
    🧩 APPROACH
    ------------------------------------------------------------
    1️⃣ Sort all intervals based on starting value
    2️⃣ Initialize `start` and `end` using the first interval
    3️⃣ Traverse remaining intervals:
         - If current.start > end → no overlap
             → store previous interval
             → reset start & end
         - Else → overlap exists
             → extend end = max(end, current.end)
    4️⃣ Add the last merged interval to result

    ------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    ------------------------------------------------------------
    Time Complexity:
        O(n log n)  → sorting dominates

    Space Complexity:
        O(n)        → output list

    ------------------------------------------------------------
    ✔ Very common interval problem (FAANG / GFG / LeetCode)
    ============================================================
*/

import java.util.*;

public class OverlappingIntervals {

    // Method to merge overlapping intervals
    public ArrayList<int[]> mergeOverlap(int[][] arr) {

        // Sort intervals based on start time
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        int start = arr[0][0];
        int end = arr[0][1];

        ArrayList<int[]> result = new ArrayList<>();

        // Traverse intervals
        for (int i = 1; i < arr.length; i++) {

            // No overlap case
            if (arr[i][0] > end) {
                result.add(new int[]{start, end});
                start = arr[i][0];
                end = arr[i][1];
            }
            else {
                // Overlapping case → extend the interval
                end = Math.max(end, arr[i][1]);
            }
        }

        // Add the last interval
        result.add(new int[]{start, end});

        return result;
    }

    // ============================================================
    //                         MAIN METHOD
    // ============================================================
    public static void main(String[] args) {

        OverlappingIntervals obj = new OverlappingIntervals();

        int[][] intervals = {
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };

        ArrayList<int[]> merged = obj.mergeOverlap(intervals);

        System.out.println("Merged Intervals:");
        for (int[] interval : merged) {
            System.out.println(Arrays.toString(interval));
        }
    }
}
