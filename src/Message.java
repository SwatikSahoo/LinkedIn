import java.util.Date;

public class Message {
    private final String id;
    private final User sender;
    private final User receiver;
    private final String content;
    private final Date timestamp;

    public Message(String id, User sender, User receiver, String content, Date timestamp) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = timestamp;
    }
}
