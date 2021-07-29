/*
ВНИМАНИЕ эта задача для ДЗ и не является обязательной.
Задание: 1. Создай массив чисел.
2. Добавь в массив 10 чисел с клавиатуры.
3. Вывести на экран длину самой длинной последовательности повторяющихся чисел в списке.
Пример для списка 2, 4, 4, 4, 8, 8, 4, 12, 12, 14:
3
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int[] a = new int[10];
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input array: ");
        for (int i = 0; i < a.length; i++) {
            a[i] = scanner.nextInt();
        }

        int count = 1;
        int maxCount = 1;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] != a[i + 1]) {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
            count++;
        }
        maxCount = Math.max(maxCount, count);
        System.out.println(maxCount);
    }
}
