package EngimonHunter2000;

public class PlayerException extends EngimonHunter2000Exception {
    private static final long serialVersionUID = 1;
    private final int msgID;
    static private final String[] msgs = {
        "Tidak ada Engimon tersebut di dalam Inventory",
        "Tidak ada Item Skill tersebut di dalam Inventory"
    };

    public PlayerException(int id) {
        super(msgs[id]);
        this.msgID = id;
    }

    public String what() {
        return msgs[this.msgID];
    }

    public void bruh() {
        System.out.println(what());
    }
}
