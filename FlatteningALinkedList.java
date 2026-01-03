/*
    ============================================================
                FLATTENING A LINKED LIST
    ============================================================

    🧠 INTUITION
    ------------------------------------------------------------
    We are given a special linked list where:
        - `next` pointer connects head nodes horizontally
        - `bottom` pointer connects nodes vertically
        - Each vertical list is already SORTED
        - Head nodes are also SORTED

    Our task is to FLATTEN this structure into a single
    sorted list using ONLY the `bottom` pointer.

    This is very similar to:
        👉 Merging multiple sorted linked lists

    ------------------------------------------------------------
    🧩 KEY IDEA
    ------------------------------------------------------------
    - Recursively flatten the list on the right (root.next)
    - Merge current list with the already-flattened right list
    - Use standard merge of two sorted linked lists
      (but via `bottom` pointers instead of `next`)

    ------------------------------------------------------------
    🧮 APPROACH
    ------------------------------------------------------------
    1️⃣ Base case:
         - If root is null → return null
    2️⃣ Recursively flatten the list starting from root.next
    3️⃣ Merge:
         - Current node's vertical list
         - Flattened list from the right
    4️⃣ Return merged sorted list

    ------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    ------------------------------------------------------------
    Let N = total number of nodes

    Time Complexity:
        O(N)

    Space Complexity:
        O(n) recursion stack (n = number of head nodes)

    ------------------------------------------------------------
    ✔ Classic recursive + merge problem
      Very common GFG / interview question
    ============================================================
*/

// Definition of Node
class Node {
    int data;
    Node next;
    Node bottom;

    Node(int x) {
        data = x;
        next = null;
        bottom = null;
    }
}

public class FlatteningALinkedList {

    public Node flatten(Node root) {

        // Base case
        if (root == null) return null;

        // First list starts at root
        Node first = root;

        // Recursively flatten the list on the right
        Node second = flatten(root.next);

        // Merge the two sorted lists
        return merge(first, second);
    }

    // Merge two sorted linked lists using bottom pointers
    Node merge(Node first, Node second) {

        Node dummy = new Node(-1);
        Node curr = dummy;

        Node curr1 = first;
        Node curr2 = second;

        // Merge like standard sorted linked list
        while (curr1 != null && curr2 != null) {

            if (curr1.data <= curr2.data) {
                curr.bottom = curr1;
                curr1 = curr1.bottom;
            } else {
                curr.bottom = curr2;
                curr2 = curr2.bottom;
            }
            curr = curr.bottom;
        }

        // Attach remaining nodes
        while (curr1 != null) {
            curr.bottom = curr1;
            curr1 = curr1.bottom;
            curr = curr.bottom;
        }

        while (curr2 != null) {
            curr.bottom = curr2;
            curr2 = curr2.bottom;
            curr = curr.bottom;
        }

        return dummy.bottom;
    }

    // ============================================================
    //                         MAIN METHOD
    // ============================================================
    public static void main(String[] args) {

        FlatteningALinkedList solver =
                new FlatteningALinkedList();

        /*
            Constructing example:
            5 -> 10 -> 19 -> 28
            |    |     |     |
            7    20    22    35
            |          |     |
            8          50    40
            |                |
            30               45
        */

        Node head = new Node(5);
        head.bottom = new Node(7);
        head.bottom.bottom = new Node(8);
        head.bottom.bottom.bottom = new Node(30);

        head.next = new Node(10);
        head.next.bottom = new Node(20);

        head.next.next = new Node(19);
        head.next.next.bottom = new Node(22);
        head.next.next.bottom.bottom = new Node(50);

        head.next.next.next = new Node(28);
        head.next.next.next.bottom = new Node(35);
        head.next.next.next.bottom.bottom = new Node(40);
        head.next.next.next.bottom.bottom.bottom = new Node(45);

        Node result = solver.flatten(head);

        // Print flattened list using bottom pointer
        while (result != null) {
            System.out.print(result.data + " -> ");
            result = result.bottom;
        }
        System.out.println("null");
    }
}
