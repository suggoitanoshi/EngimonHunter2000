package EngimonHunter2000;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author Cynthia Rusadi
 */

public class PlayerTest {
    @Test
    public void construct() {
        Player p = new Player();
        assertAll("Construct player",
        () -> assertEquals('a', p.getDir()),
        () -> assertEquals(1, p.getPositionX()),
        () -> assertEquals(1, p.getPositionY()));
    }

    @Test
    public void set_position() {
        Player p = new Player();
        p.setPositionX(5);
        p.setPositionY(9);
        assertAll("Set position",
        () -> assertEquals(5, p.getPositionX()),
        () -> assertEquals(9, p.getPositionY()));
    }

    @Test
    public void set_direction() {
        Player p = new Player();
        p.setDir('w');
        assertAll("Set direction",
        () -> assertEquals('w', p.getDir()));
    }
}
