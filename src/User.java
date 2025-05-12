import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User {
    private final String id;
    private final String name;
    private final String email;
    private final String password;
    private Profile profile;
    private final List<Connection> connections;
    private final List<Message> inbox;
    private final List<Message> sentMessage;


    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.profile =new Profile();
        connections=new ArrayList<>();
        inbox=new ArrayList<>();
        sentMessage=new ArrayList<>();
    }
    public void setProfile(Profile pro){
        profile=pro;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }

    public Profile getProfile() {
        return profile;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public List<Message> getInbox() {
        return inbox;
    }

    public List<Message> getSentMessage() {
        return sentMessage;
    }

}
