package EngimonHunter2000;

/**
 * Exception ketika terjadi kesalahan pada object class {@link Element}
 * ID   : Alasan
 * -----|-------
 * 0    : Konversi dari string ke Element gagal
 * @author Josep Marcello
 */
public class ElementException extends EngimonHunter2000Exception {
    private static final long serialVersionUID = 1L; // i think this is cursed
    private final int msgID;
    static private final String[] msgs = {
        "Konversi string ke element gagal karena nama element invalid",
    };

    /**
     * Constructor ElementsException
     * ID   : Alasan
     * -----|-------
     * 0    : Konversi dari string ke Element gagal
     * @param id nomor exception
     */
    public ElementException(int id) {
        super(msgs[id]);
        msgID = id;
    }

    public String what() {
        return msgs[msgID];
    }

    public void bruh() {
        System.out.println(what());
    }
}
