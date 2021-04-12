package EngimonHunter2000;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author Josep Marcello
 */
public class ElementTest {
    @Test
    public void elementalAdvantage() {
        assertAll("Menguji mendapatkan elemental advantage antara ELECTRIC dengan yang lainnya",
        () -> assertEquals(Element.getElementalAdvantage(Element.ELECTRIC, Element.ELECTRIC), 1),
        () -> assertEquals(Element.getElementalAdvantage(Element.ELECTRIC, Element.FIRE), 1),
        () -> assertEquals(Element.getElementalAdvantage(Element.ELECTRIC, Element.GROUND), 0),
        () -> assertEquals(Element.getElementalAdvantage(Element.ELECTRIC, Element.ICE), 1.5),
        () -> assertEquals(Element.getElementalAdvantage(Element.ELECTRIC, Element.WATER), 2));

        assertAll("Menguji mendapatkan elemental advantage antara fire dengan yang lainnya",
        () -> assertEquals(Element.getElementalAdvantage(Element.FIRE, Element.ELECTRIC), 1),
        () -> assertEquals(Element.getElementalAdvantage(Element.FIRE, Element.FIRE), 1),
        () -> assertEquals(Element.getElementalAdvantage(Element.FIRE, Element.GROUND), 0.5),
        () -> assertEquals(Element.getElementalAdvantage(Element.FIRE, Element.ICE), 2.0),
        () -> assertEquals(Element.getElementalAdvantage(Element.FIRE, Element.WATER), 0));

        assertAll("Menguji mendapatkan elemental advantage antara GROUND dengan yang lainnya",
        () -> assertEquals(Element.getElementalAdvantage(Element.GROUND, Element.ELECTRIC), 2),
        () -> assertEquals(Element.getElementalAdvantage(Element.GROUND, Element.FIRE), 1.5),
        () -> assertEquals(Element.getElementalAdvantage(Element.GROUND, Element.GROUND), 1),
        () -> assertEquals(Element.getElementalAdvantage(Element.GROUND, Element.ICE), 0),
        () -> assertEquals(Element.getElementalAdvantage(Element.GROUND, Element.WATER), 1));

        assertAll("Menguji mendapatkan elemental advantage antara ICE dengan yang lainnya",
        () -> assertEquals(Element.getElementalAdvantage(Element.ICE, Element.ELECTRIC), 0.5),
        () -> assertEquals(Element.getElementalAdvantage(Element.ICE, Element.FIRE), 0),
        () -> assertEquals(Element.getElementalAdvantage(Element.ICE, Element.GROUND), 2),
        () -> assertEquals(Element.getElementalAdvantage(Element.ICE, Element.ICE), 1),
        () -> assertEquals(Element.getElementalAdvantage(Element.ICE, Element.WATER), 1));

        assertAll("Menguji mendapatkan elemental advantage antara WATER dengan yang lainnya",
        () -> assertEquals(Element.getElementalAdvantage(Element.WATER, Element.ELECTRIC), 0),
        () -> assertEquals(Element.getElementalAdvantage(Element.WATER, Element.FIRE), 2),
        () -> assertEquals(Element.getElementalAdvantage(Element.WATER, Element.GROUND), 1),
        () -> assertEquals(Element.getElementalAdvantage(Element.WATER, Element.ICE), 1),
        () -> assertEquals(Element.getElementalAdvantage(Element.WATER, Element.WATER), 1));

    }

    @Test
    public void elementFromString() {
        assertAll("Mendapatkan element dari namanya",
        () -> assertEquals(Element.getElementFromString("eLeCtric"), Element.ELECTRIC),
        () -> assertEquals(Element.getElementFromString("fIRe"), Element.FIRE),
        () -> assertEquals(Element.getElementFromString("GROUND"), Element.GROUND),
        () -> assertEquals(Element.getElementFromString("ice"), Element.ICE),
        () -> assertEquals(Element.getElementFromString("WateR"), Element.WATER));

        assertThrows(ElementException.class, () -> Element.getElementFromString("hadeh"));
    }
}