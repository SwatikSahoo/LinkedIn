import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class LinkedInService {
    private static LinkedInService instance;
    private final Map<String, User> users;
    private final Map<String,JobPosting> jobPostings;
    private final Map<String,List<Notification>> notifications;

    private LinkedInService() {
        this.users = new ConcurrentHashMap<>();
        this.jobPostings = new ConcurrentHashMap<>();
        this.notifications = new ConcurrentHashMap<>();
    }
    public static synchronized LinkedInService getInstance(){
        if (instance==null){
            instance=new LinkedInService();
        }
        return instance;
    }
    public void registerUser(User user){
        users.put(user.getId(),user);
    }
    public User loginUser(String email, String password){
        for (User user:users.values()){
            if (user.getEmail().equals(email) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
    public void updateUserProfile(User user){
        users.put(user.getId(),user);
    }
    public void sendConnectionRequest(User sender,User receiver){
        Connection conn= new Connection(sender, new Timestamp(System.currentTimeMillis()));
        receiver.getConnections().add(conn);
        Notification noti= new Notification(UUID.randomUUID().toString(),receiver,NotificationType.ConnectionRequest,
                "New Connection Request From "+sender.getName(), new Timestamp(System.currentTimeMillis()));
        addNotification(receiver.getId(),noti);
    }

    private void addNotification(String id, Notification noti) {
        notifications.computeIfAbsent(id, k-> new CopyOnWriteArrayList<>()).add(noti);
    }
    public void acceptConnectionRequest(User user, User connectionUser){
        for (Connection conn: user.getConnections()){
            if (conn.getUser().equals(connectionUser)){
                user.getConnections().add(new Connection(connectionUser, new Timestamp(System.currentTimeMillis())));
                break;
            }
        }
    }
    public List<User> searchUsers(String keyword){
        List<User> res= new ArrayList<>();
        for (User user:users.values()){
            if (user.getName().contains(keyword)){
                res.add(user);
            }
        }
        return res;
    }
    public void postJobListing(JobPosting jobPosting){
        jobPostings.put(jobPosting.getId(), jobPosting);
        for (User user:users.values()){
            Notification noti= new Notification(UUID.randomUUID().toString(),user,NotificationType.JobPosting,
                    "New Job Posting "+jobPosting.getTitle(), new Timestamp(System.currentTimeMillis()));
            addNotification(user.getId(),noti);
        }
    }
    public List<JobPosting> searchJobListing(String keyword){
        List<JobPosting> res= new ArrayList<>();
        for (JobPosting job:jobPostings.values()){
            if (job.getTitle().contains(keyword) || job.getDescription().contains(keyword)){
                res.add(job);
            }
        }
        return res;
    }
    public void sendMessage(User sender, User receiver, String content){
        Message msg= new Message(UUID.randomUUID().toString(),sender,receiver,content, new Date());
        receiver.getInbox().add(msg);
        sender.getSentMessage().add(msg);
        Notification noti= new Notification(UUID.randomUUID().toString(),receiver,NotificationType.Message,content,new Timestamp(System.currentTimeMillis()));
        addNotification(receiver.getId(),noti);
    }
    public List<Notification> getNotification(String userId){
        return notifications.getOrDefault(userId, new ArrayList<>());
    }

}
