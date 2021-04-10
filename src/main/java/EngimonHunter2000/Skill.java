package EngimonHunter2000;

import java.util.Set;

/**
 * Class yang berisi Skill yang digunakan pada {@link Dex}.
 * Class ini merupakan "asar/blueprint dari skill milik {@link Engimon}.
 * @author y e e wangy wangy
 */
public class Skill {
    private final String name;
    private final int basePower;
    private final Elements elements;

    /**
     * @deprecated
     * Default constructor, menghasilkan skill THUNDAAA dengan detail:
     * nama: THUNDAAA
     * basePower: 40
     * Element: Electric
     */
    public Skill() {
        name = "THUNDAAA";
        basePower = 40;
        elements = new Elements();
    }

    /**
     * Constructor untuk membuat skill baru dengan nama, basePower,
     * masteryLevel, dan el yang ditentunkan.
     * @param _name nama skill
     * @param _basePower basePower skill
     * @param _masteryLevel masteryLevel skill
     * @param el element yang dapat membepalajari skill
     */
    public Skill(String _name, int _basePower, Elements.El... el)
        throws ElementsException {
        name = _name;
        basePower = _basePower;
        elements = new Elements(el);
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
        throws ElementsException {
        name = _name;
        basePower = _basePower;
        elements = new Elements(el);
    }

    /**
     * Constructor untuk membuat skill baru dengan nama, basePower,
     * masteryLevel, dan el yang ditentunkan.
     * @param _name nama skill
     * @param _basePower basePower skill
     * @param _masteryLevel masteryLevel skill
     * @param el element yang dapat membepalajari skill
     */
    public Skill(String _name, int _basePower, Elements el)
        throws ElementsException {
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
     * Getter untuk {@link EngimonHunter2000.Elements.El} element skill
     * @return {@link EngimonHunter2000.Elements.El} skill
     */
    public Set<Elements.El> getElements() {
        return elements.getElementsList();
    }
}
