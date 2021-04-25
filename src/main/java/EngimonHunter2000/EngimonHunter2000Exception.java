package EngimonHunter2000;

/**
 * abstract class untuk class error dari modul2 atau class lainnya pada
 * {@link EngimonHunter2000}
 * @author Josep Marcello
 */
public abstract class EngimonHunter2000Exception extends Exception {
    private static final long serialVersionUID = 1L; // agak cursed tapi yasudala

    /**
     * Membuat {@link Exception} baru dengan sebuah pesan kesalahan
     * @param pesan kesalahan
     */
    public EngimonHunter2000Exception(String status) {
        super(status);
    }
    /**
     * metode yang mengembalikan pesan exception. Sama dengan metode
     * getMessage()
     * @return String yang berisi pesan exception
     */
    public abstract String what();
    /**
     * metode yang menuliskan pesan exception ke layar
     */
    public void bruh() {
        System.err.println(what());
    }
}
