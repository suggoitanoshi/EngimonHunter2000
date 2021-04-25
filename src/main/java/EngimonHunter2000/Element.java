package EngimonHunter2000;

/**
 * Elemen-elemen yang ada pada gim. Tidak mencakup elemen double.
 * @author Josep Marcello
 */
public enum Element {
    /// Elemen electric
    ELECTRIC,
    /// Elemen fire
    FIRE,
    /// Elemen ground
    GROUND,
    /// Elemen ice
    ICE,
    /// Elemen water
    WATER;

    /**
     * Mendapatkan elemental advantage antara sebuah {@link Element} dengan sebuah
     * {@link Element} lain.
     * @param el1 {@link Element} pertama
     * @param el2 {@link Element} kedua
     * @return Double Elemental advantage antara sebuah el1 dengan el2.
     */
    public static Double getElementalAdvantage(Element el1, Element el2) {
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
                        return 0.0;
                    case ELECTRIC:
                        return 0.5;
                    case GROUND:
                        return 2.0;
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

    /**
     * Fungsi untuk mendapatkan {@link Element} dari nama elemen (String).
     * Tidak memerhatikan kapitalisme String. Jika String bukan
     * nama {@link Element}, maka akan dikembalikan (harusnya exception) null.
     * @param el nama Element
     * @return {@link Element} "sebenarnya" dari string (nama)
     * @throws ElementException
     */
    public static Element getElementFromString(String el) throws ElementException {
        for (Element ins : Element.values()) {
            if (ins.name().equalsIgnoreCase(el)) {
                return ins;
            }
        }

        throw new ElementException(0);
    }
}
