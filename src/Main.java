import java.util.Arrays;
import java.util.Date;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        LinkedInService sys= LinkedInService.getInstance();

        User user1= new User("1","swatik","swatik@gmail.com","1234");
        User user2= new User("2","sahoo","sahoo@gmail.com","1abc4");
        sys.registerUser(user1);
        sys.registerUser(user2);

        User loggedInUser= sys.loginUser("swatik@gmail.com","1234");
        if (loggedInUser!=null){
            System.out.println("User Logged In "+ loggedInUser.getName());
        }else{
            System.out.println("Invalid Username or Password");
        }

        Profile pro= new Profile();
        pro.setHeadline("Software Engineer");
        pro.setSummary("Passionate about coding and problem solving");
        loggedInUser.setProfile(pro);
        sys.updateUserProfile(loggedInUser);

        sys.sendConnectionRequest(user1,user2);
        sys.acceptConnectionRequest(user2,user1);

        JobPosting jobPosting= new JobPosting("1","Software Engineer", "We are hiring", Arrays.asList("Golang","Java"),"Bengaluru", new Date());
        sys.postJobListing(jobPosting);

        List<User> res=sys.searchUsers("swatik");
        System.out.println("Search Results User:");
        for (User user:res){
            System.out.println("Name: "+user.getName());
            System.out.println("Headline: "+user.getProfile().getHeadline());
            System.out.println();
        }
        List<JobPosting> rest=sys.searchJobListing("Software");
        System.out.println("Search Results JobListing:");
        for (JobPosting job:rest){
            System.out.println("Title: "+ job.getTitle());
            System.out.println("Description: "+job.getDescription());
            System.out.println();
        }

        sys.sendMessage(user1,user2,"Hi Sahooo..");

        List<Notification> noti= sys.getNotification(user2.getId());
        for (Notification notif:noti){
            System.out.println("Type: "+ notif.getType());
            System.out.println("Content: "+notif.getContent());
            System.out.println();
        }
    }
}