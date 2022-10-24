import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final String HOST = "127.0.0.1";
    public static final int PORT = 8080;

    public static Games games = new Games();

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(PORT);
        try {
            while (true) {
                Socket s = ss.accept();
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
