import java.util.*;

class MinimumNumberOfWorkers {

    public int minMen(int[] arr) {
        int n = arr.length;
        ArrayList<int[]> intervals = new ArrayList<>();

        // Build intervals
        for (int i = 0; i < n; i++) {
            if (arr[i] == -1) continue;

            int start = Math.max(0, i - arr[i]);
            int end = Math.min(n - 1, i + arr[i]);
            intervals.add(new int[]{start, end});
        }

        if (intervals.isEmpty()) return -1;

        // Sort intervals by start time
        Collections.sort(intervals, (a, b) -> a[0] - b[0]);

        int count = 0;
        int covered = 0;   // next index that must be covered
        int i = 0;

        // Greedy interval covering
        while (covered < n) {
            int farthest = -1;

            while (i < intervals.size() && intervals.get(i)[0] <= covered) {
                farthest = Math.max(farthest, intervals.get(i)[1]);
                i++;
            }

            // Cannot extend coverage
            if (farthest < covered) return -1;

            count++;
            covered = farthest + 1;
        }

        return count;
    }

    // Optional main method for testing
    public static void main(String[] args) {
        MinimumNumberOfWorkers sol = new MinimumNumberOfWorkers();

        System.out.println(sol.minMen(new int[]{1, 2, 1, 0}));            // 1
        System.out.println(sol.minMen(new int[]{2, 3, 4, -1, 2, 0, 0, -1, 0})); // -1
        System.out.println(sol.minMen(new int[]{0, 1, 0, -1}));           // -1
    }
}
