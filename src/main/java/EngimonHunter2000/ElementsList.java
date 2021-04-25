package EngimonHunter2000;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * List element yang dapat digunakan untuk {@link Skill},
 * {@link EngimonSpecies}, {@link Engimon}, dan lainnya.
 * Tidak boleh kosong.
 * @author Josep Marcello
 */
public class ElementsList implements Serializable {
    public static final long serialVersionUID = 1L;
    private Set<Element> elementsList;

    /**
     * @deprecated
     * Default constructor untuk Elements. Tipe default adalah
     * {@link Element}.ELECTRIC dengan hanya 1 elemen.
     */
    public ElementsList() {
        elementsList = new HashSet<Element>();
        elementsList.add(Element.ELECTRIC); // default Engimon and move has the type Electric
    }

    /**
     * Constructor dengan menggunakan array of {@link Element} yang sudah ditentukan.
     * @param elms array of {@link Element} berisi element-element-nya
     * @throws ElementsListException
     */
    public ElementsList(Element... elms) throws ElementsListException {
        elementsList = new HashSet<Element>();
        for (Element el : elms) {
            elementsList.add(el);
        }

        if (elementsList.size() == 0) {
            throw new ElementsListException(0);
        }
    }

    /**
     * Constructor dengan menggunakan array of String yang berisi sudah
     * nama-nama {@link Element} sudah ditentukan. Jika semua nama
     * {@link Element} pada elms invalid maka (harusnya keluar exception) akan
     * menjadi {@link Element} dengan array kosong.
     * @param elms array of String yang berisi nama-nama element
     * @throws ElementsListException
     */
    public ElementsList(String... elms) throws ElementsListException {
        elementsList = new HashSet<Element>();

        for (String elStr : elms) {
            try {
                elementsList.add(Element.getElementFromString(elStr));
            } catch (ElementException err) {
                // Nama elemen salah. Skip aja.
                err.bruh();
                continue;
            }
        }

        if (elementsList.size() == 0) {
            throw new ElementsListException(0);
        }
    }

    /**
     * Getter untuk attribute elements.
     * @return Set of {@link Element} yang merupakan atribut elements.
     */
    public Set<Element> getElementsList() {
        return elementsList;
    }

    private static <T extends Number> T max(T a, T b) {
        return a.doubleValue() >= b.doubleValue() ? a : b;
    }

    /**
     * Mendapatkan elemental advantage antara {@link Element} pemanggil dan
     * {@link Element} lawan.
     * @param elLawan {@link Element} "musuh"
     * @return Double nilai elemental advanatage (atau disadvantage).
     */
    public Double getElementalAdvantage(ElementsList elLawan) {
        Double maks = 0.0;
        for (Element el1 : elementsList) {
            for (Element el2 : elLawan.elementsList) {
                maks = max(maks, Element.getElementalAdvantage(el1, el2));
            }
        }

        return maks;
    }
}
