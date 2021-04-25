package EngimonHunter2000;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

/**
 * @author Josep Marcello
 */
public class SkillDexTest {
    static SkillDex dex;

    @BeforeAll
    static void setDex() {
        assertDoesNotThrow(() -> dex = new SkillDex("data/Test_Skills.csv"));
    }

    @Test
    void testGetExistingEntity() {
        assertAll("Nyoba dapetin nama skill yang ada di Dex",
        () -> assertEquals(dex.getEntity("THUNDAAA").getName(), "THUNDAAA"),
        () -> assertEquals(dex.getEntity("THUNDAAA").getBasePower(), 50),
        () -> assertEquals(dex.getEntity("THUNDAAA").getElements().size(), 1),
        () -> assertTrue(dex.getEntity("THUNDAAA").getElements().contains(Element.ELECTRIC)));
    }

    @Test
    void testNonExistantGetEntity() {
        assertAll("Nyoba dapetin nama skill yang ga ada di Dex",
        () -> assertEquals(dex.getEntity("asadkhdflhdaskfklsfhskdlfh"), null));
    }
}
