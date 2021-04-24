package EngimonHunter2000;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

/**
 * @author Cynthia Rusadi
 */

public class EngiDexTest {
    static EngiDex dex;
    
    @BeforeAll
    static void setDex() throws DexException {
        SkillDex skillDex = new SkillDex();
        skillDex.getDexDataFromFile("data/Test_Skills.csv");
        dex = new EngiDex(skillDex);
        assertDoesNotThrow(() -> dex.getDexDataFromFile("data/Test_Engimons.csv"));
    }

    @Test
    void testGetExistingEntity() {
        assertAll("Cek semua isinya dari nama engimon",
        () -> assertEquals(dex.getEntity("Zhonkli").getSpecies(), "Zhonkli"),
        () -> assertEquals(dex.getEntity("Zhonkli").getSlogan(), "Tendo Bonkshou"),
        () -> assertEquals(dex.getEntity("Zhonkli").getStarterSkill().getName(), "Fiery Thunder"),
        () -> assertEquals(dex.getEntity("Zhonkli").getListElement().size(), 1),
        () -> assertTrue(dex.getEntity("Zhonkli").getListElement().contains(Element.GROUND)));
    }

    @Test
    void testNonExistantGetEntity() {
        assertAll("Cek kalo entitynya ga ada",
        () -> assertEquals(dex.getEntity("albeidou"), null));
    }
}
