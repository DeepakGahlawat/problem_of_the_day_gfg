/*
    ============================================
               TRANSPOSE OF MATRIX
    ============================================
    👉 Intuition:
       The transpose of a matrix is formed by turning rows into columns and
       columns into rows. So the value at mat[i][j] becomes mat[j][i]
       in the transpose.

       Example:
           1 2 3
           4 5 6

       Transpose:
           1 4
           2 5
           3 6

    👉 Approach Used:
       - Since the matrix is n x n, we can simply loop through each element.
       - We create an ArrayList<ArrayList<Integer>> of size n.
       - For each mat[i][j], we insert it into trans[j][i].
       - Time Complexity: O(n^2)
       - Auxiliary Space: O(1) extra (output matrix not counted)

    👉 Why this works?
       We directly place matrix[i][j] at the correct transposed position.

    👉 Example Walkthrough:
       Input:
            1 1
            2 2

       trans:
            trans[0] -> 1,2
            trans[1] -> 1,2

    ============================================
*/

import java.util.*;

public class TransposeOfMatrix {

    // Method to compute transpose
    public ArrayList<ArrayList<Integer>> transpose(int[][] mat) {

        int n = mat.length;

        // Create outer list with n empty lists
        ArrayList<ArrayList<Integer>> trans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            trans.add(new ArrayList<>()); // initialize each row
        }

        // Fill transpose matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                trans.get(j).add(mat[i][j]); // add mat[i][j] into col-wise list
            }
        }

        return trans;
    }

    // MAIN METHOD for testing
    public static void main(String[] args) {

        TransposeOfMatrix obj = new TransposeOfMatrix();

        int[][] mat = {
                {1, 1, 1, 1},
                {2, 2, 2, 2},
                {3, 3, 3, 3},
                {4, 4, 4, 4}
        };

        System.out.println("Original Matrix:");
        for (int[] row : mat) System.out.println(Arrays.toString(row));

        ArrayList<ArrayList<Integer>> result = obj.transpose(mat);

        System.out.println("\nTransposed Matrix:");
        for (ArrayList<Integer> row : result) System.out.println(row);
    }
}
