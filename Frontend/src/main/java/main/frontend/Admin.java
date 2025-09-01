package main.frontend;

// Admin Model Class (extends User information)
public class Admin {
    private String adminId;
    private String designation;
    private User userDetails;

    public Admin() {}

    public Admin(String adminId, String designation, User userDetails) {
        this.adminId = adminId;
        this.designation = designation;
        this.userDetails = userDetails;
    }

    // Getters and Setters
    public String getAdminId() { return adminId; }
    public void setAdminId(String adminId) { this.adminId = adminId; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public User getUserDetails() { return userDetails; }
    public void setUserDetails(User userDetails) { this.userDetails = userDetails; }
}