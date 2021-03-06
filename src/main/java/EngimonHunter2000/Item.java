package EngimonHunter2000;

import java.util.*;

public class Item extends SkillEngimon {
    public static final long serialVersionUID = 5914066636119786165L;
    private int quantity;

    /**
     * Constructor untuk membuat skill item dari skillinfo
     * @param _skillInfo existing skill data untuk membuat item
     */
    public Item(Skill _skillInfo) {
        super(_skillInfo);
        quantity = 1;
    }

    /**
     * Constructor untuk membuat skill item dari skillinfo
     * @param _skillInfo existing skill data untuk membuat item
     * @param _masteryLevel the masteryLevel for SkillEngimon
     */
    public Item(Skill _skillInfo, int _masteryLevel) {
        super(_skillInfo, _masteryLevel);
        quantity = 1;
    }

    /**
     * Constructor untuk membuat skill item dari skillinfo
     * @param _skillInfo existing skill data untuk membuat item
     * @param _masteryLevel the masteryLevel for SkillEngimon
     * @param _quantity kuantitas dari skill item
     */
    public Item(Skill _skillInfo, int _masteryLevel, int _quantity) {
        super(_skillInfo, _masteryLevel);
        quantity = _quantity;
    }

    /**
     * Setter untuk quantity
     * @param _quantity
     */
    public void setQuantity(int _quantity) {
        quantity = _quantity;
    }

    /**
     * Getter untuk quantity
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Menambahkan quantity sebanyak 1
     */
    public void incQuantity() {
        quantity++;
    }

    /**
     * Mengurangi quantity sebanyak 1
     */
    public void decQuantity() {
        quantity--;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o.getClass() != Item.class) {
            return false;
        }

        if (this == o) {
            return true;
        }

        Item _i = (Item) o;

        return this.skillInfo.getName().equals(_i.getName()) &&
               this.masteryLevel == _i.getMasteryLevel();
    }

    /**
     * Metode untuk engimon belajar sebuah skill dari skill item
     * Jika engimon sudah memiliki skill maka mastery level bertambahm, max 3 level
     * Jika belum maka akan ditambahkan ke list skills engimon,
     * jika engimon sudah memiliki 4 skill, maka akan menggantikan
     * sebuah skill yang sudah ada
     * @param e engimon
     * @throws ItemException
     * @throws SkillEngimonException
     */
    public void learn(Engimon e) throws ItemException, SkillEngimonException {
        boolean compatible = false;
        boolean newSkill = true;

        // Mengecek kecocokan skill item dengan engimon
        for (Element el : getElements()) {
            compatible = e.getListElement().getElementsList().contains(el);
            if (compatible) {
                break;
            }
        }

        if (!compatible) {
            throw new ItemException(1);
        }

        // Mengecek mastery level item
        if (getMasteryLevel() != 1) {
            throw new ItemException(0);
        }

        // Mengecek apakah skill sudah dipelajari atau belum
        for (SkillEngimon s : e.getSkills()) {
            if (s.getName().equals(getName())) {
                newSkill = false;
                if (s.getMasteryLevel() == 3) {
                    throw new SkillEngimonException(0);
                } else {
                    s.incMasteryLevel();
                }
                break;
            }
        }

        if (newSkill) {
            Element[] els = new Element[getElements().size()];
            getElements().toArray(els);

            try {
                SkillDex temp = new SkillDex();
                Skill s = temp.getEntity(getName());
                SkillEngimon se = new SkillEngimon(s);
                e.addSkill(se);
            } catch (DexException | EngimonException exception) {
                exception.printStackTrace();
                throw new ItemException(3);
            }
        }
    }
}
