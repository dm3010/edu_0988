import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите login:");
        String login = scanner.nextLine();
        System.out.println("Введите password:");
        String pass = scanner.nextLine();
        if (login.equals("admin") && pass.equals("123456")) {
            System.out.println("confirmed admin");
        }else if(login.equals("user") && pass.equals("123")) {
            System.out.println("confirmed user");
        }
        else {
            System.out.println("Доступ запрещен");
        }
        /*
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        if(a<b){
            System.out.println("Если true");
        }else {
            System.out.println("Если false");
        }

*/
        //        System.out.println("Hello, world");
//        String name = "Ivan";
//        System.out.println("Hello " + name);
//        int num1 = 17;
//        int num2 = 5;
//        System.out.println(num1 + num2);
//        System.out.println(num1-num2);
//        System.out.println(num1*num2);
//        System.out.println(num1/num2);
//        System.out.println(num1%num2);
//        System.out.println(num1>num2);
//        System.out.println(num1<num2);
//        System.out.println(num1==num2);
//        System.out.println((num1+10)*num2);
    }
}
