package EngimonHunter2000;

import java.util.HashSet;

/**
 * {@link EngimonHunter2000}
 * @author Alvin Wilta
 */

public class Engimon extends EngimonSpecies {
    public static final long serialVersionUID = 1L;
    public static final int MAX_EXP = 100;
    public static final int MAX_CEXP = 100000;
    public static final int MAX_LIVES = 3;
    public static final int MAX_SKILLS = 4;

    private HashSet<SkillEngimon> listSkill;
    private String name;
    private int lvl;
    private int exp;
    private int cexp;
    private Position position;
    private int lives;
    private String[] parents;

    /**
     * Konstruktor dengan menggunakan nama spesies dari {@link EngimonSpecies}
     * Konstruktor ini dapat digunakan untuk membuat engimon baru pada
     * {@link Inventory} dari player
     * @param dex {@link EngiDex}   EngiDex yang akan dilookup untuk mendapatkan spesiesnya
     * @param _species              Nama species engimon yang akan di-construct
     * @param _name                 Nama engimon tersebut
     * @throws EngimonSpeciesException tidak terdapat nama spesies terkait pada dex
     * @throws ElementsListException
     * @throws EngimonException nama tidak boleh kosong
     */
    Engimon(EngiDex dex, String _species, String _name)
            throws EngimonSpeciesException, ElementsListException, EngimonException {
        super(dex, _species);
        if (_name == null || _name == "") {
            throw new EngimonException(3);
        }
        this.listSkill = new HashSet<SkillEngimon>();
        this.listSkill.add(super.getStarterSkill());
        this.name = _name;
        this.lvl = 1;
        this.exp = 0;
        this.cexp = 0;
        this.lives = 3;
        this.position = new Position(-1, -1);
        this.parents = new String[2];
        this.parents[0] = _species;
        this.parents[1] = _species;
    }

    /**
     * Konstruktor dengan parameter lengkap
     * @param dex {@link EngiDex} dex yang akan dipakai oleh engimon
     * @param _species nama spesies
     * @param _name nama engimon
     * @param _lvl level engimon
     * @param _exp exp tersisa (maks 99)
     * @param _pos posisi engimon
     * @param _lives lives default
     * @param _parent1 nama parent engimon 1
     * @param _parent2 nama parent engimon 2
     * @throws EngimonSpeciesException tidak terdapat nama spesies terkait pada dex
     * @throws ElementsListException
     * @throws EngimonException nama tidak valid
     */
    Engimon(EngiDex dex, String _species, String _name, int _lvl, int _exp, Position _pos, int _lives, String _parent1,
            String _parent2) throws EngimonSpeciesException, ElementsListException, EngimonException {
        super(dex, _species);
        if (_name == null || _name == "") {
            throw new EngimonException(3);
        }
        this.listSkill = new HashSet<SkillEngimon>();
        this.listSkill.add(super.getStarterSkill());
        this.name = _name;
        this.lvl = _lvl;
        this.exp = _exp;
        this.cexp = this.lvl * MAX_EXP + this.exp;
        this.lives = _lives;
        this.position = new Position(_pos.getX(), _pos.getY());
        this.parents = new String[2];
        this.parents[0] = _parent1;
        this.parents[1] = _parent2;
    }

    /**
     * Konstruktor untuk BREEDING
     * @param dex {@link EngiDex} dex yang akan dipakai oleh engimon
     * @param _name nama anak engimon
     * @param parent1 nama parent1 (parent1 dan parent2 bisa di-interchange)
     * @param parent2 nama parent2 (parent1 dan parent2 bisa di-interchange)
     * @throws EngimonSpeciesException
     * @throws ElementsListException
     * @throws EngimonException
     */
    Engimon(EngiDex dex, String _species, SkillEngimon _skill, String _name, String parent1, String parent2)
            throws EngimonSpeciesException, ElementsListException, EngimonException {
        super(dex, _species);
        if (_name == null || _name == "") {
            throw new EngimonException(3);
        }
        this.listSkill = new HashSet<SkillEngimon>();
        this.listSkill.add(_skill);
        this.name = _name;
        this.lvl = 1;
        this.exp = 0;
        this.cexp = 0;
        this.lives = 3;
        this.position = new Position(-1, -1);
    }

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
     * @throws EngimonException gagal mencari nama skill pada listSkill engimon
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
     * Getter jumlah lives yang dimiliki engimon
     * @return jumlah lives engimon
     */
    public int getLives() {
        return this.lives;
    }

    // SETTER

    /**
     * Menambah atau mengurangi lives pada engimon, maks 3 (jika ditambah >3 akan otomatis diset jadi 3)
     * @param _lives jumlah lives yang akan DITAMBAH/DIKURANG (bukan diset)
     * @throws EngimonException jika pengurangan terlalu banyak (lives<0)
     */
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
     * Setter untuk mengubah nama
     * @param _name nama baru engimon
     * @throws EngimonException jika nama yang ingin diganti kosong atau null
     */
    public void setName(String _name) throws EngimonException {
        if (_name == null || _name == "") {
            throw new EngimonException(4);
        }
        this.name = _name;
    }

    /**
     * Setter untuk mengubah level
     * SETTER UNTUK BREEDING
     * Note: jangan lupa untuk cek hasil return, untuk mendelete engimon kalau level udah melebihi
     * @param _lvl level yang ingin ditambah/dikurang gunakan - jika berkurang misalnya Engimon.addLevel(-3)
     * @return true jika engimon masih bisa hidup, false jika engimon melebihi cumulative exp dan harus didelete
     * @throws EngimonException jika pengurangan level terlalu banyak
     */
    public boolean addLevel(int _lvl) throws EngimonException {
        if (this.lvl + _lvl < 1) {
            throw new EngimonException(0);
        } else {
            this.lvl += _lvl;
            this.cexp += _lvl * MAX_EXP;
            if (this.cexp >= MAX_CEXP) {
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
     * @throws EngimoException jika pengurangan exp terlalu banyak
     */
    public boolean addExp(int _exp) throws EngimonException {
        this.exp += _exp;
        this.cexp += _exp;
        if (this.exp >= MAX_EXP) {
            int lvl = this.exp / MAX_EXP;
            this.exp -= (lvl * MAX_EXP);
            return this.addLevel(lvl);
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

    public void addSkill(SkillEngimon s) throws EngimonException {
        if (this.getSkillCount() >= MAX_SKILLS) {
            throw new EngimonException(5);
        } else {
            listSkill.add(s);
        }
    }

    public void delSkill(SkillEngimon s) throws EngimonException {
        if (!listSkill.remove(s)) {
            throw new EngimonException(4);
        }
    }

    public int getExp() {
        return this.exp;
    }

    public int getCExp() {
        return this.cexp;
    }

    public String[] getParents() {
        return this.parents;
    }

    public void changeParent(int _idx, String _name) {
        this.parents[_idx] = _name;
    }

    @Override public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o.getClass() != Engimon.class) {
            return false;
        }

        Engimon _e = (Engimon) o;

        return this.getSpecies() == _e.getSpecies()
                && this.getName() == _e.getName() && this.getLvl() == _e.getLvl()
                && this.getExp() == _e.getExp()
                && this.getBattlePower(1.0) == _e.getBattlePower(1.0);
    }
}
