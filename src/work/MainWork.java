package work;

import java.io.*;
import java.util.Scanner;

public class MainWork {
    public static void main(String[] args) {
        // 1. Настройка файлов
        String inputFile = "input.txt";
        String outputFile = "output.txt";
        
        // 2. Настройка ввода/вывода
        Scanner consoleScanner = new Scanner(System.in);
        PrintWriter writer = null;
        
        try {
            // 3. Проверка входного файла
            System.out.println("Пытаюсь прочитать файл: " + new File(inputFile).getAbsolutePath());
            
            if (!new File(inputFile).exists()) {
                System.out.println("ОШИБКА: Файл input.txt не найден!");
                System.out.println("Поместите файл в: " + new File("").getAbsolutePath());
                return;
            }

            // 4. Чтение массива
            System.out.println("\n=== Чтение массива из файла ===");
            int[] array = readArrayFromFile(inputFile);
            System.out.println("Прочитано " + array.length + " элементов");

            // 5. Настройка вывода в файл
            writer = new PrintWriter(new FileWriter(outputFile));
            System.out.println("\n=== Выбор метода поиска ===");
            
            // 6. Выбор метода
            System.out.println("Доступные методы:");
            System.out.println("1. Линейный поиск");
            System.out.println("2. Бинарный поиск (массив будет отсортирован)");
            System.out.print("Ваш выбор (1/2): ");
            
            int choice = consoleScanner.nextInt();
            writer.println("Выбран метод: " + (choice == 1 ? "линейный" : "бинарный"));

            // 7. Ввод значения для поиска
            System.out.print("\nВведите значение для поиска: ");
            int value = consoleScanner.nextInt();
            writer.println("Искомое значение: " + value);

            // 8. Выполнение поиска
            System.out.println("\n=== Результаты ===");
            int result = -1;
            
            if (choice == 1) {
                result = linearSearch(array, value);
            } else if (choice == 2) {
                array = sortArray(array);
                System.out.println("Массив отсортирован");
                writer.println("Отсортированный массив:");
                printArray(array, writer);
                result = binarySearch(array, value);
            }

            // 9. Вывод результатов
            String resultStr = "Результат: " + (result != -1 ? "найден на позиции " + result : "не найден");
            System.out.println(resultStr);
            writer.println(resultStr);

        } catch (IOException e) {
            System.out.println("ОШИБКА: " + e.getMessage());
        } finally {
            if (writer != null) writer.close();
            consoleScanner.close();
            System.out.println("\nПрограмма завершена. Результаты в " + outputFile);
        }
    }

    private static int[] readArrayFromFile(String filename) throws IOException {
        Scanner fileScanner = new Scanner(new File(filename));
        int size = fileScanner.nextInt();
        int[] array = new int[size];
        
        System.out.println("Содержимое файла:");
        for (int i = 0; i < size; i++) {
            array[i] = fileScanner.nextInt();
            System.out.print(array[i] + " ");
        }
        System.out.println();
        
        fileScanner.close();
        return array;
    }

    private static void printArray(int[] array, PrintWriter writer) {
        for (int num : array) {
            writer.print(num + " ");
        }
        writer.println();
    }
    
    // Сортировка массива
    private static int[] sortArray(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
        return array;
    }

    // Линейный поиск
    private static int linearSearch(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    // Бинарный поиск
    private static int binarySearch(int[] array, int value) {
        int left = 0;
        int right = array.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (array[mid] == value) {
                return mid;
            } else if (array[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}