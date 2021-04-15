package EngimonHunter2000;

/**
 * Class Item
 * @author Jeane Mikha Erwansyah
 */
public class Item extends SkillEngimon {
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

    /**
    public void learn() {
        // Mengecek kecocokan skill item dengan engimon
        // Mengecek apakah ada 1 elemen yang cocok dengan skill item
        // Jika iya berarti compatible
        // Jika tidak throw exception 1

        // Mengecek apakah skill sudah dipelajari atau belum
        // Jika sudah tambahkan mastery level
        
        // Mengecek mastery level item
        // Jika tidak sama dengan 1 throw exception 0
        
        // Mengecek jumlah skills yang telah dilajari
        // Jika jumlah skill engimon sudah max maka pilih skill untuk di-replace
        // Jika pilihan salah throw exception 2
    }
    */
}
