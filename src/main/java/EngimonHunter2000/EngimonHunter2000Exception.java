package EngimonHunter2000;

/**
 * abstract class untuk class error dari modul2 atau class lainnya pada
 * {@link EngimonHunter2000}
 * @author y e e wangy wangy
 */
public abstract class EngimonHunter2000Exception extends Exception {
    private static final long serialVersionUID = 1L; // agak cursed tapi yasudala
    /**
     * metode yang mengembalikan pesan exception
     * @return String yang berisi pesan exception
     */
    public abstract String what();
    /**
     * metode yang menuliskan pesan exception ke layar
     */
    public abstract void bruh();
}
