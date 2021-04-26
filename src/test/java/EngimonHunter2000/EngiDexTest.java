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
        SkillDex skillDex = new SkillDex("data/Test_Skills.csv");
        assertDoesNotThrow(() -> dex = new EngiDex(skillDex, "data/Test_Engimons.csv"));
    }

    @Test
    void testGetExistingEntity() {
        assertAll("Cek semua isinya dari nama engimon",
        () -> assertEquals(dex.getEntity("Zhonkli").getSpecies(), "Zhonkli"),
        () -> assertEquals(dex.getEntity("Zhonkli").getSlogan(), "Tendo Bonkshou"),
        () -> assertEquals(dex.getEntity("Zhonkli").getStarterSkill().getName(), "Fiery Thunder"),
        () -> assertEquals(dex.getEntity("Zhonkli").getListElement().getElementsList().size(), 1),
        () -> assertTrue(dex.getEntity("Zhonkli").getListElement().getElementsList().contains(Element.GROUND)));
    }

    @Test
    void testNonExistantGetEntity() {
        assertAll("Cek kalo entitynya ga ada",
        () -> assertEquals(dex.getEntity("albeidou"), null));
    }

    @Test
    void testGetEngimonName() {
        assertAll("Cek nama engimon",
        () -> assertEquals(dex.getEngimonNameFromElement(Element.ELECTRIC), "Picakhu"));
    }
}
