public class MyQueue {
    private int[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;
    
    //конструктор, создаем очередь с заданной вместимостью
    public MyQueue(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }
    
    //добавляем элемент в конец очереди
    public void enqueue(int element) {
        if (isFull()) {
            System.out.println("очередь переполнена.");
            return;
        }
        rear = (rear + 1) % capacity; //кольцевое смещение
        queue[rear] = element;
        size++;
    }
    
    //удаляем и возвращаем элемент из начала очереди
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("очередь пуста.");
            return -1;
        }
        int result = queue[front];
        front = (front + 1) % capacity; //кольцевое смещение
        size--;
        return result;
    }
    
    //смотрим первый элемент без удаления
    public int peek() {
        if (isEmpty()) {
            System.out.println("очередь пуста.");
            return -1;
        }
        return queue[front];
    }
    
    //проверяем, пуста ли очередь
    public boolean isEmpty() {
        return size == 0;
    }
    
    //проверяем, заполнена ли очередь
    public boolean isFull() {
        return size == capacity;
    }
    
    //получаем размер очереди
    public int getSize() {
        return size;
    }
    
    //выводим все элементы очереди
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("очередь пуста.");
            return;
        }
        System.out.print("элементы очереди: ");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            System.out.print(queue[index] + " ");
        }
        System.out.println();
    }
    
}