public class BracketChecker {
    public static boolean isValid(String expression) {
        //массив для хранения открывающих скобок, размер как у строки
        char[] stack = new char[expression.length()];
        int top = -1; //указатель на вершину стека, -1 значит стек пуст
        
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            
            //если встретили открывающую скобку - кладем в стек
            if (ch == '(' || ch == '[' || ch == '{') {
                top++;
                stack[top] = ch;
            }
            //если встретили закрывающую скобку проверяем соответствие
            else if (ch == ')' || ch == ']' || ch == '}') {
                //если стек пуст, значит закрывающая скобка без пары
                if (top == -1) {
                    return false;
                }
                
                //достаем последнюю открывающую скобку
                char popped = stack[top];
                top--;
                
                //проверяем, что скобки одного типа
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
        
        //если стек пуст то все скобки закрыты, иначе есть незакрытые
        return top == -1;
    }
    
    public static void main(String[] args) throws java.io.IOException {
        System.out.println("введите выражение для проверки скобок:");
        
        //читаем ввод посимвольно до нажатия enter
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