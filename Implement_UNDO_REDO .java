import java.util.*;

/*
====================================================
Class Name: Implement_UNDO_REDO
====================================================

-----------------------
Problem Explanation:
-----------------------
We are implementing a simple text editor that supports:

1) append(x) → add a character at the end of the document
2) undo()    → undo the last append operation
3) redo()    → redo the most recently undone operation
4) read()    → return the current document content

-----------------------
Intuition:
-----------------------
- The document only changes at the end → StringBuilder is efficient.
- Undo and Redo naturally follow LIFO behavior → Stack is ideal.

-----------------------
Approach:
-----------------------
- Use StringBuilder `doc` to store current document content.
- Use Stack<Character> `st` to store characters removed during undo.
- Maintain an index `ind` pointing to the last character.

Operations:
- append(x): add character to document and move index forward
- undo(): remove last character and push it onto stack
- redo(): pop from stack and append back
- read(): return document as string

-----------------------
Time Complexity:
-----------------------
append → O(1)
undo   → O(1)
redo   → O(1)
read   → O(n)

-----------------------
Space Complexity:
-----------------------
O(n) for document and undo stack
====================================================
*/

class Implement_UNDO_REDO {

    // Stores the current document
    StringBuilder doc = new StringBuilder();

    // Stack to store undone characters
    Stack<Character> st = new Stack<>();

    // Index of last character in document
    int ind = -1;

    // Append a character to the document
    public void append(char x) {
        doc.append(x);
        ind++;
    }

    // Undo the last append operation
    public void undo() {
        if (ind >= 0) {
            st.push(doc.charAt(ind)); // save for redo
            doc.deleteCharAt(ind);    // remove character
            ind--;
        }
    }

    // Redo the last undone operation
    public void redo() {
        if (!st.isEmpty()) {
            doc.append(st.pop()); // restore character
            ind++;
        }
    }

    // Read the current document
    public String read() {
        return doc.toString();
    }

    // ---------------- MAIN METHOD ----------------
    public static void main(String[] args) {

        Implement_UNDO_REDO editor = new Implement_UNDO_REDO();

        editor.append('H');
        editor.append('e');
        editor.append('l');
        editor.append('l');
        editor.append('o');
        System.out.println(editor.read()); // Hello

        editor.undo();
        editor.undo();
        System.out.println(editor.read()); // Hel

        editor.redo();
        System.out.println(editor.read()); // Hell

        editor.append('!');
        System.out.println(editor.read()); // Hell!

        editor.undo();
        editor.redo();
        System.out.println(editor.read()); // Hell!
    }
}
