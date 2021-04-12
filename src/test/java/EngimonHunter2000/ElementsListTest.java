package EngimonHunter2000;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author Josep Marcello
 */
public class ElementsListTest {
    @Test
    public void constructorWithSingleEnum() throws ElementsListException {
        ElementsList e = new ElementsList(Element.GROUND);
        ElementsList lawan1 = new ElementsList(Element.FIRE);
        ElementsList lawan2 = new ElementsList(Element.ELECTRIC);
        ElementsList lawan3 = new ElementsList(Element.ICE);
        ElementsList lawan4 = new ElementsList(Element.WATER);
        ElementsList lawan5 = new ElementsList(Element.GROUND);
        assertAll("Harusnya menghasilkan elements list dengan sebuah element",
                () -> assertNotNull(e),
                () -> assertEquals(e.getElementsList().size(), 1),
                () -> assertTrue(e.getElementsList().contains(Element.GROUND)),
                () -> assertFalse(e.getElementsList().contains(Element.FIRE)),
                () -> assertEquals(e.getElementalAdvantage(lawan1), 1.5), // vs fire
                () -> assertEquals(e.getElementalAdvantage(lawan2), 2), // vs electric
                () -> assertEquals(e.getElementalAdvantage(lawan3), 0), // vs ice
                () -> assertEquals(e.getElementalAdvantage(lawan4), 1), // vs water
                () -> assertEquals(e.getElementalAdvantage(lawan5), 1)); // vs ground
    }

    @Test
    public void constructorWithMultipleEnums() throws ElementsListException {
        ElementsList e = new ElementsList(Element.GROUND, Element.FIRE);
        ElementsList lawan1 = new ElementsList(Element.FIRE);
        ElementsList lawan2 = new ElementsList(Element.ELECTRIC);
        ElementsList lawan3 = new ElementsList(Element.ICE);
        ElementsList lawan4 = new ElementsList(Element.WATER);
        ElementsList lawan5 = new ElementsList(Element.GROUND);
        assertAll("Harusnya menghasilkan elements list dengan dua elements",
                () -> assertNotNull(e),
                () -> assertEquals(e.getElementsList().size(), 2),
                () -> assertTrue(e.getElementsList().contains(Element.GROUND)),
                () -> assertTrue(e.getElementsList().contains(Element.FIRE)),
                () -> assertFalse(e.getElementsList().contains(Element.WATER)),
                () -> assertEquals(e.getElementalAdvantage(lawan1), 1.5), // vs fire
                () -> assertEquals(e.getElementalAdvantage(lawan2), 2), // vs electric
                () -> assertEquals(e.getElementalAdvantage(lawan3), 2), // vs ice
                () -> assertEquals(e.getElementalAdvantage(lawan4), 1), // vs water
                () -> assertEquals(e.getElementalAdvantage(lawan5), 1)); // vs ground
    }

    @Test
    public void constructorWrongString() {
        assertThrows(ElementsListException.class, () -> new ElementsList("hadeh"));
    }
}