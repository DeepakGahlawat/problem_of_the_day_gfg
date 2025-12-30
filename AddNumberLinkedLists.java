/*
    ============================================================
                ADD NUMBER LINKED LISTS
    ============================================================

    🧠 INTUITION
    ------------------------------------------------------------
    Each linked list represents a NON-NEGATIVE integer where:
        - Each node stores a single digit
        - Digits are stored in FORWARD order
          (most significant digit first)

    Example:
        1 -> 2 -> 3   represents 123

    To add two such numbers easily:
        - Reverse both lists to process digits from
          least significant to most significant
        - Perform digit-by-digit addition with carry
        - Reverse the result back to forward order

    ------------------------------------------------------------
    🧩 KEY OBSERVATIONS
    ------------------------------------------------------------
    - Reversing simplifies addition (like normal math)
    - Carry must be propagated correctly
    - Output should NOT contain leading zeros

    ------------------------------------------------------------
    🧮 APPROACH
    ------------------------------------------------------------
    1️⃣ Reverse both input linked lists
    2️⃣ Traverse both lists and add corresponding digits
       along with carry
    3️⃣ Build the result list in reverse order
    4️⃣ Reverse the result list to restore forward order
    5️⃣ Remove leading zeros if any

    ------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    ------------------------------------------------------------
    Let n and m be lengths of the two lists

    Time Complexity:
        O(n + m)

    Space Complexity:
        O(n + m)   (for the result list)

    ------------------------------------------------------------
    ✔ Classic linked list addition problem
      Very common interview question
    ============================================================
*/

// Definition for singly-linked list node
class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}

public class AddNumberLinkedLists {

    public Node addTwoLists(Node head1, Node head2) {

        // Reverse both linked lists
        Node curr1 = reverse(head1);
        Node curr2 = reverse(head2);

        int carry = 0;

        // Dummy node to build result list
        Node dummy = new Node(-1);
        Node curr = dummy;

        // Add digits while at least one list has nodes
        while (curr1 != null || curr2 != null) {

            int sum = carry;

            if (curr1 != null) sum += curr1.data;
            if (curr2 != null) sum += curr2.data;

            // Create new node with digit
            Node newNode = new Node(sum % 10);
            carry = sum / 10;

            curr.next = newNode;
            curr = newNode;

            if (curr1 != null) curr1 = curr1.next;
            if (curr2 != null) curr2 = curr2.next;
        }

        // If carry remains, add a new node
        if (carry != 0) {
            curr.next = new Node(carry);
        }

        // Reverse result to restore forward order
        Node newHead = reverse(dummy.next);

        // Remove leading zeros (if any)
        while (newHead != null && newHead.data == 0) {
            newHead = newHead.next;
        }

        return newHead;
    }

    // Utility function to reverse a linked list
    Node reverse(Node head) {

        Node prev = null;
        Node curr = head;

        while (curr != null) {
            Node nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }

    // ============================================================
    //                         MAIN METHOD
    // ============================================================
    public static void main(String[] args) {

        AddNumberLinkedLists solver = new AddNumberLinkedLists();

        // Example 1: 123 + 999 = 1122
        Node a1 = new Node(1);
        a1.next = new Node(2);
        a1.next.next = new Node(3);

        Node b1 = new Node(9);
        b1.next = new Node(9);
        b1.next.next = new Node(9);

        Node res1 = solver.addTwoLists(a1, b1);
        printList(res1); // 1 -> 1 -> 2 -> 2

        // Example 2: 63 + 7 = 70
        Node a2 = new Node(6);
        a2.next = new Node(3);

        Node b2 = new Node(7);

        Node res2 = solver.addTwoLists(a2, b2);
        printList(res2); // 7 -> 0
    }

    // Helper to print linked list
    static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }
}
