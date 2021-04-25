package EngimonHunter2000;
import java.util.ArrayList;

public class GameState {
    Player player;
    // ArrayList<Engimon> wildEngimon;

    public GameState() {
        player = new Player();
    }

    public GameState(Player p) {
        player = p;
        // wildEngimon = w;
    }

    public Player getPlayer() {
        return player;
    }
}
