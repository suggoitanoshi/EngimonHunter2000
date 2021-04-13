package EngimonHunter2000;

public class DexException extends EngimonHunter2000Exception {
    private int msgID;
    private final String[] msgs = {
        "asdasd",
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