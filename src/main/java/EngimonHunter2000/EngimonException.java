package EngimonHunter2000;

/**
 * Class Exception untuk {@link Engimon} 
 * ID  : Alasan 
 * ----|--------
 * 0   : Level engimon tidak cukup untuk dikurang
 * 1   : Gagal mencari nama skill pada listSkill engimon
 * 2   : Lives engimon tidak cukup untuk dikurang
 * 3   : Level parent engimon (salah satu atau dua) masih dibawah umur untuk breeding
 * 4   : Nama engimon tidak valid (null atau kosong)
 * @author Alvin Wilta
 */

public class EngimonException extends EngimonHunter2000Exception {
    private static final long serialVersionUID = 1L;
    private int msgID;
    private static final String[] msgs = { "Level yang dimiliki engimon tidak cukup",
            "Nama skill terkait tidak dimiliki oleh engimon ini",
            "Lives tidak bisa dikurangi hingga < 0, pastikan dicek dahulu sebelum dikurang",
            "Nama engimon tidak boleh kosong" };

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