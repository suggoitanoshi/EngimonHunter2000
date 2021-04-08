package EngimonHunter2000;

/**
 * @author y e e wangy wangy
 * Class yang berisi Skill yang digunakan pada {@link Dex}.
 * Class ini merupakan "asar/blueprint dari skill milik {@link Engimon}.
 */
public class Skill {
    private final String name;
    private final int basePower;
    private final Elements elements;

    /**
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
    public Skill(String _name, int _basePower, Elements.El[] el)
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
    public Skill(String _name, int _basePower, String[] el)
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

    public String getName() {
        return name;
    }

    public int getBasePower() {
        return basePower;
    }

    public Elements getElements() {
        return elements;
    }
}
