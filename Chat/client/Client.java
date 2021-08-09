package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8188);

            System.out.println("Успешно подключен");
            DataInputStream in = new DataInputStream(socket.getInputStream()); // поток ввода
            DataOutputStream out = new DataOutputStream(socket.getOutputStream()); // поток вывода
            String response = in.readUTF();
            System.out.println(response);
            Scanner scanner = new Scanner(System.in);
            while(true){
                String consoletext = scanner.nextLine();
                out.writeUTF(consoletext);
                response = in.readUTF();
                System.out.println("Ответ сервера :" + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
