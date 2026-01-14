/*
========================================================
Class Name: Police and Thieves
========================================================

Intuition:
-----------
We are given a row where:
- 'P' represents a Policeman
- 'T' represents a Thief

Rules:
------
1. Each policeman can catch at most ONE thief.
2. A policeman can catch a thief only if the distance
   between them is ≤ k.
3. Policemen and thieves must be matched optimally to
   maximize the total number of catches.

Key Idea (Two Pointers / Greedy):
--------------------------------
- Maintain two pointers:
    i → points to the next Policeman
    j → points to the next Thief
- Always try to match the nearest available policeman
  and thief.
- If the distance is valid (|i - j| ≤ k), make a match.
- Otherwise, move the pointer that is "behind" to find
  a closer match.

Why this works:
---------------
Matching the closest valid policeman-thief pair greedily
ensures maximum matches without blocking better future matches.

Time Complexity:
----------------
O(n), where n = length of the array

Space Complexity:
-----------------
O(1), constant extra space

========================================================
*/

class PoliceAndThieves {

    public int catchThieves(char[] arr, int k) {
        int n = arr.length;
        int i = 0; // pointer for policemen
        int j = 0; // pointer for thieves
        int count = 0;

        while (i < n && j < n) {

            // Move i to the next policeman
            while (i < n && arr[i] != 'P') i++;

            // Move j to the next thief
            while (j < n && arr[j] != 'T') j++;

            // If both pointers are valid and within distance
            if (i < n && j < n && Math.abs(i - j) <= k) {
                count++; // thief caught
                i++;
                j++;
            }
            // Policeman is behind thief → move policeman
            else if (i < n && i < j) {
                i++;
            }
            // Thief is behind policeman → move thief
            else if (j < n && j < i) {
                j++;
            }
        }
        return count;
    }

    // ===================== MAIN METHOD =====================
    public static void main(String[] args) {
        PoliceAndThieves sol = new PoliceAndThieves();

        char[] arr1 = {'P', 'T', 'T', 'P', 'T'};
        int k1 = 1;
        System.out.println(sol.catchThieves(arr1, k1)); // 2

        char[] arr2 = {'T', 'T', 'P', 'P', 'T', 'P'};
        int k2 = 2;
        System.out.println(sol.catchThieves(arr2, k2)); // 3
    }
}
