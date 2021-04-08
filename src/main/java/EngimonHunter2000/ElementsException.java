package EngimonHunter2000;

/**
 * Exception ketika terjadi kesalahan pada object class {@link Elements}
 * ID   : Alasan
 * -----|-------
 * 0    : Konstruktor menghasilkan list tanpa element
 * 1    : Konversi string ke element gagal karena nama element invalid
 * @author y e e wangy wangy
 */
public class ElementsException extends EngimonHunter2000Exception {
    private static final long serialVersionUID = 1L; // i think this is cursed
    private final int msgID;
    static private final String[] msgs = {
        "Konstruktor Elements gagal, array/list konstruktor harus memiliki elemen valid dan tidak kosong",
        "Konversi string ke element gagal karena nama element invalid",
    };

    /**
     * Constructor ElementsException
     * ID   : Alasan
     * -----|-------
     * 0    : Konstruktor menghasilkan list tanpa element
     * 1    : Konversi string ke element gagal karena nama element invalid
     * @param id nomor exception
     */
    public ElementsException(int id) {
        msgID = id;
    }

    public String what() {
        return msgs[msgID];
    }

    public void bruh() {
        System.out.println(what());
    }
}
