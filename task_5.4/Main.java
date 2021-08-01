/*
1. Ввести путь к файлу с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
-2
11
3
-5
2
10
Пример вывода:
-2
2
8
10
*/

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя файла: ");
        File file = new File(scanner.next());

        BufferedReader br = new BufferedReader(new FileReader(file));
        br.lines().mapToInt(Integer::parseInt).filter(v -> v % 2 == 0).sorted().forEach(System.out::println);
    }
}
