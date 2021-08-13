package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private Scanner scanner;
    private final Runnable listener = () -> {
        try (DataInputStream in = new DataInputStream(socket.getInputStream())) {
            while (!socket.isClosed()) {
                System.out.print(in.readUTF() + "\n: ");
            }
        } catch (IOException e) {
            System.out.println("Соединение прервано");
            //e.printStackTrace();
        }
    };
    private final Runnable writer = () -> {
        try (DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
            String input;
            do {
                input = scanner.nextLine();
                out.writeUTF(input);
            }
            while (!socket.isClosed());
        } catch (IOException e) {
            // e.printStackTrace();
        }
    };

    public void start() {
        try {
            scanner = new Scanner(System.in);
            socket = new Socket("18.188.76.190", 8188);
            new Thread(listener).start();
            new Thread(writer).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client().start();
    }

}
