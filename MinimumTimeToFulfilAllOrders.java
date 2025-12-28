/*
    ============================================================
            MINIMUM TIME TO FULFIL ALL ORDERS
    ============================================================

    🧠 INTUITION
    ------------------------------------------------------------
    We have:
        - n donuts to prepare
        - m chefs, each with a rank r

    A chef with rank r:
        - Makes 1st donut in r minutes
        - 2nd donut in 2r minutes
        - 3rd donut in 3r minutes
        - ...
        - k donuts in r * (1 + 2 + ... + k) = r * k * (k + 1) / 2 minutes

    All chefs work in parallel.
    We must minimize the TOTAL TIME to complete n donuts.

    ------------------------------------------------------------
    🧩 KEY IDEA (GREEDY + MIN HEAP)
    ------------------------------------------------------------
    Instead of guessing the time, we SIMULATE donut preparation:

    - Always assign the next donut to the chef who can
      complete their NEXT donut the earliest.
    - This is done using a MIN HEAP (PriorityQueue).

    ------------------------------------------------------------
    🧮 APPROACH
    ------------------------------------------------------------
    1️⃣ Initialize a min-heap storing:
         {chefIndex, nextFinishTime, donutsMadeSoFar}

    2️⃣ Initially:
         Each chef has made 0 donuts,
         next donut time = rank[i]

    3️⃣ Repeat n times:
         - Extract chef who finishes next donut earliest
         - Assign donut
         - Update chef’s next finish time
         - Push chef back into heap

    4️⃣ After all donuts assigned:
         - Compute total time taken by each chef
         - Answer is the maximum among them

    ------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    ------------------------------------------------------------
    Let m = number of chefs, n = number of donuts

    Time Complexity:
        O(n log m)

    Space Complexity:
        O(m)

    ------------------------------------------------------------
    ✔ Greedy simulation with heap
      Classic GFG / interview scheduling problem
    ============================================================
*/

import java.util.*;

public class MinimumTimeToFulfilAllOrders {

    public int minTime(int[] rank, int n) {

        int m = rank.length;

        // Min-heap based on next finish time
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[1] - b[1]
        );

        // Each entry: {chefIndex, nextFinishTime, donutsMade}
        for (int i = 0; i < m; i++) {
            pq.add(new int[]{i, rank[i], 0});
        }

        // Assign n donuts one by one
        while (n > 0) {

            int[] top = pq.poll();
            int chef = top[0];
            int nextTime = top[1];
            int donutsMade = top[2];

            // Update for next donut
            int newDonuts = donutsMade + 1;
            int newTime = nextTime + (newDonuts + 1) * rank[chef];

            pq.add(new int[]{chef, newTime, newDonuts});
            n--;
        }

        // Calculate maximum time among all chefs
        int ans = Integer.MIN_VALUE;

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int chef = top[0];
            int donutsMade = top[2];

            // Total time = r * k * (k + 1) / 2
            int time = donutsMade * (donutsMade + 1) * rank[chef] / 2;
            ans = Math.max(ans, time);
        }

        return ans;
    }

    // ============================================================
    //                         MAIN METHOD
    // ============================================================
    public static void main(String[] args) {

        MinimumTimeToFulfilAllOrders solver =
                new MinimumTimeToFulfilAllOrders();

        int[] rank1 = {1, 2, 3, 4};
        int n1 = 10;
        System.out.println(
                "Minimum time = " +
                        solver.minTime(rank1, n1)
        ); // 12

        int[] rank2 = {1, 1, 1, 1, 1, 1, 1, 1};
        int n2 = 8;
        System.out.println(
                "Minimum time = " +
                        solver.minTime(rank2, n2)
        ); // 1
    }
}
