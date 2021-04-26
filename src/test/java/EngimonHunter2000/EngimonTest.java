/**
 * TODO: Add skill item/engimon to test this class
 */
package EngimonHunter2000;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EngimonTest {
    static SkillDex skillDex;
    static EngiDex dex;

    @BeforeAll
    public static void initAll() {
        assertDoesNotThrow(() -> skillDex = new SkillDex("data/Test_Skills.csv"));
        assertDoesNotThrow(() -> dex = new EngiDex(skillDex, "data/Test_Engimons.csv"));

    }

    @Test
    void testDefaultConstructor() throws EngimonSpeciesException, EngimonException, ElementsListException {
        Engimon engi1 = new Engimon(dex, "Picakhu", "Testname");
        assertAll("Cek setiap fungsi getter pada class engimon, untuk constructor inventory",
                () -> assertEquals(engi1.getName(), "Testname"), () -> assertEquals(engi1.getLvl(), 1),
                () -> assertEquals(engi1.getElementCount(), 1), () -> assertEquals(engi1.getSkillCount(), 1),
                () -> assertEquals(engi1.getPosition().getX(), -1), () -> assertEquals(engi1.getPosition().getY(), -1),
                () -> assertEquals(engi1.getSkillByString("THUNDAAA").getName(), "THUNDAAA"),
                () -> assertEquals(engi1.getLives(), 3), () -> assertEquals(engi1.getBattlePower(1.0), 51),
                () -> assertEquals(engi1.getParents()[0], "Picakhu"),
                () -> assertEquals(engi1.getParents()[1], "Picakhu"));
        engi1.setName("Ayam");
        engi1.setPos(2, 3);
        engi1.setLives(2);
        engi1.addLevel(10);
        assertAll("Check add level engimon", () -> assertEquals(engi1.getLvl(), 11),
                () -> assertEquals(engi1.getPosition().getX(), 2), () -> assertEquals(engi1.getPosition().getY(), 3),
                () -> assertEquals(engi1.getName(), "Ayam"), () -> assertEquals(engi1.getLives(), 3));
        engi1.addExp(325);
        assertAll("Check add exp engimon", () -> assertEquals(engi1.getLvl(), 14),
                () -> assertEquals(engi1.getExp(), 25), () -> assertEquals(engi1.getCExp(), 1625));
        engi1.addSkill(new SkillEngimon(skillDex.getEntity("Tackle"), 2));
        assertAll("Check add skill engimon", () -> assertEquals(engi1.getSkillCount(), 2),
                () -> assertEquals(engi1.getSkillByString("Tackle").getMasteryLevel(), 2));
        engi1.delSkill(engi1.getSkillByString("Tackle"));
        assertThrows(EngimonException.class, () -> engi1.getSkillByString("Tackle"));

    }

    @Test
    void testFullConstructor() throws EngimonSpeciesException, EngimonException, ElementsListException {
        Position pos = new Position(2, 3);
        Engimon engi2 = new Engimon(dex, "Picakhu", "AAA", 20, 25, pos, 2, "Rachui", "Rachua");
        assertAll("Cek setiap fungsi getter pada class engimon, untuk constructor inventory",
                () -> assertEquals(engi2.getName(), "AAA"), () -> assertEquals(engi2.getLvl(), 20),
                () -> assertEquals(engi2.getElementCount(), 1), () -> assertEquals(engi2.getSkillCount(), 1),
                () -> assertEquals(engi2.getPosition().getX(), 2), () -> assertEquals(engi2.getPosition().getY(), 3),
                () -> assertEquals(engi2.getSkillByString("THUNDAAA").getName(), "THUNDAAA"),
                () -> assertEquals(engi2.getLives(), 2), () -> assertEquals(engi2.getBattlePower(1.0), 70),
                () -> assertEquals(engi2.getParents()[0], "Rachui"),
                () -> assertEquals(engi2.getParents()[1], "Rachua"));
        engi2.setName("Picha");
        engi2.setPos(5, 5);
        engi2.setLives(-1);
        engi2.addLevel(10);
        assertAll("Check add level engimon", () -> assertEquals(engi2.getLvl(), 30),
                () -> assertEquals(engi2.getPosition().getX(), 5), () -> assertEquals(engi2.getPosition().getY(), 5),
                () -> assertEquals(engi2.getName(), "Picha"), () -> assertEquals(engi2.getLives(), 1));
        engi2.addExp(10);
        assertAll("Check add exp engimon", () -> assertEquals(engi2.getLvl(), 30),
                () -> assertEquals(engi2.getExp(), 35));
        engi2.addSkill(new SkillEngimon(skillDex.getEntity("Tackle"), 2));
        assertAll("Check add skill engimon", () -> assertEquals(engi2.getSkillCount(), 2),
                () -> assertEquals(engi2.getSkillByString("Tackle").getMasteryLevel(), 2));
        engi2.delSkill(engi2.getSkillByString("Tackle"));
        assertThrows(EngimonException.class, () -> engi2.getSkillByString("Tackle"));

    }

}
