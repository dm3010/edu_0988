package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8188); //создаем серверный сокет
            System.out.println("Сервер запущен");
            while (true) { // Бесконечный цикл ожидания подключения клиентов
                Socket socket = serverSocket.accept(); //ожидание подключения клиента

                System.out.println("Клиент подключился");
                DataInputStream in = new DataInputStream(socket.getInputStream()); // поток ввода
                DataOutputStream out = new DataOutputStream((socket.getOutputStream())); // поток вывода
                out.writeUTF("Добро пожаловать на сервер");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (true) {
                                String request = in.readUTF();
                                out.writeUTF(request.toUpperCase(Locale.ROOT));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

