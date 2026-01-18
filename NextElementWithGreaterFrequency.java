import java.util.*;

public class NextElementWithGreaterFrequency {

    static class Solution {
        public ArrayList<Integer> nextFreqGreater(int[] arr) {
            // code here
            Stack<Integer> st = new Stack<>();
            Map<Integer, Integer> map = new HashMap<>();
            int n = arr.length;
            ArrayList<Integer> res = new ArrayList<>();

            // Step 1: count frequency of each element
            for (int it : arr) {
                map.put(it, map.getOrDefault(it, 0) + 1);
            }

            // Step 2: traverse from right to left
            for (int i = n - 1; i >= 0; i--) {
                int currfreq = map.get(arr[i]);

                // Pop elements from stack while they do NOT have higher freq than current
                while (!st.isEmpty() && map.get(st.peek()) <= currfreq)
                    st.pop();

                // If stack empty -> no element with higher frequency
                if (st.isEmpty()) res.add(-1);
                else res.add(st.peek());

                // Push current element
                st.push(arr[i]);
            }

            // Reverse result because we filled it from right to left
            Collections.reverse(res);
            return res;
        }
    }

    // MAIN METHOD
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] arr1 = {2, 1, 1, 3, 2, 1};
        System.out.println("Input: " + Arrays.toString(arr1));
        System.out.println("Output: " + sol.nextFreqGreater(arr1));

        System.out.println();

        int[] arr2 = {5, 1, 5, 6, 6};
        System.out.println("Input: " + Arrays.toString(arr2));
        System.out.println("Output: " + sol.nextFreqGreater(arr2));
    }
}
