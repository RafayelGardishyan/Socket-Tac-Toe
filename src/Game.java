import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private List<String> xes;
    private List<String> os;
    private int[][] sums;
    private static final String[] abc = new String[] {"A", "B", "C"};

    private String lastmove = "";

    private int joined = 0;

    public Game() {
        this.xes = new ArrayList<>();
        this.os = new ArrayList<>();
        sums = new int[8][2];
    }

    public boolean join() {
        if (joined == 1) lastmove = "O";
        if (joined == 2)
            throw new IllegalArgumentException("There are already two players in this game");
        return joined++ == 0;
    }

    public void move(String move, Boolean isx) {
        String col = move.substring(0, 1);
        int row = Integer.parseInt(move.substring(1, 2));
        int x = 0;
        if (this.xes.contains(move) || this.os.contains(move)) {
            throw new IllegalArgumentException("This cell is already occupied");
        }
        if (isx && !lastmove.equals("X")) {
            this.xes.add(move);
            lastmove = "X";
        } else if (!isx && !lastmove.equals("O")){
            this.os.add(move);
            x = 1;
            lastmove = "O";
        } else {
            throw new IllegalArgumentException("Last move was already taken by this player");
        }
        updateWins(col, row, x);
    }

    private void updateWins(String col, int row, int x) {
        switch (col) {
            case "A" : sums[0][x]++;
            case "B" : sums[1][x]++;
            case "C" : sums[2][x]++;
        }
        switch (row) {
            case 1 : sums[3][x]++;
            case 2 : sums[4][x]++;
            case 3 : sums[5][x]++;
        }
        switch (new String(col + row)) {
            case "A1", "C3" : sums[6][x]++;
            case "A3", "C1" : sums[7][x]++;
            case "B2" : {
                sums[6][x]++;
                sums[7][x]++;
            }
        }
    }

    public String getBoard() {
        StringBuilder board = new StringBuilder("\n  | A | B | C |\n");
        board.append("--+---+---+---+-\n");
        for (int i = 0; i < 3; i++) {
            board.append(String.format("%s |", i + 1));
            for (String l :
                    abc) {
                if (this.xes.contains(String.format("%s%s", l, i + 1))) {
                    board.append(" X |");
                } else if (this.os.contains(String.format("%s%s", l, i + 1))) {
                    board.append(" O |");
                } else {
                    board.append("   |");
                }
            }
            if (i != 2) {
                board.append("\n--+---+---+---+-\n");
            }
        }
        return board.toString();
    }

    public String wins() {
        int totalsum = 0;
        for (int[] pos:
             sums) {
            if (pos[0] == 3){
                return "X";
            }
            if (pos[1] == 3) {
                return "O";
            }
            totalsum += pos[0] + pos[1];
        }
        if (totalsum == 24) return "tie";
        return "-1";
    }

    public String getLastmove() {
        return this.lastmove;
    }
}
