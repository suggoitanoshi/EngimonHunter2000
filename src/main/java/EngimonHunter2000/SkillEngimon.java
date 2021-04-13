package EngimonHunter2000;

import java.util.Set;

// TODO: Finish this!
/**
 * Class for Engimon's skill. Uses {@link Skill} class to get the skill's
 * metadata (name, basepower, and elements).
 * @author Josep Marcello
 */
public class SkillEngimon {
    private Skill metadata;
    private int masteryLevel;

    /**
     * Construct a new engimon skill from a skill named `name` and 1 mastery
     * level.
     * @param name name of skill
     */
    public SkillEngimon(String name) {
        // implement get skill from name
        masteryLevel = 1;
    }

    /**
     * Construct a new engimon skill from a skill named `name` and specified
     * mastery level `_masteryLevel`.
     * @param name name of skill
     * @param _masteryLevel the masteryLevel for SkillEngimon
     */
    public SkillEngimon(String name, int _masteryLevel) {
        // implement get skill from name
        masteryLevel = _masteryLevel;
    }

    /**
     * Getter untuk mendapatkan nama skill (diambil dari metadata)
     * @return nama skill
     */
    public String getName() {
        return metadata.getName();
    }

    /**
     * Getter untuk mendapatkan base power skill (diambil dari metadata)
     * @return base power skill
     */
    public int getBasePower() {
        return metadata.getBasePower();
    }

    /**
     * Getter untuk mendapatkan {@link EngimonHunter2000.Element.El} milik
     * skill (diambil dari metadata)
     * @return {@link EngimonHunter2000.Element.El} skill
     */
    public Set<Element> getElements() {
        return metadata.getElements();
    }

    /**
     * Getter untuk mendapatkan mastery level skill
     * @return mastery level skill
     */
    public int getMasteryLevel() {
        return masteryLevel;
    }

    /**
     * Setter untuk masteryLevel
     * @param _masteryLevel nilai mastery level yang baru
     */
    public void setMasteryLevel(int _masteryLevel) {
        masteryLevel = _masteryLevel;
    }

    /**
     * Menambahkan mastery level sebanyak 1
     */
    public void incMasteryLevel() {
        masteryLevel++;
    }

    /**
     * Mengurangi mastery level sebanyak 1
     */
    public void decMasteryLevel() {
        masteryLevel--;
    }
}
