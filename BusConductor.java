/*
    ============================================================
                        BUS CONDUCTOR
    ============================================================

    🧠 INTUITION
    ------------------------------------------------------------
    We are given:
        - chairs[]      → positions of chairs
        - passengers[]  → positions of passengers

    Each passenger must sit on exactly one chair.
    Cost (moves) = |chair position - passenger position|

    To MINIMIZE the total number of moves:
        - Match passengers to chairs such that total distance
          travelled is minimum.

    ------------------------------------------------------------
    🧩 KEY OBSERVATION (GREEDY)
    ------------------------------------------------------------
    If both arrays are SORTED:
        - Assign the closest available chair to each passenger
        - This greedy pairing minimizes total absolute distance

    Why sorting works?
        - Any cross-matching (farther pairs) increases total moves
        - This is a classic "minimum sum of absolute differences"
          problem

    ------------------------------------------------------------
    🧮 APPROACH
    ------------------------------------------------------------
    1️⃣ Sort chairs[]
    2️⃣ Sort passengers[]
    3️⃣ Pair i-th chair with i-th passenger
    4️⃣ Sum up |chairs[i] - passengers[i]|

    ------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    ------------------------------------------------------------
    Time Complexity:
        O(n log n)  → due to sorting

    Space Complexity:
        O(1) extra (in-place sorting, ignoring input storage)

    ------------------------------------------------------------
    ✔ Simple, clean greedy solution
      Common in OA / interview rounds
    ============================================================
*/

import java.util.*;

public class BusConductor {

    public int findMoves(int[] chairs, int[] passengers) {

        // Step 1: Sort both arrays
        Arrays.sort(chairs);
        Arrays.sort(passengers);

        int moves = 0;

        // Step 2: Pair each passenger with closest chair
        for (int i = 0; i < chairs.length; i++) {
            moves += Math.abs(chairs[i] - passengers[i]);
        }

        return moves;
    }

    // ============================================================
    //                         MAIN METHOD
    // ============================================================
    public static void main(String[] args) {

        BusConductor solver = new BusConductor();

        int[] chairs = {3, 1, 5};
        int[] passengers = {2, 7, 4};

        System.out.println(
                "Minimum number of moves = " +
                        solver.findMoves(chairs, passengers)
        );
    }
}
