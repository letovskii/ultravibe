public class BracketChecker {
    public static boolean isValid(String expression) {
        char[] stack = new char[expression.length()];
        int top = -1;
        
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            
            if (ch == '(' || ch == '[' || ch == '{') {
                top++;
                stack[top] = ch;
            }
            else if (ch == ')' || ch == ']' || ch == '}') {
                if (top == -1) {
                    return false;
                }
                char popped = stack[top];
                top--;
                
                if (ch == ')' && popped != '(') {
                    return false;
                }
                if (ch == ']' && popped != '[') {
                    return false;
                }
                if (ch == '}' && popped != '{') {
                    return false;
                }
            }
        }
        
        return top == -1;
    }
    
    public static void main(String[] args) throws java.io.IOException {
        System.out.println("введите выражение для проверки скобок:");
        
        StringBuilder input = new StringBuilder();
        int ch;
        while ((ch = System.in.read()) != '\n' && ch != -1) {
            input.append((char) ch);
        }
        
        String expression = input.toString().trim();
        boolean result = isValid(expression);
        
        if (result) {
            System.out.println("скобки расставлены правильно");
        } else {
            System.out.println("ошибка в расстановке скобок");
        }
    }
}