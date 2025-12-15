/**
 * Problem:
 * Count indices such that after removing the element at that index,
 * the sum of elements at even positions equals the sum of elements at odd positions.
 *
 * This is also known as:
 * "Count Indices to Balance Even and Odd Sums"
 */
class CountIndicesToBalanceEvenAndOddSums {

    /**
     * Core logic method
     */
    public static int cntWays(int[] arr) {

        int n = arr.length;

        // pre[i]  = sum of elements at indices i, i-2, i-4 ...
        // suff[i] = sum of elements at indices i, i+2, i+4 ...
        int[] pre = new int[n];
        int[] suff = new int[n];

        // -----------------------------
        // Build prefix parity sums
        // -----------------------------
        for (int i = 0; i < n; i++) {
            // If i-2 exists, add current value to that parity sum
            pre[i] = (i - 2 >= 0) ? pre[i - 2] + arr[i] : arr[i];
        }

        // -----------------------------
        // Build suffix parity sums
        // -----------------------------
        for (int i = n - 1; i >= 0; i--) {
            // If i+2 exists, add current value to that parity sum
            suff[i] = (i + 2 < n) ? suff[i + 2] + arr[i] : arr[i];
        }

        int count = 0;

        // -----------------------------
        // Try removing each index
        // -----------------------------
        for (int i = 0; i < n; i++) {

            /*
             After removing index i:
             - Indices to the right shift left by 1
             - Even indices become odd and vice versa
            */

            // New even-index sum after removal
            int evenSum = 0;
            if (i + 1 < n) evenSum += suff[i + 1]; // right side (flipped)
            if (i - 2 >= 0) evenSum += pre[i - 2]; // left side (same parity)

            // New odd-index sum after removal
            int oddSum = 0;
            if (i + 2 < n) oddSum += suff[i + 2];  // right side (same parity)
            if (i - 1 >= 0) oddSum += pre[i - 1];  // left side (flipped)

            // If balanced, count this index
            if (evenSum == oddSum) {
                count++;
            }
        }

        return count;
    }

    /**
     * Main method to test the solution
     */
    public static void main(String[] args) {

        int[] arr = {2, 1, 6, 4};

        int result = cntWays(arr);

        System.out.println("Number of indices to balance even and odd sums: " + result);
    }
}
