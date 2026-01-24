public class JosephusProblem {

    /**
     * Problem: Josephus Problem
     *
     * There are n people standing in a circle (1 to n).
     * Starting from position 1, every k-th person is eliminated.
     * We need to find the position of the last remaining person.
     *
     * Approach (Mathematical / Iterative Josephus Formula):
     * ----------------------------------------------------
     * Let:
     *   f(n, k) = position of survivor (0-based index)
     *
     * Recurrence relation:
     *   f(1, k) = 0
     *   f(n, k) = (f(n-1, k) + k) % n
     *
     * This works because:
     * - After removing one person, the circle shrinks
     * - The starting point shifts by k positions
     *
     * Final answer is converted to 1-based index:
     *   survivor = f(n, k) + 1
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int josephus(int n, int k) {

        int ans = 0; // f(1, k) = 0 (0-based index)

        // Build solution iteratively from 2 people to n people
        for (int i = 1; i <= n; i++) {
            ans = (ans + k) % i;
        }

        // Convert 0-based index to 1-based index
        return ans + 1;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        JosephusProblem sol = new JosephusProblem();

        // Example 1
        int n1 = 5, k1 = 2;
        System.out.println("Input: n = 5, k = 2");
        System.out.println("Output: " + sol.josephus(n1, k1)); // Expected: 3
        System.out.println();

        // Example 2
        int n2 = 7, k2 = 3;
        System.out.println("Input: n = 7, k = 3");
        System.out.println("Output: " + sol.josephus(n2, k2)); // Expected: 4
        System.out.println();

        // Extra test
        int n3 = 1, k3 = 5;
        System.out.println("Input: n = 1, k = 5");
        System.out.println("Output: " + sol.josephus(n3, k3)); // Expected: 1
    }
}
