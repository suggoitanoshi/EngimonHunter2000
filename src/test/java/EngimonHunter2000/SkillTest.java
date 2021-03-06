package EngimonHunter2000;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author Josep Marcello
 */
public class SkillTest {
    @Test
    public void constructorWithEnum() throws ElementsListException {
        Skill s = new Skill("THUNDAAA", 40, Element.ELECTRIC);
        assertAll("Harusnya menghasilkan skill default (THUNDAAA, power: 40, Elements: Electric)",
            () -> assertNotNull(s),
            () -> assertEquals("THUNDAAA", s.getName()),
            () -> assertEquals(40, s.getBasePower()),
            () -> assertEquals(1, s.getElements().size()),
            () -> assertEquals(Element.ELECTRIC, s.getElements().toArray()[0])
        );
    }

    @Test
    public void constructorWithString() throws ElementsListException {
        Skill s = new Skill("THUNDAAA", 40, "ElecTRic");
        assertAll("Harusnya menghasilkan skill default (THUNDAAA, power: 40, Elements: Electric)",
            () -> assertNotNull(s),
            () -> assertEquals("THUNDAAA", s.getName()),
            () -> assertEquals(40, s.getBasePower()),
            () -> assertEquals(1, s.getElements().size()),
            () -> assertEquals(Element.ELECTRIC, s.getElements().toArray()[0])
        );
    }
}
