package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class Server {

    private Set<UserThread> userThreadSet = new HashSet<>();

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(8188)) {
            System.out.println("Сервер запущен");
            while (true) { // Бесконечный цикл ожидания подключения клиентов

                Socket socket = serverSocket.accept(); //ожидание подключения клиента

                new Thread(new UserThread(socket, this)).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getActiveUserNames(UserThread excludeUser) {
        return userThreadSet.stream().filter(u -> u != excludeUser).map(UserThread::getName).toList();
    }

    public void addUser(UserThread excludeUser) {
        userThreadSet.add(excludeUser);
        broadcast("К нам присоединился " + excludeUser.getName(), excludeUser);
    }

    public void removeUser(UserThread excludeUser) {
        userThreadSet.remove(excludeUser);
        broadcast(excludeUser.getName() + " покинул чат", excludeUser);
    }

    public void broadcast(String message, UserThread excludeUser) {
        for (UserThread user : userThreadSet) {
            if (user != excludeUser) user.sendMessage(message);
        }
    }

    public static void main(String[] args) {
        new Server().start();
    }
}

class UserThread implements Runnable {
    private final Socket socket;
    private final Server server;
    private String name;
    private DataInputStream in;
    private DataOutputStream out;

    public UserThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            name = in.readUTF();
            System.out.println("В чат подключился " + name);

            sendMessage("Добро пожаловать в чат, " + name);
            printActiveUserList();
            server.addUser(this);

            String request;
            do {
                request = in.readUTF();
                sendMessage(request.toUpperCase(Locale.ROOT));
            } while (!request.equals("exit"));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
                server.removeUser(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Клиент " + name + " отключился");
    }

    public void printActiveUserList() throws IOException {
        sendMessage("Сейчас онлайн: " + server.getActiveUserNames(this).toString());
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }
}

