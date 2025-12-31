/*
    ============================================================
                    PALINDROME LINKED LIST
    ============================================================

    🧠 INTUITION
    ------------------------------------------------------------
    A linked list is a palindrome if it reads the same
    forwards and backwards.

    Since singly linked lists cannot be traversed backward,
    we need a way to compare the first half with the second half.

    ------------------------------------------------------------
    🧩 KEY IDEA
    ------------------------------------------------------------
    1️⃣ Use slow & fast pointers to find the middle of the list
    2️⃣ Reverse the second half of the list
    3️⃣ Compare the first half and the reversed second half
    4️⃣ If all corresponding values match → palindrome

    ------------------------------------------------------------
    🧮 APPROACH
    ------------------------------------------------------------
    - Slow pointer moves 1 step, fast pointer moves 2 steps
    - When fast reaches the end, slow is at the middle
    - Reverse list from slow.next
    - Compare node-by-node with head

    ------------------------------------------------------------
    ⏱️ TIME & SPACE COMPLEXITY
    ------------------------------------------------------------
    Time Complexity:
        O(n)

    Space Complexity:
        O(1)   (in-place reversal, no extra data structures)

    ------------------------------------------------------------
    ✔ Optimal solution
      Frequently asked Linked List interview problem
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

public class PalindromeLinkedList {

    public boolean isPalindrome(Node head) {

        // Edge cases: empty or single node list
        if (head == null || head.next == null) return true;

        Node slow = head;
        Node fast = head;

        // Step 1: Find middle of the list
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse second half
        Node secondHalf = reverse(slow.next);
        Node firstHalf = head;

        // Step 3: Compare both halves
        while (secondHalf != null) {
            if (firstHalf.data != secondHalf.data) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        return true;
    }

    // Utility method to reverse a linked list
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

        PalindromeLinkedList solver = new PalindromeLinkedList();

        // Example 1: Palindrome
        Node h1 = new Node(1);
        h1.next = new Node(2);
        h1.next.next = new Node(1);
        h1.next.next.next = new Node(1);
        h1.next.next.next.next = new Node(2);
        h1.next.next.next.next.next = new Node(1);

        System.out.println(
                solver.isPalindrome(h1)
        ); // true

        // Example 2: Not a palindrome
        Node h2 = new Node(10);
        h2.next = new Node(20);
        h2.next.next = new Node(30);
        h2.next.next.next = new Node(40);
        h2.next.next.next.next = new Node(50);

        System.out.println(
                solver.isPalindrome(h2)
        ); // false
    }
}
