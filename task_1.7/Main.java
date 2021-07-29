/* Задача наполнить корабельный бассейн. Нужно посчитать, сколько литров воды нужно для заполнения бассейна до бортов.
Известно, что бассейн имеет линейные размеры a × b × c, заданные в метрах.
Эти размеры передаются запрашиваются у пользователя. Прграмма должена вывести на экран количество литров воды,
которое нужно для наполнения бассейна.
Пример:
Пользователь даёт числа 25, 5, 2.
Пример вывода:
250000 */


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размер a: ");
        int a = scanner.nextInt();
        System.out.print("Введите размер b: ");
        int b = scanner.nextInt();
        System.out.print("Введите размер c: ");
        int c = scanner.nextInt();

        System.out.println("Количество литров: " + (a * b * c * 1000));
    }
}
