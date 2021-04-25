package EngimonHunter2000;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author Cynthia Rusadi
 */

public class PlayerTest {
    static SkillDex skillDex;
    static EngiDex engiDex;
    static Player p;

    @BeforeAll
    static void setDex() throws DexException, EngimonSpeciesException, ElementsListException {
        skillDex = new SkillDex();
        skillDex.getDexDataFromFile("data/Test_Skills.csv");    
        engiDex = new EngiDex(skillDex);
        assertDoesNotThrow(() -> engiDex.getDexDataFromFile("data/Test_Engimons.csv"));
        p = new Player(engiDex);
    }

    @Test
    public void construct() {
        assertAll("Construct player",
        () -> assertEquals('a', p.getDir()),
        () -> assertEquals(1, p.getPositionX()),
        () -> assertEquals(1, p.getPositionY()));
    }

    @Test
    public void set_position() {
        p.setPositionX(5);
        p.setPositionY(9);
        assertAll("Set position",
        () -> assertEquals(5, p.getPositionX()),
        () -> assertEquals(9, p.getPositionY()));
    }

    @Test
    public void set_direction() {
        p.setDir('w');
        assertAll("Set direction",
        () -> assertEquals('w', p.getDir()));
    }
}
