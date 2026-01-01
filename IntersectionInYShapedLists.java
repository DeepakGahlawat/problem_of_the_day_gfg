/*
    ============================================================
              INTERSECTION IN Y-SHAPED LINKED LISTS
    ============================================================

    🧠 INTUITION
    ------------------------------------------------------------
    Two singly linked lists intersect if they share a COMMON NODE
    reference (not just same value).

    Because the lists can be of different lengths, directly
    traversing them together may not align the nodes.

    ------------------------------------------------------------
    🧩 KEY IDEA (Two Pointer Trick)
    ------------------------------------------------------------
    - Use two pointers, one starting at head1 and one at head2
    - Move both pointers one step at a time
    - When a pointer reaches the end of its list, redirect it to
      the head of the other list

    This ensures:
        Both pointers traverse exactly (len1 + len2) nodes
        They will meet at the intersection point

    ------------------------------------------------------------
    🧮 APPROACH
    ------------------------------------------------------------
    1️⃣ Initialize two pointers curr1 = head1, curr2 = head2
    2️⃣ Move both pointers forward
    3️⃣ If any pointer becomes null, redirect it to the other head
    4️⃣ Loop ends when curr1 == curr2
    5️⃣ That node is the intersection point

    ------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    ------------------------------------------------------------
    Time Complexity:
        O(n + m)

    Space Complexity:
        O(1)

    ------------------------------------------------------------
    ✔ Optimal solution
      Very popular interview problem (no extra memory)
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

public class IntersectionInYShapedLists {

    public Node intersectPoint(Node head1, Node head2) {

        Node curr1 = head1;
        Node curr2 = head2;

        // Traverse until both pointers meet
        while (curr1 != curr2) {

            // Move to next or switch head when reaching end
            curr1 = (curr1 == null) ? head2 : curr1.next;
            curr2 = (curr2 == null) ? head1 : curr2.next;
        }

        // Either intersection node or null (guaranteed intersection exists)
        return curr1;
    }

    // ============================================================
    //                         MAIN METHOD
    // ============================================================
    public static void main(String[] args) {

        IntersectionInYShapedLists solver =
                new IntersectionInYShapedLists();

        // Common part: 15 -> 30
        Node common = new Node(15);
        common.next = new Node(30);

        // List 1: 10 -> 15 -> 30
        Node head1 = new Node(10);
        head1.next = common;

        // List 2: 3 -> 6 -> 9 -> 15 -> 30
        Node head2 = new Node(3);
        head2.next = new Node(6);
        head2.next.next = new Node(9);
        head2.next.next.next = common;

        Node intersection = solver.intersectPoint(head1, head2);
        System.out.println(intersection.data); // Output: 15
    }
}
