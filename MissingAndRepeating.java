// Given an unsorted array arr[] of size n, containing elements from the range 1 to n, it is known that one number in this range is missing, and another number occurs twice in the array, find both the duplicate number and the missing number.

// Examples:

// Input: arr[] = [2, 2]
// Output: [2, 1]
// Explanation: Repeating number is 2 and the missing number is 1.
// Input: arr[] = [1, 3, 3] 
// Output: [3, 2]
// Explanation: Repeating number is 3 and the missing number is 2.
// Input: arr[] = [4, 3, 6, 2, 1, 1]
// Output: [1, 5]
// Explanation: Repeating number is 1 and the missing number is 5.
// Constraints:
// 2 ≤ n ≤ 106
// 1 ≤ arr[i] ≤ n



import java.util.ArrayList;
import java.util.Scanner;

class MissingAndRepeating {

    static ArrayList<Integer> findTwoElement(int arr[]) {
        int n = arr.length;
        int xorn = 0;  // XOR of numbers from 1 to n
        int xor = 0;   // XOR of array elements
        
        for (int i = 1; i <= n; i++) {
            xorn ^= i;
            xor ^= arr[i - 1];
        }
        
        int xorab = xorn ^ xor;  // XOR of missing and repeating
        
        // Find rightmost set bit
        int rightmostsetbit = xorab & (~(xorab - 1));
        
        int xor1 = 0, xor2 = 0;
        
        // Divide elements into two buckets
        for (int it : arr) {
            if ((it & rightmostsetbit) == 0)
                xor1 ^= it;
            else
                xor2 ^= it;
        }
        
        for (int i = 1; i <= n; i++) {
            if ((i & rightmostsetbit) == 0)
                xor1 ^= i;
            else
                xor2 ^= i;
        }
        
        // Check which is repeating
        int countXor1 = 0;
        for (int num : arr) {
            if (num == xor1)
                countXor1++;
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        if (countXor1 == 2) {
            // xor1 is repeating, xor2 is missing
            ans.add(xor1);
            ans.add(xor2);
        } else {
            // xor2 is repeating, xor1 is missing
            ans.add(xor2);
            ans.add(xor1);
        }
        
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input size
        int n = sc.nextInt();
        int[] arr = new int[n];

        // Input array
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        ArrayList<Integer> result = findTwoElement(arr);

        System.out.println("Repeating Number: " + result.get(0));
        System.out.println("Missing Number: " + result.get(1));
    }
}
