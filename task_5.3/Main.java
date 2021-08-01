/*
 * Задание: Дана коллекция с числами. Запишите в новую коллекцию только те числа, которые больше нуля и меньше 10-ти.
 * Коллекции вы создаёте сами
 */

import java.util.*;
import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите числа через пробел: ");
        String[] inputs = scanner.nextLine().split(" +");

        List<Integer> col1 = Arrays.stream(inputs).map(Integer::parseInt).collect(toList());
        List<Integer> col2 = col1.stream().filter(i -> i > 0 && i < 10).collect(toList());

        System.out.println(col2);
    }
}
