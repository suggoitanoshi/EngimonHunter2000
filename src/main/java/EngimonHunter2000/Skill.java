package EngimonHunter2000;

import java.util.Set;

/**
 * Class yang berisi Skill yang digunakan pada {@link Dex}.
 * Class ini merupakan "blueprint" dari skill milik {@link Engimon}.
 * @author Josep Marcello
 */
public class Skill {
    private final String name;
    private final int basePower;
    private final ElementsList elements;

    /**
     * Constructor untuk membuat skill baru dengan nama, basePower,
     * masteryLevel, dan el yang ditentunkan.
     * @param _name nama skill
     * @param _basePower basePower skill
     * @param _masteryLevel masteryLevel skill
     * @param el element yang dapat membepalajari skill
     */
    public Skill(String _name, int _basePower, Element... el)
        throws ElementsListException {
        name = _name;
        basePower = _basePower;
        elements = new ElementsList(el);
    }

    /**
     * Constructor untuk membuat skill baru dengan nama, basePower,
     * masteryLevel, dan el yang ditentunkan.
     * @param _name nama skill
     * @param _basePower basePower skill
     * @param _masteryLevel masteryLevel skill
     * @param el element yang dapat membepalajari skill
     */
    public Skill(String _name, int _basePower, String... el)
        throws ElementsListException {
        name = _name;
        basePower = _basePower;
        elements = new ElementsList(el);
    }

    /**
     * Constructor untuk membuat skill baru dengan nama, basePower,
     * masteryLevel, dan el yang ditentunkan.
     * @param _name nama skill
     * @param _basePower basePower skill
     * @param _masteryLevel masteryLevel skill
     * @param el element yang dapat membepalajari skill
     */
    public Skill(String _name, int _basePower, ElementsList el)
        throws ElementsListException {
        name = _name;
        basePower = _basePower;
        elements = el;
    }

    /**
     * Getter untuk nama skill
     * @return nama skill
     */
    public String getName() {
        return name;
    }

    /**
     * Getter untuk base power skill
     * @return base power skill
     */
    public int getBasePower() {
        return basePower;
    }

    /**
     * Getter untuk {@link EngimonHunter2000.Element.El} element skill
     * @return {@link EngimonHunter2000.Element.El} skill
     */
    public Set<Element> getElements() {
        return elements.getElementsList();
    }
}
