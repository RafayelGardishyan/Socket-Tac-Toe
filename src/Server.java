import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final String HOST = "";
    public static final int PORT = 8080;

    public static Games games = new Games();

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(PORT);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("test.txt")));
        try {
            while (true) {
                Socket s = ss.accept();
                pw.println("Client has connected!");
                pw.flush();
                pw.close();
                System.out.println("Client has connected!");
                Thread th = new Player(s);
                th.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ss.close();
    }
}
