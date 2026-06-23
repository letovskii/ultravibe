public class SortComparison {
    
    // сортировка пузырьком
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    
    // быстрая сортировка
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
    
    // создаем массив случайных чисел
    public static int[] generateArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }
        return arr;
    }
    
    // копируем массив
    public static int[] copyArray(int[] arr) {
        int[] copy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }
    
    // проверяем, отсортирован ли массив
    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        int[] sizes = {10, 100, 1000, 10000, 50000};
        
        System.out.println("=== СРАВНЕНИЕ СКОРОСТИ СОРТИРОВКИ ===\n");
        
        for (int size : sizes) {
            System.out.println("Размер массива: " + size);
            
            int[] original = generateArray(size);
            
            // тестируем пузырьковую сортировку
            int[] arrBubble = copyArray(original);
            long startBubble = System.nanoTime();
            bubbleSort(arrBubble);
            long endBubble = System.nanoTime();
            double timeBubble = (endBubble - startBubble) / 1_000_000.0;
            
            // тестируем быструю сортировку
            int[] arrQuick = copyArray(original);
            long startQuick = System.nanoTime();
            quickSort(arrQuick, 0, arrQuick.length - 1);
            long endQuick = System.nanoTime();
            double timeQuick = (endQuick - startQuick) / 1_000_000.0;
            
            System.out.println("  Пузырьковая: " + String.format("%.2f", timeBubble) + " мс");
            System.out.println("  Быстрая:     " + String.format("%.2f", timeQuick) + " мс");
            
            if (timeBubble > 0 && timeQuick > 0) {
                double ratio = timeBubble / timeQuick;
                System.out.println("  Быстрее в:   " + String.format("%.1f", ratio) + " раз");
            }
            
            System.out.println();
        }
    }
}