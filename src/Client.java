import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Client {
    public static void main(final String[] args) throws IOException {
        Socket s = new Socket(Server.HOST, Server.PORT);

        Scanner sc = new Scanner(s.getInputStream());
        sc.useDelimiter(" ");
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            if (sc.hasNext()) {
                String rec = sc.nextLine();
                if (Objects.equals(rec, "[input]")) {
                    System.out.print("-> ");
                    pw.write(input.next() + "\n");
                    pw.flush();
                } else if (Objects.equals(rec, "[exit]")) {
                    s.close();
                    System.exit(0);
                } else {
                    System.out.println(rec);
                }
            }
        }


    }
}
