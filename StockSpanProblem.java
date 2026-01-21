import java.util.*;

class StockSpanProblem {

    public ArrayList<Integer> calculateSpan(int[] arr) {
        ArrayList<Integer> res = new ArrayList<>();
        int n = arr.length;

        res.add(1); // span for day 0 is always 1

        for (int i = 1; i < n; i++) {
            int curr = i - 1;

            // jump backwards using already computed spans
            while (curr >= 0 && arr[curr] <= arr[i]) {
                curr -= res.get(curr);
            }

            res.add(i - curr);
        }

        return res;
    }

    public static void main(String[] args) {
        StockSpanProblem obj = new StockSpanProblem();

        int[] arr1 = {100, 80, 90, 120};
        System.out.println("Input:  " + Arrays.toString(arr1));
        System.out.println("Output: " + obj.calculateSpan(arr1));

        int[] arr2 = {10, 4, 5, 90, 120, 80};
        System.out.println("\nInput:  " + Arrays.toString(arr2));
        System.out.println("Output: " + obj.calculateSpan(arr2));
    }
}
