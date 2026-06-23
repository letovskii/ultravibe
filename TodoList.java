public class TodoList {
    private Node head;
    
    private class Node {
        String task;
        boolean isDone;
        Node next;
        
        Node(String task) {
            this.task = task;
            this.isDone = false;
            next = null;
        }
    }
    
    public TodoList() {
        head = null;
    }
    
    // добавляем задачу в конец
    public void add(String task) {
        Node newNode = new Node(task);
        
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("добавлена задача: " + task);
    }
    
    // удаляем задачу по номеру
    public void remove(int index) {
        if (head == null) {
            System.out.println("список пуст");
            return;
        }
        
        if (index == 0) {
            System.out.println("удалена задача: " + head.task);
            head = head.next;
            return;
        }
        
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            if (current.next == null) {
                System.out.println("задача с таким номером не найдена");
                return;
            }
            current = current.next;
        }
        
        if (current.next == null) {
            System.out.println("задача с таким номером не найдена");
            return;
        }
        
        System.out.println("удалена задача: " + current.next.task);
        current.next = current.next.next;
    }
    
    // отмечаем задачу как выполненную
    public void markDone(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            if (current == null) {
                System.out.println("задача с таким номером не найдена");
                return;
            }
            current = current.next;
        }
        
        if (current == null) {
            System.out.println("задача с таким номером не найдена");
            return;
        }
        
        current.isDone = true;
        System.out.println("задача выполнена: " + current.task);
    }
    
    // выводим все задачи
    public void printAll() {
        if (head == null) {
            System.out.println("список задач пуст");
            return;
        }
        
        int i = 0;
        Node current = head;
        System.out.println("список задач:");
        while (current != null) {
            String status = current.isDone ? "[X]" : "[ ]";
            System.out.println(i + ". " + status + " " + current.task);
            current = current.next;
            i++;
        }
    }
    
    public static void main(String[] args) throws java.io.IOException {
        TodoList todo = new TodoList();
        
        todo.add("купить молоко");
        todo.add("сделать уроки");
        todo.add("позвонить маме");
        todo.add("убрать в комнате");
        todo.add("почитать книгу");
        
        todo.printAll();
        
        System.out.println("\nотмечаем задачу под номером 1 как выполненную");
        todo.markDone(1);
        todo.printAll();
        
        System.out.println("\nудаляем задачу под номером 3");
        todo.remove(3);
        todo.printAll();
        
        System.out.println("\nдобавляем новую задачу");
        todo.add("купить хлеб");
        todo.printAll();
    }
}