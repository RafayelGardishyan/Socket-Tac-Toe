import java.util.HashMap;
import java.util.Map;

public class Games {
    private Map<Integer, Game> games;
    private int currentID;

    public Games() {
        this.games = new HashMap<>();
        this.currentID = 0;
    }

    public int newGame() {
        this.games.put(currentID, new Game());
        return currentID++;
    }

    public Game getGame(int id) {
        return this.games.get(id);
    }
}
