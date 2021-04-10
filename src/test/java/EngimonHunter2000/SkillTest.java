package EngimonHunter2000;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SkillTest {
    @Test
    public void constructorWithEnum() throws EngimonHunter2000Exception {
        Skill s = new Skill("THUNDAAA", 40, Elements.El.ELECTRIC);
        Assertions.assertNotNull(s);
        assertAll("Harusnya menghasilkan skill default (THUNDAAA, power: 40, Elements: Electric)",
            () -> assertEquals("THUNDAAA", s.getName()),
            () -> assertEquals(40, s.getBasePower()),
            () -> assertEquals(Elements.El.ELECTRIC, s.getElements())
        );
    }

    public void constructorWithString() throws EngimonHunter2000Exception {
        Skill s = new Skill("THUNDAAA", 40, "Electric");
        Assertions.assertNotNull(s);
        assertAll("Harusnya menghasilkan skill default (THUNDAAA, power: 40, Elements: Electric)",
            () -> assertEquals("THUNDAAA", s.getName()),
            () -> assertEquals(40, s.getBasePower()),
            () -> assertEquals(Elements.El.ELECTRIC, s.getElements())
        );
    }
}