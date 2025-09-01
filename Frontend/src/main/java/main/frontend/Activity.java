package main.frontend;

import java.time.LocalDateTime;

public class Activity {
    private LocalDateTime time;
    private String type;
    private String description;
    private String user;

    public Activity() {}

    public Activity(LocalDateTime time, String type, String description, String user) {
        this.time = time;
        this.type = type;
        this.description = description;
        this.user = user;
    }

    // Getters and Setters
    public LocalDateTime getTime() { return time; }
    public void setTime(LocalDateTime time) { this.time = time; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
}
