public class ProductCatalog {
    private Node root;
    
    private class Node {
        int id;
        String name;
        double price;
        Node left;
        Node right;
        
        Node(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }
    }
    
    public void insert(int id, String name, double price) {
        root = insertRec(root, id, name, price);
    }
    
    // рекурсивно ищем место для нового узла
    private Node insertRec(Node current, int id, String name, double price) {
        if (current == null) {
            return new Node(id, name, price);
        }
        if (id < current.id) {
            current.left = insertRec(current.left, id, name, price);
        } else if (id > current.id) {
            current.right = insertRec(current.right, id, name, price);
        }
        return current;
    }
    
    public String search(int id) {
        return searchRec(root, id);
    }
    
    // спускаемся по дереву, сравнивая id
    private String searchRec(Node current, int id) {
        if (current == null) {
            return "товар не найден";
        }
        if (id == current.id) {
            return "товар: " + current.name + ", цена: " + current.price + " руб.";
        }
        return id < current.id ? searchRec(current.left, id) : searchRec(current.right, id);
    }
    
    public static void main(String[] args) throws java.io.IOException {
        ProductCatalog catalog = new ProductCatalog();
        
        // добавляем товары с разными артикулами
        catalog.insert(5, "книга", 500);
        catalog.insert(3, "ручка", 50);
        catalog.insert(7, "тетрадь", 100);
        catalog.insert(2, "карандаш", 30);
        catalog.insert(4, "ластик", 20);
        catalog.insert(6, "линейка", 80);
        catalog.insert(8, "клей", 60);
        
        System.out.println("введите артикул товара:");
        StringBuilder input = new StringBuilder();
        int ch;
        while ((ch = System.in.read()) != '\n' && ch != -1) {
            input.append((char) ch);
        }
        
        int id = Integer.parseInt(input.toString().trim());
        System.out.println(catalog.search(id));
    }
}