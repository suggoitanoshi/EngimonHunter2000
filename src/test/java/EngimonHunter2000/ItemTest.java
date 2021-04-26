package EngimonHunter2000;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemTest {
    static Item i;
    static Skill s;

    @BeforeEach
    public void newItem() throws ElementsListException {
        assertDoesNotThrow(() -> s = new Skill("THUNDAAA", 40, "ELECTRIC"));
        assertDoesNotThrow(() -> i = new Item(s));
    }

    @Test
    public void setquantity() {
        assertEquals(1, i.getQuantity());
        i.incQuantity();
        assertEquals(2, i.getQuantity());
    }

    @Test
    public void setquantity1() {
        assertEquals(1, i.getQuantity());
        i.decQuantity();
        assertEquals(0, i.getQuantity());
    }

    @Test
    public void setquantity2() {
        assertEquals(1, i.getQuantity());
        i.setQuantity(2);
        assertEquals(2, i.getQuantity());
    }

    @Test
    public void equalityTest() {
        assertTrue(i.equals(i));
    }
}
