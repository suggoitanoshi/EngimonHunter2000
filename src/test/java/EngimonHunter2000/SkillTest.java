package EngimonHunter2000;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SkillTest {
    @Test
    public void constructorWithEnum() throws EngimonHunter2000Exception {
        Skill s = new Skill("THUNDAAA", 40, Elements.El.ELECTRIC);
        assertAll("Harusnya menghasilkan skill default (THUNDAAA, power: 40, Elements: Electric)",
            () -> assertNotNull(s),
            () -> assertEquals("THUNDAAA", s.getName()),
            () -> assertEquals(40, s.getBasePower()),
            () -> assertEquals(1, s.getElements().size()),
            () -> assertEquals(Elements.El.ELECTRIC, s.getElements().toArray()[0])
        );
    }

    @Test
    public void constructorWithString() throws EngimonHunter2000Exception {
        Skill s = new Skill("THUNDAAA", 40, "ElecTRic");
        assertAll("Harusnya menghasilkan skill default (THUNDAAA, power: 40, Elements: Electric)",
            () -> assertNotNull(s),
            () -> assertEquals("THUNDAAA", s.getName()),
            () -> assertEquals(40, s.getBasePower()),
            () -> assertEquals(1, s.getElements().size()),
            () -> assertEquals(Elements.El.ELECTRIC, s.getElements().toArray()[0])
        );
    }
}