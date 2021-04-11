package EngimonHunter2000;

/**
 * Exception ketika terjadi kesalahan pada object class {@link ElementsList}
 * ID   : Alasan
 * -----|-------
 * 0    : Konstruktor menghasilkan list tanpa element
 * @author Josep Marcello
 */
public class ElementsListException extends EngimonHunter2000Exception {
    private static final long serialVersionUID = 1L; // i think this is cursed
    private final int msgID;
    static private final String[] msgs = {
        "Konstruktor Elements gagal, array/list konstruktor harus memiliki elemen valid dan tidak kosong",
    };

    /**
     * Constructor ElementsException
     * ID   : Alasan
     * -----|-------
     * 0    : Konstruktor menghasilkan list tanpa element
     * @param id nomor exception
     */
    public ElementsListException(int id) {
        msgID = id;
    }

    public String what() {
        return msgs[msgID];
    }

    public void bruh() {
        System.out.println(what());
    }
}
