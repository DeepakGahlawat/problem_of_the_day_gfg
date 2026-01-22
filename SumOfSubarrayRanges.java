import java.util.Stack;

public class SumOfSubarrayRanges {

    /**
     * Problem: Sum of subarray ranges
     *
     * Range of a subarray = (max element - min element)
     * We need sum of ranges for ALL subarrays.
     *
     * Key idea (Contribution Technique):
     * Instead of iterating all subarrays (O(n^2)),
     * we count contribution of each element arr[i] as:
     *
     *   total contribution = (count as MAX - count as MIN) * arr[i]
     *
     * Where:
     * - count as MAX = number of subarrays where arr[i] is the maximum
     * - count as MIN = number of subarrays where arr[i] is the minimum
     *
     * We compute counts using Monotonic Stacks in O(n):
     * - Previous Smaller, Next Smaller (for minimum contributions)
     * - Previous Greater, Next Greater (for maximum contributions)
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int subarrayRanges(int[] arr) {
        int n = arr.length;
        long res = 0; // long prevents overflow during intermediate summation

        // These arrays store distances/ways:
        // leftMin[i]  = number of choices to extend left where arr[i] is MIN
        // rightMin[i] = number of choices to extend right where arr[i] is MIN
        // leftMax[i]  = number of choices to extend left where arr[i] is MAX
        // rightMax[i] = number of choices to extend right where arr[i] is MAX
        int[] leftMin = new int[n];
        int[] rightMin = new int[n];
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        Stack<Integer> stack = new Stack<>();

        // 1) Previous Smaller Element (Left Min)
        // Maintain increasing stack:
        // Pop while stack top >= current -> ensures STRICTLY smaller is previous boundary
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            leftMin[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        stack.clear();

        // 2) Next Smaller Element (Right Min)
        // Traverse from right:
        // Pop while stack top > current -> ensures correct handling of duplicates
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            rightMin[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }

        stack.clear();

        // 3) Previous Greater Element (Left Max)
        // Maintain decreasing stack:
        // Pop while stack top <= current -> ensures STRICTLY greater is previous boundary
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            leftMax[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        stack.clear();

        // 4) Next Greater Element (Right Max)
        // Traverse from right:
        // Pop while stack top < current -> ensures correct handling of duplicates
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                stack.pop();
            }
            rightMax[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }

        // 5) Contribution Calculation
        // Each element arr[i]:
        // contributes as MAX in leftMax[i] * rightMax[i] subarrays
        // contributes as MIN in leftMin[i] * rightMin[i] subarrays
        for (int i = 0; i < n; i++) {
            long countMax = (long) leftMax[i] * rightMax[i];
            long countMin = (long) leftMin[i] * rightMin[i];

            // Add contribution of arr[i] as max and subtract as min
            res += (countMax - countMin) * arr[i];
        }

        // Guaranteed to fit in int by problem statement
        return (int) res;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        SumOfSubarrayRanges sol = new SumOfSubarrayRanges();

        // Example 1
        int[] arr1 = {1, 2, 3};
        System.out.println("Input: [1,2,3]");
        System.out.println("Output: " + sol.subarrayRanges(arr1)); // Expected: 4
        System.out.println();

        // Example 2
        int[] arr2 = {-32, 0, -2, 72};
        System.out.println("Input: [-32,0,-2,72]");
        System.out.println("Output: " + sol.subarrayRanges(arr2)); // Expected: 318
        System.out.println();

        // Extra test
        int[] arr3 = {4, 1, 2};
        System.out.println("Input: [4,1,2]");
        System.out.println("Output: " + sol.subarrayRanges(arr3));
    }
}
