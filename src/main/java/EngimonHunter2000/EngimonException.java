package EngimonHunter2000;

/**
 * Class Exception untuk {@link Engimon} 
 * ID  : Alasan 
 * ----|--------
 * 0   : Inventory Penuh
 * 1   : Inventory Kosong
 * 2   : Item tidak ada di inventory
 * 3   : Indeks item tidak valid
 * @author Alvin Wilta
 */

public class EngimonException extends EngimonHunter2000Exception {
    private static final long serialVersionUID = 1L;
    private int msgID;
    private static final String[] msgs = { "Level yang dimiliki engimon tidak cukup",
            "Nama skill terkait tidak dimiliki oleh engimon ini", "Nama spesies yang tertera tidak ada pada dex" };

    public EngimonException(int id) {
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