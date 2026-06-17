import java.util.Stack;
import java.util.Scanner;

public class BracketChecker {
    public static boolean isValid(String expression) {
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }
            else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                
                char top = stack.pop();
                
                if (ch == ')' && top != '(') {
                    return false;
                }
                if (ch == ']' && top != '[') {
                    return false;
                }
                if (ch == '}' && top != '{') {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("введите выражение для проверки скобок:");
        String input = scanner.nextLine();
        
        boolean result = isValid(input);
        
        if (result) {
            System.out.println("скобки расставлены правильно");
        } else {
            System.out.println("ошибка в расстановке скобок");
        }
        
        scanner.close();
    }
}