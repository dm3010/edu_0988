/*
Задание: Дано число, например 31. Проверьте, что это число не делится ни на одно другое число кроме себя самого и единицы. То есть в нашем случае нужно проверить, что число 31 не делится на все числа от 2 до 30. Если число не делится - выведите 'false', а если делится - выведите 'true'.
*/

public class Main {
    public static void main(String[] args) {

        int num = 31;
        System.out.println(check(num));
    }

    private static boolean check(int a) {
        if (a == 0) return true;
        a = Math.abs(a);
        for (int i = 2; i <= a / 2; i++) {
            if (a % i == 0) {
                return true;
            }
        }
        return false;
    }
}
