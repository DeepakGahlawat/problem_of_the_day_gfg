import java.util.*;

/*
 Intuition:
 ----------
 Redundant brackets are those which do NOT add any new operation
 inside them.

 Examples:
 1. ((a+b))  → inner (a+b) already valid, outer () is redundant
 2. (a+(b)/c) → (b) is redundant because it has no operator
 3. (a+b+(c+d)) → NOT redundant, (c+d) is meaningful

 Key Idea:
 ---------
 - Use a stack.
 - Push all characters except ')'.
 - When ')' is encountered:
     - Pop until '(' is found.
     - Count operators (+, -, *, /) inside.
     - If no operator is found → redundant brackets exist.
*/

class ExpressionContainsRedundantBracketOrNot {

    public static boolean checkRedundancy(String s) {
        Stack<Character> st = new Stack<>();

        // Traverse each character in the expression
        for (char ch : s.toCharArray()) {

            // Push everything except closing bracket
            if (ch != ')') {
                st.push(ch);
            } 
            else {
                int operatorCount = 0;

                // Pop until '(' is found
                while (!st.isEmpty() && st.peek() != '(') {
                    char c = st.pop();

                    // Count operators inside brackets
                    if (c == '+' || c == '-' || c == '*' || c == '/') {
                        operatorCount++;
                    }
                }

                // Remove the opening '('
                st.pop();

                // If no operator found → redundant bracket
                if (operatorCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    // Optional main method for quick testing
    public static void main(String[] args) {
        System.out.println(checkRedundancy("((a+b))"));      // true
        System.out.println(checkRedundancy("(a+(b)/c)"));   // true
        System.out.println(checkRedundancy("(a+b+(c+d))")); // false
    }
}
