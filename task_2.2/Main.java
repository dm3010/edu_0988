/*Выведи на экран таблицу умножения 10 на 10 в следующем виде:
1 2 3 4 ...
2 4 6 8 ...
3 6 9 12 ...
4 8 12 16 ...
... */

import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {

        int size = 10;

        for (int i = 1; i <= size; i++) {
            StringJoiner joiner = new StringJoiner(" ");
            for (int j = 1; j <= size; j++) {
                joiner.add(Integer.toString(i * j));
            }
            System.out.println(joiner);
        }
    }
}
