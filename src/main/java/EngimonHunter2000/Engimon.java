package EngimonHunter2000;

import java.util.Set;

/**
 * 
 * {@link EngimonHunter2000}
 * 
 * @author Alvin Wilta
 */

public class Engimon extends EngimonSpecies {
    private Set<SkillEngimon> listSkill;
    private String name;
    private int lvl;
    private int exp;
    private int cexp;
    private Position position;
    private int lives;

    /**
     * @deprecated Membuat engimon picakhu, pakai kalau darurat atau buat debugging
     */
    Engimon(EngiDex dex) {
        super(dex, "Picakhu");
        this.name = super.getSpecies();
        this.lvl = 20;
        this.exp = 0;
        this.cexp = 20 * 100;
        this.lives = 3;
        this.position = new Position(-1, -1);
    }

    /**
     * Konstruktor dengan menggunakan nama spesies dari {@link EngimonSpecies}
     * Konstruktor ini dapat digunakan untuk membuat engimon baru pada
     * {@link Inventory} dari player
     * 
     * @param dex      EngiDex yang akan dilookup untuk mendapatkan spesiesnya
     * @param _species Nama species engimon yang akan di-construct
     * @param _name    Nama engimon tersebut
     */
    Engimon(EngiDex dex, String _species, String _name) {
        super(dex, _species);
        this.listSkill.add(super.getStarterSkill());
        this.name = _name;
        this.lvl = 1;
        this.exp = 0;
        this.cexp = 0;
        this.lives = 3;
        this.position.setPosition(-1, -1);
    }

    /**
     * Konstruktor dengan parameter lengkap
     * 
     * @param dex
     * @param _species
     * @param _name
     * @param _lvl
     * @param _exp
     * @param _pos
     * @param _lives
     */
    Engimon(EngiDex dex, String _species, String _name, int _lvl, int _exp, Position _pos, int _lives) {
        super(dex, _species);
        this.listSkill.add(super.getStarterSkill());
        this.name = _name;
        this.lvl = _lvl;
        this.exp = _exp;
        this.cexp = this.lvl * 100 + this.exp;
        this.lives = _lives;
        this.position = new Position(_pos.getX(), _pos.getY());
    }

    /**
     * 
     * @return nama dari Engimon
     */
    public String getName() {
        return this.name;
    }

    public int getLvl() {
        return this.lvl;
    }

    public int getElementCount() {
        return super.getListElement().size();
    }

    public Position getPosition() {
        return this.position;
    }

    public Set<SkillEngimon> getSkills() {
        return this.listSkill;
    }

    public SkillEngimon getSkillByIndex(int index) {

    }

    public int getSkillsCount() {

    }

    // setter
    public void setPos(int x, int y) {
        position.setPosition(x, y);
    }

    public void setSkills(int index, Skill oskill) {

    }

    public void setLevel(int level) {

    }

    // methods
    public void addExp(int _exp) {
        this.exp += _exp;
        if (this.exp >= 100) {
            this.lvl
        }
    }

    public void 

    public Long getBattlePower(Double elmtAdv) {

    }

    public Boolean isEqual(Engimon engi) {

    }

    public void showEngimon() {

    }

    public void interact() {

    }

}
