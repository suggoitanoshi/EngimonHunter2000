package EngimonHunter2000;

/**
 * Exception ketika terjadi kesalahan pada {@link Item}
 * ID  : Alasan
 * ----|--------
 * 0   : Mastery level item bukan 1
 * 1   : Skill item tidak cocok dengan Engimon
 * 2   : Input pilihan untuk mengganti skill di luar batas
 * @author Jeane Mikha Erwansyah
 */
public class ItemException extends EngimonHunter2000Exception {
    private static final long serialVersionUID = 1l;
    private final int msgID;
    static private final String[] msgs = {
        "Mastery level item bukan 1",
        "Skill item tidak cocok dengan Engimon",
        "Input pilihan untuk mengganti skill di luar batas"
    };

    public ItemException(int id) {
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

