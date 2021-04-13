package EngimonHunter2000;

/**
 * Class Exception untuk {@link Dex}
 * ID   : Alasan
 * -----|-------
 * 0    : Parsing file gagal, terjadi IO Exception
 * 1    : File yang dibaca tidak menghasilkan Dex berisi
 * 2    : File yang dibaca memiliki entry invalid
 * @author Josep Marcello
 */
public class DexException extends EngimonHunter2000Exception {
    private static final long serialVersionUID = 1L; // i think this is cursed
    private int msgID;
    private final String[] msgs = {
        "Parsing file gagal, terjadi IO Exception",
        "File yang dibaca tidak menghasilkan Dex berisi",
        "File yang dibaca memiliki entry invalid",
    };

    public DexException(int id) {
        msgID = id;
    }

    public String what() {
        return msgs[msgID];
    }

    public void bruh() {
        System.out.println(what());
    }
}