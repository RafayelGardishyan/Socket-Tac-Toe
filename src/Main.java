import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Game g = new Game();

        System.out.println(g.getBoard());
        System.out.printf("%s wins", Objects.equals(g.wins(), "-1") ? "Nobody" : g.wins());

        g.move("A1", true);
        System.out.println(g.getBoard());
        System.out.printf("%s wins", Objects.equals(g.wins(), "-1") ? "Nobody" : g.wins());

        g.move("C2", false);
        System.out.println(g.getBoard());
        System.out.printf("%s wins", Objects.equals(g.wins(), "-1") ? "Nobody" : g.wins());

        g.move("C1", false);
        System.out.println(g.getBoard());
        System.out.printf("%s wins", Objects.equals(g.wins(), "-1") ? "Nobody" : g.wins());

        g.move("C3", false);
        System.out.println(g.getBoard());
        System.out.printf("%s wins", Objects.equals(g.wins(), "-1") ? "Nobody" : g.wins());

    }
}