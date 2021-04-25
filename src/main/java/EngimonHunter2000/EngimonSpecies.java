package EngimonHunter2000;

/**
 * 
 * {@link EngimonHunter2000}
 * 
 * @author Alvin Wilta
 */

public class EngimonSpecies {
    private final String spesies;
    private final String slogan;
    private SkillEngimon starterSkill;
    private ElementsList listElement;

    /**
     * Konstruktor ini dibuat untuk membuat {@link EngiDex}
     * 
     * @param _spesies nama dari spesies yang dibaca dari file
     * @param _slogan slogan dari spesies yang dibaca dari file
     * @param _starter {@link SkillEngimon} skill pertama yang dibaca dari file dan sudah di-construct
     *                 menggunakan konstruktor {@link SkillEngimon}
     */
    EngimonSpecies(String _spesies, String _slogan, SkillEngimon _starter, Element... el) throws ElementsListException {
        this.listElement = new ElementsList(el);
        this.spesies = _spesies;
        this.slogan = _slogan;
        this.starterSkill = _starter;
    }

    /**
     * Konstruktor ini dibuat untuk membuat {@link Engimon}
     * TODO: buat exception dari dex ketika gadapet engimon yang sesuai dari namanya
     * @param dex {@link EngiDex} EngiDex yang akan dilookup untuk mendapatkan spesiesnya
     * @param _spesies nama spesies yang akan di-construct
     */
    EngimonSpecies(EngiDex dex, String _spesies) throws ElementsListException, EngimonSpeciesException {
        try {
            this.spesies = dex.getEntity(_spesies).getSpecies();
        } catch (DexException e) {
            throw new EngimonSpeciesException(0);
        }
        this.slogan = dex.getEntity(_spesies).getSlogan();
        this.listElement = new ElementsList(
                (Element[]) dex.getEntity(_spesies).getListElement().getElementsList().toArray());
        this.starterSkill = dex.getEntity(_spesies).getStarterSkill();

    }

    public String getSpecies() {
        return this.spesies;
    }

    public String getSlogan() {
        return this.slogan;
    }

    public SkillEngimon getStarterSkill() {

        return this.starterSkill;

    }

    public ElementsList getListElement() {
        return this.listElement;
    }

    /**
     * TODO: handle buat elemen baru?
     * @param _element {@link Element} Element yang ingin ditambahkan
     */
    public void addElement(Element _element) throws EngimonSpeciesException {
        if (this.listElement.getElementsList().size() < 2) {
            // nambahin elemen baru?
        } else {
            throw new EngimonSpeciesException(1);
        }
    }

}