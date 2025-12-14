import java.util.*;

/*
 Problem: 2D Submatrix Sum Queries

 Given a 2D integer matrix and multiple queries,
 each query asks for the sum of elements in a submatrix
 defined by top-left (r1, c1) and bottom-right (r2, c2).

 Approach:
 We use a 2D Prefix Sum technique to preprocess the matrix.
 This allows answering each query in O(1) time after preprocessing.
*/

public class SubmatrixSumQueries2D {

    /*
     Time Complexity:
       - Prefix sum preprocessing: O(n × m)
       - Each query: O(1)
       - Total: O(n × m + q)

     Space Complexity:
       - O(1) extra space (prefix sum stored in original matrix)
       - If a separate prefix matrix is used: O(n × m)
    */
    public static ArrayList<Integer> prefixSum2D(int[][] mat, int[][] queries) {

        int m = mat.length;
        int n = mat[0].length;

        // -------- Build 2D Prefix Sum --------
        // Time: O(n × m)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = mat[i][j]
                          + (i > 0 ? mat[i - 1][j] : 0)
                          + (j > 0 ? mat[i][j - 1] : 0)
                          - (i > 0 && j > 0 ? mat[i - 1][j - 1] : 0);
            }
        }

        ArrayList<Integer> res = new ArrayList<>();

        // -------- Process Queries --------
        // Time per query: O(1)
        for (int[] q : queries) {
            int r1 = q[0], c1 = q[1];
            int r2 = q[2], c2 = q[3];

            int sum = mat[r2][c2]
                    - (r1 > 0 ? mat[r1 - 1][c2] : 0)
                    - (c1 > 0 ? mat[r2][c1 - 1] : 0)
                    + (r1 > 0 && c1 > 0 ? mat[r1 - 1][c1 - 1] : 0);

            res.add(sum);
        }

        return res;
    }

    // ---------------- MAIN METHOD ----------------
    public static void main(String[] args) {

        int[][] mat = {
            {1, 2, 3},
            {1, 1, 0},
            {4, 2, 2}
        };

        int[][] queries = {
            {0, 0, 1, 1},
            {1, 0, 2, 2}
        };

        ArrayList<Integer> result = prefixSum2D(mat, queries);
        System.out.println(result); // Expected Output: [5, 10]
    }
}
