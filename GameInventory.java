public class GameInventory {
    private Node[] buckets;
    private int size;
    private int capacity;
    
    private class Node {
        String itemName;
        int quantity;
        Node next;
        
        Node(String itemName, int quantity) {
            this.itemName = itemName;
            this.quantity = quantity;
            next = null;
        }
    }
    
    public GameInventory() {
        capacity = 10;
        buckets = new Node[capacity];
        size = 0;
    }
    
    private int getHash(String itemName) {
        return Math.abs(itemName.hashCode()) % capacity;
    }
    
    // добавляем предмет или увеличиваем количество
    public void addItem(String itemName, int quantity) {
        if (quantity <= 0) {
            System.out.println("количество должно быть больше 0");
            return;
        }
        
        int index = getHash(itemName);
        Node current = buckets[index];
        
        // ищем предмет в корзине
        while (current != null) {
            if (current.itemName.equals(itemName)) {
                current.quantity += quantity;
                System.out.println("добавлено " + quantity + " " + itemName + ", теперь: " + current.quantity);
                return;
            }
            current = current.next;
        }
        
        // если предмет не найден - создаем новый
        Node newNode = new Node(itemName, quantity);
        newNode.next = buckets[index];
        buckets[index] = newNode;
        size++;
        System.out.println("добавлен новый предмет: " + itemName + " в количестве " + quantity);
    }
    
    // используем предмет (уменьшаем количество)
    public void useItem(String itemName, int quantity) {
        if (quantity <= 0) {
            System.out.println("количество должно быть больше 0");
            return;
        }
        
        int index = getHash(itemName);
        Node current = buckets[index];
        
        while (current != null) {
            if (current.itemName.equals(itemName)) {
                if (current.quantity < quantity) {
                    System.out.println("недостаточно предметов. есть: " + current.quantity + ", нужно: " + quantity);
                    return;
                }
                
                current.quantity -= quantity;
                System.out.println("использовано " + quantity + " " + itemName + ", осталось: " + current.quantity);
                
                // если количество стало 0 - удаляем предмет
                if (current.quantity == 0) {
                    removeItem(itemName);
                }
                return;
            }
            current = current.next;
        }
        
        System.out.println("предмет не найден");
    }
    
    // полностью удаляем предмет из инвентаря
    public void removeItem(String itemName) {
        int index = getHash(itemName);
        Node current = buckets[index];
        Node prev = null;
        
        while (current != null) {
            if (current.itemName.equals(itemName)) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                System.out.println("предмет удален: " + itemName);
                return;
            }
            prev = current;
            current = current.next;
        }
        
        System.out.println("предмет не найден");
    }
    
    // проверяем наличие предмета
    public boolean hasItem(String itemName) {
        int index = getHash(itemName);
        Node current = buckets[index];
        
        while (current != null) {
            if (current.itemName.equals(itemName)) {
                return current.quantity > 0;
            }
            current = current.next;
        }
        return false;
    }
    
    // получаем количество предмета
    public int getQuantity(String itemName) {
        int index = getHash(itemName);
        Node current = buckets[index];
        
        while (current != null) {
            if (current.itemName.equals(itemName)) {
                return current.quantity;
            }
            current = current.next;
        }
        return 0;
    }
    
    // выводим инвентарь
    public void printInventory() {
        if (size == 0) {
            System.out.println("инвентарь пуст");
            return;
        }
        
        System.out.println("===== ИНВЕНТАРЬ =====");
        for (int i = 0; i < capacity; i++) {
            Node current = buckets[i];
            while (current != null) {
                System.out.println(current.itemName + " x" + current.quantity);
                current = current.next;
            }
        }
        System.out.println("===================");
    }
    
    // общее количество предметов в инвентаре
    public int getTotalItems() {
        int total = 0;
        for (int i = 0; i < capacity; i++) {
            Node current = buckets[i];
            while (current != null) {
                total += current.quantity;
                current = current.next;
            }
        }
        return total;
    }
    
    public static void main(String[] args) throws java.io.IOException {
        GameInventory inventory = new GameInventory();
        
        System.out.println("добавляем предметы:");
        inventory.addItem("зелье здоровья", 5);
        inventory.addItem("зелье маны", 3);
        inventory.addItem("золотая монета", 100);
        inventory.addItem("меч", 1);
        inventory.addItem("зелье здоровья", 2); // добавляем к существующему
        
        inventory.printInventory();
        
        System.out.println("\nиспользуем предметы:");
        inventory.useItem("зелье здоровья", 3);
        inventory.useItem("зелье маны", 1);
        inventory.useItem("зелье скорости", 1); // такого нет
        
        inventory.printInventory();
        
        System.out.println("\nпроверяем наличие:");
        System.out.println("есть меч? " + inventory.hasItem("меч"));
        System.out.println("есть лук? " + inventory.hasItem("лук"));
        System.out.println("количество зелья здоровья: " + inventory.getQuantity("зелье здоровья"));
        System.out.println("общее количество предметов: " + inventory.getTotalItems());
        
        System.out.println("\nудаляем предмет:");
        inventory.removeItem("меч");
        inventory.printInventory();
        
        System.out.println("\nвведите название предмета для проверки:");
        StringBuilder input = new StringBuilder();
        int ch;
        while ((ch = System.in.read()) != '\n' && ch != -1) {
            input.append((char) ch);
        }
        
        String item = input.toString().trim();
        if (inventory.hasItem(item)) {
            System.out.println("у вас есть " + item + " в количестве " + inventory.getQuantity(item));
        } else {
            System.out.println("у вас нет такого предмета");
        }
    }
}