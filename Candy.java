import java.util.*;

/*
========================================================
Class Name: Candy
========================================================

Intuition:
-----------
Each child must receive at least one candy.
Additionally, if a child has a higher rating than an adjacent child,
they must receive more candies than that neighbor.

This is a classic two-pass greedy problem.

Key Observations:
-----------------
1. If ratings increase from left to right, candies should also increase.
2. If ratings increase from right to left, candies should increase accordingly.
3. Both left and right neighbor conditions must be satisfied simultaneously.

Approach (Two Pass Greedy):
---------------------------
1. Initialize a candies array with 1 candy for each child.
2. Left-to-right pass:
   - If rating[i] > rating[i-1], then candies[i] = candies[i-1] + 1
3. Right-to-left pass:
   - If rating[i] > rating[i+1], then candies[i] = max(candies[i], candies[i+1] + 1)
4. Sum all candies.

This ensures:
- Minimum candies are used
- Both neighbor constraints are satisfied

Time Complexity:
----------------
O(n), where n = number of children

Space Complexity:
-----------------
O(n), for the candies array

========================================================
*/

class Candy {

    public int minCandy(int arr[]) {
        int n = arr.length;
        int[] candies = new int[n];

        // Step 1: Every child gets at least one candy
        Arrays.fill(candies, 1);

        // Step 2: Left to right pass
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) {
                candies[i] = Math.max(candies[i], candies[i - 1] + 1);
            }
        }

        // Step 3: Right to left pass
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        // Step 4: Sum total candies
        int count = 0;
        for (int i = 0; i < n; i++) {
            count += candies[i];
        }

        return count;
    }

    // ===================== MAIN METHOD =====================
    public static void main(String[] args) {
        Candy sol = new Candy();

        int[] arr1 = {1, 0, 2};
        int[] arr2 = {1, 2, 2};

        System.out.println(sol.minCandy(arr1)); // 5
        System.out.println(sol.minCandy(arr2)); // 4
    }
}
