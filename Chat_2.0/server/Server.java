package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

    final int PORT = 8188;

    private Map<String, UserThread> users = new HashMap<>();

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен");

            while (!serverSocket.isClosed()) { // Бесконечный цикл ожидания подключения клиентов

                Socket socket = serverSocket.accept(); //ожидание подключения клиента
                new Thread(new UserThread(socket, this)).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getActiveUserNames(UserThread excludeUser) {
        return users.values().stream().filter(s -> s != excludeUser).map(UserThread::getName).toList();
    }

    public boolean send(String userName, String message) {
        if (users.containsKey(userName.toUpperCase())) {
            users.get(userName.toUpperCase()).sendMessage(message);
            return true;
        }
        return false;
    }

    public boolean addUser(UserThread user) {
        boolean result = false;
        if (!users.containsKey(user.getName().toUpperCase())) {
            users.put(user.getName().toUpperCase(), user);
            broadcast("К нам присоединился " + user.getName(), user);
            result = true;
        }
        return result;
    }

    public void removeUser(UserThread user) {
        users.remove(user.getName().toUpperCase());
        broadcast(user.getName() + " покинул чат", user);
    }

    public UserThread getUser(String userName) {
        return users.get(userName.toUpperCase());
    }

    public void broadcast(String message, UserThread excludeUser) {
        for (UserThread user : users.values()) {
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

            login();

            String request;
            do {
                request = in.readUTF().trim();
                if (request.startsWith("/m ")) {
                    String toUser = request.split(" ")[1];
                    String message = (request + " ").split(" ", -1)[2];
                    if (server.send(toUser, "*" + name + ": " + message)) {
                        sendMessage("Вы -> " + toUser + ": " + message);
                        continue;
                    }
                    ;
                }
                sendMessage("Вы: " + request);
                server.broadcast(getName() + ": " + request, this);
            } while (!request.equals("/exit"));

        } catch (IOException e) {
            // e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        server.removeUser(this);
        System.out.println("Клиент " + name + " отключился");
    }

    private void login() throws IOException {
        sendMessage("Представьтесь, пожалуйста");
        do {
            name = in.readUTF();
            if (server.addUser(this)) break;
            else {
                sendMessage("Ошибка авторизации. Возможно это имя пользователя уже используется. Повторите попытку");
            }
        } while (true);

        System.out.println("В чат подключился " + name);
        sendMessage("Добро пожаловать в чат, " + name);
        printActiveUserList();
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

