import java.util.*;

public class    TugasASD2 {
    
    public static Node buildExpressionTree(String expression) {
        Stack<Node> nodes = new Stack<>();
        Stack<Character> operators = new Stack<>();
        
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            
            if (c == ' ') {
                continue;
            } else if (Character.isDigit(c)) {
                int num = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num = num * 10 + Character.getNumericValue(expression.charAt(i));
                    i++;
                }
                i--;
                nodes.push(new Node(num));
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (!operators.empty() && operators.peek() != '(') {
                    char op = operators.pop();
                    Node right = nodes.pop();
                    Node left = nodes.pop();
                    nodes.push(new Node(op, left, right));
                }
                operators.pop();
            } else {
                while (!operators.empty() && getPrecedence(c) <= getPrecedence(operators.peek())) {
                    char op = operators.pop();
                    Node right = nodes.pop();
                    Node left = nodes.pop();
                    nodes.push(new Node(op, left, right));
                }
                operators.push(c);
            }
        }
        
        while (!operators.empty()) {
            char op = operators.pop();
            Node right = nodes.pop();
            Node left = nodes.pop();
            nodes.push(new Node(op, left, right));
        }
        
        return nodes.pop();
    }
    
    public static int evaluateExpressionTree(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.value;
        }
        int leftValue = evaluateExpressionTree(root.left);
        int rightValue = evaluateExpressionTree(root.right);
        char operator = root.operator;
        switch (operator) {
            case '+':
                return leftValue + rightValue;
            case '-':
                return leftValue - rightValue;
            case '*':
                return leftValue * rightValue;
            case '/':
                if (rightValue == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return leftValue / rightValue;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
    
    public static int getPrecedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  
        String expression = scanner.nextLine();
        Node root = buildExpressionTree(expression);
        int result = evaluateExpressionTree(root);
        System.out.println(result);
    }
    
    static class Node {
        int value;
        char operator;
        Node left;
        Node right;
        
        Node(int value) {
            this.value = value;
        }
        
        Node(char operator, Node left, Node right) {
            this.operator = operator;
            this.left = left;
            this.right = right;
        }
    }
}
