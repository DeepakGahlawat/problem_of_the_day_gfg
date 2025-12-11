// Given a pair-sum array arr[] construct the original array. A pair-sum array for an array is the array that contains sum of all pairs in ordered form, i.e., arr[0] is sum of res[0] and res[1], arr[1] is sum of res[0] and res[2] and so on.

// Note: If the size of original array res[] is n, then the size of pair-sum array arr[] would be n * (n -1) /2. We may assume that the pair-sum array arr[] is appropriate in size.
// Note that, if the original array is correct then the driver code will print true, else false;

// Examples:

// Input: arr[] = [4, 5, 3]
// Output: true
// Explanation: A valid original array is [3, 1, 2], pairwise sums are (3 + 1), (3 + 2) and (1 + 2).
// Input: arr[] = [3]
// Output: true
// Explanation: One of the valid original array is [1, 2].
// Constraints: 
// 1 ≤ n ≤ 103
// 1 ≤ arr[i] ≤ 109

// Expected Complexities
// Time Complexity: O(n)
// Auxiliary Space: O(1)

/*
    ======================= PROBLEM EXPLANATION ==========================

    You are given a "pair-sum array".

    For an original array res[] of size n:
        The pair-sum array contains sums of all pairs (res[i] + res[j])
        where i < j, in a fixed ordered form.

    Example:
        Original array: [3, 1, 2]

        All pairs in order:
           (3 + 1) = 4
           (3 + 2) = 5
           (1 + 2) = 3

        Pair-sum array = [4, 5, 3]

    Goal:
        Given only arr[] (the pair-sum array), reconstruct ANY valid res[].

    Key Insight:
        Let the original array be: res[0], res[1], res[2], ..., res[n-1]

        Pair sums give us:
            arr[0]  = res[0] + res[1]
            arr[1]  = res[0] + res[2]
            arr[n-1]= res[1] + res[2]

        Adding arr[0] + arr[1] - arr[n-1] gives:
            2 * res[0]

        So:
            res[0] = (arr[0] + arr[1] - arr[n-1]) / 2

        Then:
            res[i] = arr[i-1] - res[0]

    Complexity:
        Time:  O(n)
        Space: O(1) extra (besides result)

*/

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class ConstructArrayFromPairSum {

    public ArrayList<Integer> constructArr(int[] arr) {

        // Special case: only one pair-sum → original size = 2
        if (arr.length == 1)
            return new ArrayList<>(List.of(1, arr[0] - 1));

        // Step 1: Compute n using formula n(n-1)/2 = arr.length
        int n = (int) ((1 + Math.sqrt(1 + 8 * arr.length)) / 2);

        // Step 2: Derive res[0]
        int first = (arr[0] + arr[1] - arr[n - 1]) / 2;

        ArrayList<Integer> res = new ArrayList<>();
        res.add(first);

        // Step 3: Compute the remaining elements
        for (int i = 1; i < n; i++) {
            res.add(arr[i - 1] - first);
        }

        return res;
    }

    // ===================== MAIN METHOD FOR TESTING =====================
    public static void main(String[] args) {

        ConstructArrayFromPairSum solver = new ConstructArrayFromPairSum();

        // Test Case 1
        int[] test1 = {4, 5, 3};  // Original should be [3,1,2]
        System.out.println("Test Case 1:");
        System.out.println("Input:  " + Arrays.toString(test1));
        System.out.println("Output: " + solver.constructArr(test1));
        System.out.println();

        // Test Case 2
        int[] test2 = {3};  // Original should be [1,2]
        System.out.println("Test Case 2:");
        System.out.println("Input:  " + Arrays.toString(test2));
        System.out.println("Output: " + solver.constructArr(test2));
        System.out.println();

        // Test Case 3 (additional)
        int[] test3 = {7, 8, 9, 5, 6, 4}; 
        System.out.println("Test Case 3:");
        System.out.println("Input:  " + Arrays.toString(test3));
        System.out.println("Output: " + solver.constructArr(test3));
        System.out.println();
    }
}
