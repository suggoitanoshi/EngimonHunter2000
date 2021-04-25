package EngimonHunter2000;

/**
 * Exception ketika terjadi kesalahan pada {@link Item}
 * ID  : Alasan
 * ----|--------
 * 0   : Mastery level lebih dari 3
 * 1   : Mastery level kurang dari 1
 */
public class SkillEngimonException extends EngimonHunter2000Exception {
    private static final long serialVersionUID = 1l;
    private final int msgID;
    private static final String[] msgs = {
        "Mastery level akan lebih dari 3",
        "Mastery level akan kurang dari 1"
    };

    public SkillEngimonException(int id) {
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
