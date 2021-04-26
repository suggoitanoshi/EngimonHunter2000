package EngimonHunter2000;

/**
 * Exception ketika terjadi kesalahan pada object class {@link Element}
 * ID   : Alasan
 * -----|-------
 * 0    : Parent tidak memiliki level yang cukup
 * 1    : Engimon tidak bisa breeding dengan dirinya
 * 2    : Skill engimon parent 1 kosong
 * 3    : Skill engimon parent 2 kosong
 * 4    : Kebanyakan iterasi di max_skill
 * @author Alvin Wilta
 */
public class BreedingException extends EngimonHunter2000Exception {
    private static final long serialVersionUID = 1L; // i think this is cursed
    private final int msgID;
    static private final String[] msgs = { "Parent dari engimon memiliki level < 4",
            "Engimon tidak bisa dikawinkan dengan dirinya sendiri", "Gagal membuat arraylist 1",
            "Gagal membuat arraylist 2", "Kebanyakan iterasi :(" };

    /**
     * Constructor ElementsException
     * ID   : Alasan
     * -----|-------
     * 0    : Parent tidak memiliki level yang cukup
     * 1    : Engimon tidak bisa breeding dengan dirinya
     * 2    : Skill engimon parent 1 kosong
     * 3    : Skill engimon parent 2 kosong
     * 4    : Kebanyakan iterasi di max_skill
     * @param id nomor exception
     */
    public BreedingException(int id) {
        super(msgs[id]);
        msgID = id;
        this.bruh();
    }

    public String what() {
        return msgs[msgID];
    }

    public void bruh() {
        System.out.println(what());
    }
}
