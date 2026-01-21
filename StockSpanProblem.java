import java.util.*;

class StockSpanProblem {

    public ArrayList<Integer> calculateSpan(int[] arr) {
        ArrayList<Integer> res = new ArrayList<>();
        Stack<Integer> st = new Stack<>();
        int n = arr.length;

        for(int i=0;i<n;i++)
        {
            while(!st.isEmpty() && arr[st.peek()]<=arr[i])
            {
                st.pop();
            }
            if(st.isEmpty()) res.add(i+1);
            else res.add(i-st.peek());
            
            st.push(i);
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
