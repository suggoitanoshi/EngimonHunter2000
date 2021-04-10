package EngimonHunter2000;

import java.util.HashSet;
import java.util.Set;

/**
 * Element untuk {@link Skill} {@link EngimonSpecies} dan {@link Engimon}.
 * Elements list tidak boleh kosong.
 * @author y e e wangy wangy
 */
public class Elements {
    public enum El {
        /// Elemen electric
        ELECTRIC,
        /// Elemen fire
        FIRE,
        /// Elemen ground
        GROUND,
        /// Elemen ice
        ICE,
        /// Elemen water
        WATER,
    }
    private Set<El> elementsList;

    /**
     * @deprecated
     * Default constructor untuk Elements. Tipe default adalah
     * {@link El}.ELECTRIC dengan hanya 1 elemen.
     */
    public Elements() {
        elementsList = new HashSet<El>();
        elementsList.add(El.ELECTRIC); // default Engimon and move has the type Electric
    }

    /**
     * Constructor dengan menggunakan array of String yang sudah ditentukan.
     * @param elms array of {@link El} berisi element-element-nya
     */
    public Elements(El... elms) throws ElementsException {
        elementsList = new HashSet<El>();
        for (El el : elms) {
            elementsList.add(el);
        }

        if (elementsList.size() == 0) {
            throw new ElementsException(0);
        }
    }

    /**
     * Constructor dengan menggunakan array of String yang berisi sudah
     * nama-nama {@link El} sudah ditentukan. Jika semua nama {@link El} pada
     * elms invalid maka (harusnya keluar exception) akan menjadi {@link El}
     * dengan array kosong.
     * @param elms array of String yang berisi nama-nama element
     */
    public Elements(String... elms) throws ElementsException {
        elementsList = new HashSet<El>();

        for (String elStr : elms) {
                try {
                    El el = getElementFromString(elStr);
                    elementsList.add(el);
                } catch (ElementsException jsakfasdhfkalsjfhaeksjfhkalfhklas) {
                    continue;
                }
        }

        if (elementsList.size() == 0) {
            throw new ElementsException(0);
        }
    }

    /**
     * Getter untuk attribute elements.
     * @return Set of {@link El} yang merupakan atribut elements.
     */
    public Set<El> getElementsList() {
        return elementsList;
    }

    private static Double max(Double a, Double b) {
        return a >= b ? a : b;
    }

    /**
     * Fungsi untuk mendapatkan {@link El} dari nama elemen (String).
     * Tidak memerhatikan kapitalisme String. Jika String bukan
     * nama {@link El}, maka akan dikembalikan (harusnya exception) null.
     * @return {@link El} "sebenarnya" dari string (nama)
     */
    public static El getElementFromString(String el) throws ElementsException {
        if (el.toLowerCase().equals("electric")) {
            return El.ELECTRIC;
        }

        if (el.toLowerCase().equals("fire")) {
            return El.FIRE;
        }

        if (el.toLowerCase().equals("ground")) {
            return El.GROUND;
        }

        if (el.toLowerCase().equals("ice")) {
            return El.ICE;
        }

        if (el.toLowerCase().equals("water")) {
            return El.WATER;
        }

        throw new ElementsException(1);
    }

    /**
     * Mendapatkan elemental advantage antara {@link Elements} pemanggil dan
     * {@link Elements} lawan.
     * @param elLawan {@link Elements} "musuh"
     * @return Double nilai elemental advanatage (atau disadvantage).
     */
    public Double getElementalAdvantage(Elements elLawan) {
        Double maks = 0.0;
        for (El el1 : elementsList) {
            for (El el2 : elLawan.elementsList) {
                maks = max(maks, getElementalAdvantage(el1, el2));
            }
        }

        return maks;
    }

    /**
     * Mendapatkan elemental advantage antara sebuah {@link El} dengan sebuah
     * {@link El} lain.
     * @param el1 {@link El} pertama
     * @param el2 {@link El} kedua
     * @return Double Elemental advantage antara sebuah el1 dengan el2.
     */
    public static Double getElementalAdvantage(El el1, El el2) {
        switch (el1) {
            case ELECTRIC:
                switch (el2) {
                    case WATER:
                        return 2.0;
                    case ICE:
                        return 1.5;
                    case GROUND:
                        return 0.0;
                    default:
                        return 1.0;
                }
            case FIRE:
                switch (el2) {
                    case ICE:
                        return 2.0;
                    case GROUND:
                        return 0.5;
                    case WATER:
                        return 0.0;
                    default:
                        return 1.0;
                }
            case GROUND:
                switch (el2) {
                    case ELECTRIC:
                        return 2.0;
                    case FIRE:
                        return 1.5;
                    case ICE:
                        return 0.0;
                    default:
                        return 1.0;
                }
            case ICE:
                switch (el2) {
                    case FIRE:
                        return 0.9;
                    case ELECTRIC:
                        return 0.5;
                    case GROUND:
                        return 2.9;
                    default:
                        return 1.0;
                }
            default: // case WATER
                switch (el2) {
                    case FIRE:
                        return 2.0;
                    case ELECTRIC:
                        return 0.0;
                    default:
                        return 1.0;
                }
        }
    }
}
