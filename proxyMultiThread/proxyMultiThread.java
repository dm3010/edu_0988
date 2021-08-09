import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.concurrent.*;

public class proxyMultiThread {

    static final int THREADS_COUNT = 20;
    static final String INPUT_FILE_NAME = "c:/temp/ip.txt";
    static final String OUTPUT_FILE_NAME = "c:/temp/good_ip.txt";

    public static void main(String[] args) {

        BlockingQueue<String> readQueue = new LinkedBlockingQueue<>(10);
        BlockingQueue<String> writeQueue = new LinkedBlockingQueue<>(10);

        Reader reader = new Reader(INPUT_FILE_NAME, readQueue);
        Writer writer = new Writer(OUTPUT_FILE_NAME, writeQueue, THREADS_COUNT);

        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                try (BufferedReader br = new BufferedReader(
                        new FileReader(INPUT_FILE_NAME))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        readQueue.put(line);
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        readQueue.put("EOF"); // На случай поломки Reader'а флаг для остальных на завершение
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }).start();
        */
        //^^^^^^^^^^^ 3-й вариант

        new Thread(reader, "Reader").start(); // 2-й вариант

        new Thread(writer, "Writer").start();

        Checker checker = new Checker(readQueue, writeQueue);
        for (int i = 0; i < THREADS_COUNT; i++) {
            new Thread(checker, "Checker" + i).start();
        }
    }
}

class Reader implements Runnable {
    private final String filename;
    private final BlockingQueue<String> queue;

    Reader(String filename, BlockingQueue<String> queue) {
        this.queue = queue;
        this.filename = filename;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(
                new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                queue.put(line);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                queue.put("EOF"); // На случай поломки Reader'а флаг для остальных на завершение
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

class Writer implements Runnable {
    private final String filename;
    private final BlockingQueue<String> queue;
    private int count;

    Writer(String filename, BlockingQueue<String> queue, int eofCount) {
        this.queue = queue;
        this.count = eofCount;
        this.filename = filename;
    }

    @Override
    public void run() {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(filename))) {
            String line;
            while (count > 0) {
                line = queue.take();
                if (line.equals("EOF")) count--;
                else {
                    bw.write(line);
                    bw.flush();
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Checker implements Runnable {
    private final BlockingQueue<String> inQueue, outQueue;

    public Checker(BlockingQueue<String> input, BlockingQueue<String> output) {
        this.inQueue = input;
        this.outQueue = output;
    }

    @Override
    public void run() {
        try {
            String line;
            while (!(line = inQueue.take()).equals("EOF")) {

                String[] arr = line.split("\\s+");
                String ip = arr[0];
                int port = Integer.parseInt(arr[1]);
                boolean check = checkProxy(ip, port);

                if (check) {
                    outQueue.put(ip + ":" + port + "\n");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                inQueue.put("EOF");
                outQueue.put("EOF");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkProxy(String ip, int port) {
        try {
            URL url = new URL("https://vozhzhaev.ru/test.php");
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(proxy);

            InputStream is = urlConnection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            rd.readLine();
            System.out.println(ip + ":" + port + "\tOk");
            return true;

        } catch (IOException e) {
            System.out.println(ip + ":" + port + "\tBad");
            return false;
        }
    }
}