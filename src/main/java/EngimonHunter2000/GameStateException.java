package EngimonHunter2000;

/**
 * Exception ketika terjadi kesalahan pada {@link Inventory}
 * ID  : Alasan
 * ----|--------
 * 0   : Gagal mendapatkan file resource
 * 1   : Gagal me-load game
 * 2   : Gagal menyimpan game
 */
public class GameStateException extends EngimonHunter2000Exception {
    private static final long serialVersionUID = 1l;
    private final int msgID;
    static private final String[] msgs = {
        "Gagal mendapatkan file resource",
        "Gagal me-load game",
        "Gagal menyimpan game",
    };

    public GameStateException(int id) {
        super(msgs[id]);
        this.msgID = id;
    }

    public String what() {
        return msgs[this.msgID];
    }
}
