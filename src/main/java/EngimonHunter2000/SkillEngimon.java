package EngimonHunter2000;

import java.util.Set;

/**
 * Class for Engimon's skill. This class shouldn't create a new skill, instead
 * this class uses an existing {@link Skill} instance. Used in conjunction with
 * {@link SkillDex} to get the existing skill instance.
 * @author Josep Marcello
 */
public class SkillEngimon {
    public static final int MAX_MASTERYLEVEL = 3;
    public static final int MIN_MASTERYLEVEL = 1;

    private Skill skillInfo;
    private int masteryLevel;

    /**
     * Construct a new engimon skill from an existing skill called _skillInfo
     * with mastery level 1.
     * @param _skillInfo existing skill data
     */
    public SkillEngimon(Skill _skillInfo) {
        skillInfo = _skillInfo;
        masteryLevel = 1;
    }

    /**
     * Construct a new engimon skill from an existing skill called _skillInfo
     * with specified mastery level.
     * @param _skillInfo existing skill data
     * @param _masteryLevel the masteryLevel for SkillEngimon
     */
    public SkillEngimon(Skill _skillInfo, int _masteryLevel) {
        // implement get skill from name
        skillInfo = _skillInfo;
        masteryLevel = _masteryLevel;
    }

    /**
     * Getter untuk mendapatkan nama skill (diambil dari skillInfo)
     * @return nama skill
     */
    public String getName() {
        return skillInfo.getName();
    }

    /**
     * Getter untuk mendapatkan base power skill (diambil dari skillInfo)
     * @return base power skill
     */
    public int getBasePower() {
        return skillInfo.getBasePower();
    }

    /**
     * Getter untuk mendapatkan {@link EngimonHunter2000.Element.El} milik
     * skill (diambil dari skillInfo)
     * @return {@link EngimonHunter2000.Element.El} skill
     */
    public Set<Element> getElements() {
        return skillInfo.getElements();
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
    public void setMasteryLevel(int _masteryLevel) throws SkillEngimonException {
        if (_masteryLevel > MAX_MASTERYLEVEL) {
            throw new SkillEngimonException(1);
        }
        masteryLevel = _masteryLevel;
    }

    /**
     * Menambahkan mastery level sebanyak 1
     */
    public void incMasteryLevel() throws SkillEngimonException {
        if (getMasteryLevel() == MAX_MASTERYLEVEL) {
            throw new SkillEngimonException(1);
        }
        masteryLevel++;
    }

    /**
     * Mengurangi mastery level sebanyak 1
     */
    public void decMasteryLevel() throws SkillEngimonException {
        if (getMasteryLevel() == MIN_MASTERYLEVEL) {
            throw new SkillEngimonException(1);
        }
        masteryLevel--;
    }
}
