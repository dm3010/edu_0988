import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class ProxyChecker {
    public static void main(String[] args) {

        try {

            FileReader fileIn = new FileReader("c:/temp/ip.txt");
            FileWriter fileOut = new FileWriter("c:/temp/good_ip.txt");
            BufferedReader br = new BufferedReader(fileIn);
            BufferedWriter bw = new BufferedWriter(fileOut);

            String line;
            while ((line = br.readLine()) != null) {

                String[] arr = line.split("\\s+");
                String ip = arr[0];
                int port = Integer.parseInt(arr[1]);
                if (checkProxy(ip, port)) {
                    bw.write(ip + ":" + port + "\n");
                    bw.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static boolean checkProxy(String ip, int port) {

        System.out.print("Проверяем ip " + ip + ":" + port);

        try {

            URL url = new URL("https://vozhzhaev.ru/test.php");
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(proxy);

            InputStream is = urlConnection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            rd.readLine();
            System.out.println("\tOk");
            return true;

        } catch (IOException e) {
            System.out.println("\tBad");
            return false;
        }
    }
}
