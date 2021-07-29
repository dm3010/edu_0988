/*
Задание: Пользователь вводит сумму вклада и процент, который будет начисляться ежегодно. Отобразить размер вклада поочередно на ближайшие 5 лет.
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите сумму вклада: ");
        double deposit = scanner.nextDouble();

        System.out.print("Введите процентную ставку: ");
        double rate = scanner.nextDouble();

        int years = 5;

        for (int i = 1; i <= years; i++) {
            deposit *= (100 + rate) / 100;
            System.out.printf("Сумма вклада через %d год(а): %.2f%n", i, deposit);
        }
    }
}
