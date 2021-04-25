package EngimonHunter2000;

/**
 * Class Exception untuk {@link Dex} 
 * ID: Alasan 
 * --|---------
 * 0 : Nama spesies yang akan dibuat tidak ada pada EngiDex
 * 1 : File yang dibaca tidak menghasilkan Dex berisi 
 * 2 : File yang dibaca memiliki entry invalid
 * 
 * @author Alvin Wilta
 */
public class EngimonSpeciesException extends EngimonHunter2000Exception {
    private static final long serialVersionUID = 1L; // i think this is cursed
    private int msgID;
    private static final String[] msgs = { "Gagal membuat object EngimonSpecies, nama spesies tidak dikenali",
            "Elemen pada engimon melebihi 2 elemen!" };

    public EngimonSpeciesException(int id) {
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
