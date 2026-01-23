import java.util.Arrays;
import java.util.Stack;

public class MaximumPeopleVisibleInALine {

    /**
     * Problem: Maximum People Visible in a Line
     *
     * Visibility rule:
     * Person i can see person j if:
     * 1) height[j] < height[i]
     * 2) There is NO person k between them with height[k] >= height[i]
     *
     * Meaning:
     * A person can see in both directions until a person of height >= their height blocks the view.
     *
     * Approach (Monotonic Stack + boundaries):
     * For every person i, find:
     * - Previous Greater-or-Equal person on the left
     * - Next Greater-or-Equal person on the right
     *
     * Then visible range for i becomes:
     *   (prev[i] + 1) ... (next[i] - 1)
     * and count = range length (including i).
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int maxPeople(int[] arr) {
        int n = arr.length;

        // Compute previous greater-or-equal and next greater-or-equal indices
        int[] prev = previousGreater(arr);
        int[] next = nextGreater(arr);

        int maxCount = 0;

        for (int i = 0; i < n; i++) {
            // If no greater element on left -> boundary starts from 0
            int leftBound = (prev[i] == -1 ? 0 : prev[i] + 1);

            // If no greater element on right -> boundary ends at n-1
            int rightBound = (next[i] == n ? n - 1 : next[i] - 1);

            // Total visible people including self
            int count = rightBound - leftBound + 1;

            maxCount = Math.max(maxCount, count);
        }

        return maxCount;
    }

    /**
     * previousGreater[i] gives the index of the nearest person on the LEFT
     * whose height is >= arr[i]. If not found, it remains -1.
     *
     * Monotonic stack keeps heights in decreasing order (top has nearest greater/equal).
     *
     * Time Complexity: O(n)
     */
    private int[] previousGreater(int[] arr) {
        int n = arr.length;
        int[] prev = new int[n];
        Arrays.fill(prev, -1);

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            // Remove smaller heights (they can't block visibility for current element)
            while (!st.isEmpty() && arr[st.peek()] < arr[i]) {
                st.pop();
            }

            // Now top (if exists) is nearest >= arr[i]
            if (!st.isEmpty()) prev[i] = st.peek();

            st.push(i);
        }

        return prev;
    }

    /**
     * nextGreater[i] gives the index of the nearest person on the RIGHT
     * whose height is >= arr[i]. If not found, it remains n.
     *
     * Traversing from right side using monotonic stack.
     *
     * Time Complexity: O(n)
     */
    private int[] nextGreater(int[] arr) {
        int n = arr.length;
        int[] next = new int[n];
        Arrays.fill(next, n);

        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            // Remove smaller heights
            while (!st.isEmpty() && arr[st.peek()] < arr[i]) {
                st.pop();
            }

            // Now top (if exists) is nearest >= arr[i]
            if (!st.isEmpty()) next[i] = st.peek();

            st.push(i);
        }

        return next;
    }

    /**
     * Main method to test the solution
     */
    public static void main(String[] args) {
        MaximumPeopleVisibleInALine sol = new MaximumPeopleVisibleInALine();

        // Example 1
        int[] arr1 = {6, 2, 5, 4, 5, 1, 6};
        System.out.println("Input: [6,2,5,4,5,1,6]");
        System.out.println("Output: " + sol.maxPeople(arr1)); // Expected: 6
        System.out.println();

        // Example 2
        int[] arr2 = {1, 3, 6, 4};
        System.out.println("Input: [1,3,6,4]");
        System.out.println("Output: " + sol.maxPeople(arr2)); // Expected: 4
        System.out.println();

        // Extra test
        int[] arr3 = {5, 1, 2, 3, 4};
        System.out.println("Input: [5,1,2,3,4]");
        System.out.println("Output: " + sol.maxPeople(arr3));
    }
}
