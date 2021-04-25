package EngimonHunter2000;

import java.util.Set;
import java.util.HashMap;

/**
 * 
 * {@link EngimonHunter2000}
 * 
 * @author Alvin Wilta
 */

public class Engimon extends EngimonSpecies {
    public static int max_exp = 100;
    public static int max_cexp = 100000;
    private HashMap<String, SkillEngimon> listSkill;
    private String name;
    private int lvl;
    private int exp;
    private int cexp;
    private Position position;
    private int lives;
    private String[] parents;

    /**
     * @deprecated Membuat engimon picakhu, pakai kalau darurat atau buat debugging
     */
    Engimon(EngiDex dex) {
        super(dex, "Picakhu");
        this.name = super.getSpecies();
        this.lvl = 20;
        this.exp = 0;
        this.cexp = 20 * max_exp;
        this.lives = 3;
        this.position = new Position(-1, -1);
        this.parents[0] = "Picakhu";
        this.parents[1] = "Picakhu";
    }

    /**
     * Konstruktor dengan menggunakan nama spesies dari {@link EngimonSpecies}
     * Konstruktor ini dapat digunakan untuk membuat engimon baru pada
     * {@link Inventory} dari player
     * 
     * @param dex {@link EngiDex}   EngiDex yang akan dilookup untuk mendapatkan spesiesnya
     * @param _species              Nama species engimon yang akan di-construct
     * @param _name                 Nama engimon tersebut
     */
    Engimon(EngiDex dex, String _species, String _name) {
        super(dex, _species);
        this.listSkill = new HashMap<String, SkillEngimon>();
        this.listSkill.put(super.getStarterSkill().getName(), super.getStarterSkill());
        this.name = _name;
        this.lvl = 1;
        this.exp = 0;
        this.cexp = 0;
        this.lives = 3;
        this.position.setPosition(-1, -1);
        this.parents[0] = _species;
        this.parents[0] = _species;
    }

    /**
     * Konstruktor dengan parameter lengkap
     * 
     * @param dex dex yang akan dipakai oleh engimon
     * @param _species nama spesies
     * @param _name nama engimon
     * @param _lvl level engimon
     * @param _exp exp tersisa (maks 99)
     * @param _pos posisi engimon
     * @param _lives lives default
     * @param _parent1 nama parent engimon 1
     * @param _parent2 nama parent engimon 2
     */
    Engimon(EngiDex dex, String _species, String _name, int _lvl, int _exp, Position _pos, int _lives, String _parent1,
            String _parent2) {
        super(dex, _species);
        this.listSkill = new HashMap<String, SkillEngimon>();
        this.listSkill.put(super.getStarterSkill().getName(), super.getStarterSkill());
        this.name = _name;
        this.lvl = _lvl;
        this.exp = _exp;
        this.cexp = this.lvl * max_exp + this.exp;
        this.lives = _lives;
        this.position = new Position(_pos.getX(), _pos.getY());
    }

    Engimon(EngiDex dex, String _name, SkillEngimon _skill, Engimon parent1, Engimon parent2) {

        this.listSkill = new HashMap<String, SkillEngimon>();
        this.listSkill.put(_skill.getName(), _skill);
        super(dex, _species);
        this.name = _name;
        this.lvl = 1;
        this.exp = 0;
        this.cexp = 0;
        this.lives = 3;
        this.position = new Position(-1, -1);
    }

    private void breedingElement(Engimon parent1, Engimon parent2) {
        if (Element.getElementalAdvantage(parent1.getListElement(), parent2.getListElement())) {

        }
    }

    /**
     * Getter nama engimon
     * @return nama dari Engimon
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter level engimon
     * @return level dari Engimon
     */
    public int getLvl() {
        return this.lvl;
    }

    /**
     * Getter jumlah elemen engimon
     * @return jumlah elemen yang dimiliki
     */
    public int getElementCount() {
        return super.getListElement().size();
    }

    /**
     * Getter jumlah skill engimon
     * @return jumlah skill yang dimiliki
     */
    public int getSkillCount() {
        return this.listSkill.size();
    }

    /**
     * Getter posisi engimon
     * @return posisi Engimon
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * Getter list skill engimon
     * @return list skill engimon
     */
    public HashMap<String, SkillEngimon> getSkills() {
        return this.listSkill;
    }

    /**
     * Getter skill engimon berdasarkan nama skill
     * @param _skillname nama dari skill engimon
     * @return {@link SkillEngimon} skill engimon yang dimiliki berdasarkan nama
     */
    public SkillEngimon getSkillByString(String _skillname) {
        return this.listSkill.get(_skillname);
    }

    public int getSkillsCount() {
        return this.listSkill.size();
    }

    // setter
    public void setPos(int x, int y) {
        position.setPosition(x, y);
    }

    public void setSkills(int index, Skill oskill) {

    }

    /**
     * Setter untuk mengubah level, gunakan - jika berkurang, misalnya
     * Engimon.setLevel(-3); untuk mengurangi level engimon
     * @param level
     * @return true jika engimon masih bisa hidup, false jika engimon melebihi cexp dan harus didelete
     */
    public boolean addLevel(int _lvl) throws EngimonException {
        if (this.lvl - _lvl < 1) {
            throw new EngimonException(0);
        } else {
            this.lvl += _lvl;
            this.cexp += _lvl * max_exp;
            if (this.cexp >= max_cexp) {
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * Setter untuk menambah exp engimon
     * @param _exp exp yang ingin ditambah, bisa >100
     * @return true jika engimon masih bisa hidup, false jika engimon melebihi cexp dan harus didelete
     */
    public boolean addExp(int _exp) throws EngimonException {
        this.exp += _exp;
        if (this.exp >= max_exp) {
            this.exp -= max_exp;
            return this.addLevel(1);
        } else {
            return true;
        }
    }

    public Long getBattlePower(Double elmtAdv) {

    }

    public Boolean isEqual(Engimon engi) {

    }

    public void showEngimon() {

    }

    public void interact() {

    }

}
