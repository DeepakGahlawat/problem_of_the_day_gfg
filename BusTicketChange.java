/*
========================================================
Class Name: Bus Ticket Change
========================================================

Intuition:
-----------
Each bus ticket costs exactly 5 coins.
Passengers pay using notes of value 5, 10, or 20.

We must always return correct change so that every passenger
effectively pays 5 coins.

Key Observations:
-----------------
1. A passenger paying with 5 needs no change → always safe.
2. A passenger paying with 10 needs 1 five-coin note as change.
3. A passenger paying with 20 needs 15 as change:
   - Prefer giving 10 + 5 (best choice)
   - Otherwise give 5 + 5 + 5

Greedy Strategy:
----------------
Always try to keep higher denomination notes (10) for future use,
because they help make change for 20 efficiently.

Approach:
---------
- Maintain count of available 5-coin and 10-coin notes.
- Process passengers one by one in order.
- If at any point correct change cannot be given, return false.

Time Complexity:
----------------
O(n), where n = number of passengers

Space Complexity:
-----------------
O(1), constant extra space

========================================================
*/

class BusTicketChange {

    public boolean canServe(int[] arr) {
        int count5 = 0;
        int count10 = 0;

        for (int i = 0; i < arr.length; i++) {

            // Passenger pays with 5 → no change needed
            if (arr[i] == 5) {
                count5++;
            }

            // Passenger pays with 10 → needs one 5 as change
            else if (arr[i] == 10) {
                if (count5 == 0) return false;
                count5--;
                count10++;
            }

            // Passenger pays with 20 → needs 15 as change
            else {
                // Prefer giving 10 + 5
                if (count10 > 0 && count5 > 0) {
                    count10--;
                    count5--;
                }
                // Otherwise give three 5s
                else if (count10 == 0 && count5 >= 3) {
                    count5 -= 3;
                }
                // No valid way to give change
                else {
                    return false;
                }
            }
        }
        return true;
    }

    // Main method for quick testing
    public static void main(String[] args) {
        BusTicketChange sol = new BusTicketChange();

        int[] arr1 = {5, 5, 5, 10, 20};
        int[] arr2 = {5, 5, 10, 10, 20};

        System.out.println(sol.canServe(arr1)); // true
        System.out.println(sol.canServe(arr2)); // false
    }
}
