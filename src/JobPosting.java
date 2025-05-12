import java.util.Date;
import java.util.List;

public class JobPosting {
    private final String id;
    private final String title;
    private final String description;
    private final List<String> requirements;
    private final String locations;
    private final Date postDate;

    public JobPosting(String id, String title, String description, List<String> requirements, String locations, Date postDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.requirements = requirements;
        this.locations = locations;
        this.postDate = postDate;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getRequirements() {
        return requirements;
    }

    public String getLocations() {
        return locations;
    }

    public Date getPostDate() {
        return postDate;
    }
}
