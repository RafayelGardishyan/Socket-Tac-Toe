import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Player extends Thread {
    private Game game;
    private boolean isx;
    private final PrintWriter pw;
    private final Scanner sc;

    private final String[] welcomeText = {
        "Welcome to sock-tac-toe",
        "- Type 'n' to create a new game",
        "- Type 'j' to join a game",
        "- Type 'e' to exit"
    };


    public Player(Socket s) throws IOException {
        this.pw = new PrintWriter(s.getOutputStream());
        this.sc = new Scanner(s.getInputStream());
    }

    public void run() {
        for (String line :
                welcomeText) {
            sendMessage(line, false);
        }
        sendMessage("", true);
        boolean gotResults = false;
        while (!gotResults) {
            switch (waitOnInput()) {
                case "n" -> {
                    gotResults = true;
                    int id = Server.games.newGame();
                    init(Server.games.getGame(id));
                    sendMessage(
                            String.format(
                                    "You joined game [%s]. Waiting for an opponent", id), false
                    );
                }
                case "j" -> {
                    gotResults = true;
                    askForId();
                }
                case "e" -> {
                    gotResults = true;
                    exitClient();
                }
                default -> sendMessage("Incorrect Input", true);
            }
        }

        sendMessage(String.format("You are playing '%s'", isx?"X":"O"), false);

        while (game.wins().equals("-1")) {
            System.out.print(".");
            System.out.print(".");
            System.out.print(".\r");
            if (game.getLastmove().equals(isx?"O":"X")){
                sendMessage(game.getBoard(), false);
                sendMessage("It's your turn. \n" +
                        "Send your move in the format 'A1' where A stands for the column and 1 for the row",
                        true);
                gotResults = false;
                while (!gotResults) {
                    try {
                        game.move(waitOnInput().strip(), isx);
                        sendMessage(game.getBoard(), false);
                        sendMessage("Your move was recorded. Waiting on the opponent", false);
                        gotResults = true;
                    } catch (Exception e) {
                        sendMessage(
                                String.format("%s. Try again", e.getMessage()), true);
                    }
                }
            }
        }
        sendMessage(game.getBoard(), false);
        sendMessage(
                String.format(
                        "Game is over. %s has won",
                        game.wins().equals("tie")? "Nobody" : game.wins()
                ), false);
        sendMessage("[exit]", false);
    }

    private void exitClient() {
        sendMessage("[exit]", false);
    }

    private void askForId() {
        sendMessage("Please enter the game ID", true);
        boolean gotResults = false;
        while (!gotResults) {
            try{
                int id = Integer.parseInt(waitOnInput().strip());
                init(Server.games.getGame(id));
                sendMessage(String.format("You joined game [%s]. Starting...", id), false);
                sendMessage("Waiting on the opponent", false);
                gotResults = true;
            } catch (Exception e) {
                sendMessage("Something went wrong. Try again", true);
            }
        }
    }

    public void init(Game g) {
        this.game = g;
        this.isx = g.join();
        System.out.printf("Player : I am %s\n", isx?"X":"O");
    }

    public void sendMessage(String message, boolean expectInput) {
        pw.write(message + (message.length()!=0?"\n":""));
        pw.flush();
        if (expectInput) {
            pw.write("[input]\n");
            pw.flush();
        }
    }

    public String waitOnInput() {
        while (!this.sc.hasNext()) {
        }
        String n = sc.next();
        System.out.printf("\nPlayer : %s\n", n);
        return n;
    }

    public void move(String cell) {
        game.move(cell, isx);
    }

    public boolean myTurn() {
        return Objects.equals(game.getLastmove(), isx ? "X" : "O");
    }
}
