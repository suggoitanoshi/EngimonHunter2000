package EngimonHunter2000;

/**
 * Exception ketika terjadi kesalahan pada object class {@link Element}
 * ID   : Alasan
 * -----|-------
 * 0    : Parent tidak memiliki level yang cukup
 * 1    : 
 * @author Alvin Wilta
 */
public class BreedingException extends EngimonHunter2000Exception {
    private static final long serialVersionUID = 1L; // i think this is cursed
    private final int msgID;
    static private final String[] msgs = { "Parent dari engimon memiliki level < 4",
            "Engimon tidak bisa dikawinkan dengan dirinya sendiri" };

    /**
     * Constructor ElementsException
     * ID   : Alasan
     * -----|-------
     * 0    : Parent tidak memiliki level yang cukup
     * 1    :
     * @param id nomor exception
     */
    public BreedingException(int id) {
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
