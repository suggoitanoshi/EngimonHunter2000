package EngimonHunter2000;

import java.util.HashSet;

/**
 * {@link EngimonHunter2000}
 * @author Alvin Wilta
 */

public class Engimon extends EngimonSpecies {
    public static int max_exp = 100;
    public static int max_cexp = 100000;
    public static int max_lives = 3;

    private HashSet<SkillEngimon> listSkill;
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
    Engimon(EngiDex dex) throws EngimonSpeciesException, ElementsListException {
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
     * @param dex {@link EngiDex}   EngiDex yang akan dilookup untuk mendapatkan spesiesnya
     * @param _species              Nama species engimon yang akan di-construct
     * @param _name                 Nama engimon tersebut
     */
    Engimon(EngiDex dex, String _species, String _name) throws EngimonSpeciesException, ElementsListException {
        super(dex, _species);
        this.listSkill = new HashSet<SkillEngimon>();
        this.listSkill.add(super.getStarterSkill());
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
            String _parent2) throws EngimonSpeciesException, ElementsListException {
        super(dex, _species);
        this.listSkill = new HashSet<SkillEngimon>();
        this.listSkill.add(super.getStarterSkill());
        this.name = _name;
        this.lvl = _lvl;
        this.exp = _exp;
        this.cexp = this.lvl * max_exp + this.exp;
        this.lives = _lives;
        this.position = new Position(_pos.getX(), _pos.getY());
    }

    // buat breding (pake method breedingElement buat nentuin element anaknya
    Engimon(EngiDex dex, String _species, String _name, SkillEngimon _skill, Engimon parent1, Engimon parent2)
            throws EngimonSpeciesException, ElementsListException {
        super(dex, _species);
        this.listSkill = new HashSet<SkillEngimon>();
        this.listSkill.add(_skill);
        this.name = _name;
        this.lvl = 1;
        this.exp = 0;
        this.cexp = 0;
        this.lives = 3;
        this.position = new Position(-1, -1);
    }

    //belom selesai
    // private void breedingElement(Engimon parent1, Engimon parent2) throws EngimonException {
    //     if (parent1.getLvl() < 4 || parent2.getLvl() < 4) {
    //         throw new EngimonException(3);
    //     }
    //     if (Element.getElementalAdvantage(parent1.getListElement(), parent2.getListElement())) {

    //     }
    // }

    // GETTER

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
        return super.getListElement().getElementsList().size();
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
    public HashSet<SkillEngimon> getSkills() {
        return this.listSkill;
    }

    /**
     * Getter skill engimon berdasarkan nama skill
     * @param _skillname nama dari skill engimon
     * @return {@link SkillEngimon} skill engimon yang dimiliki berdasarkan nama
     */
    public SkillEngimon getSkillByString(String _skillname) throws EngimonException {
        for (SkillEngimon se : this.listSkill) {
            if (se.getName().equals(_skillname)) {
                return se;
            }
        }

        throw new EngimonException(1);
    }

    /**
     * Getter jumlah skill dari engimon
     * @return jumlah skill yang dimiliki engimon
     */
    public int getSkillsCount() {
        return this.listSkill.size();
    }

    /**
     * Getter jumlah lives yang dimiliki engimon
     * @return jumlah lives engimon
     */
    public int getLives() {
        return this.lives;
    }

    // SETTER

    public void setLives(int _lives) throws EngimonException {
        this.lives += _lives;
        if (this.lives > 3) {
            this.lives = 3;
        }
        if (this.lives < 0) {
            throw new EngimonException(2);
        }
    }

    /**
     * Setter untuk mengubah posisi engimon
     * @param x absis dari koordinat
     * @param y ordinat dari koordinat
     */
    public void setPos(int x, int y) {
        position.setPosition(x, y);
    }

    /**
     * masih gatau ini butuh ato engga
     * @param index
     * @param oskill
     */
    public void setSkills(int index, Skill oskill) {

    }

    /**
     * Setter untuk mengubah level, gunakan - jika berkurang, misalnya
     * Engimon.setLevel(-3); untuk mengurangi level engimon
     * SETTER UNTUK BREEDING
     * Note: jangan lupa untuk cek hasil return, untuk mendelete engimon kalau level udah melebihi
     * @param level
     * @return true jika engimon masih bisa hidup, false jika engimon melebihi cumulative exp dan harus didelete
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
     * SETTER UNTUK BATTLE
     * Note: jangan lupa untuk cek hasil return, untuk mendelete engimon kalau level udah melebihi
     * @param _exp exp yang ingin ditambah, bisa >100
     * @return true jika engimon masih bisa hidup, false jika engimon melebihi cumulative exp dan harus didelete
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

    /**
     * Method untuk mengembalikan battle power yang dimiliki oleh engimon ini
     * @param elmtAdv {@link Element} Element advantage dari engimon ini dengan lawannya
     * @return battle power dari engimon terkait
     */
    public Double getBattlePower(Double elmtAdv) {
        Double sum = 0d;
        for (SkillEngimon skill : this.listSkill) {
            sum += skill.getBasePower() * skill.getMasteryLevel();
        }
        return (this.lvl * elmtAdv + sum);
    }

    public void showEngimon() {

    }

    public void interact() {

    }

}
