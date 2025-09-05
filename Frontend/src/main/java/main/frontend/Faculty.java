package main.frontend;


public class Faculty {
    private String facultyId;
    private String designation;
    private String specialization;
    private User userDetails;

    public Faculty() {}

    public Faculty(String facultyId, String designation, String specialization, User userDetails) {
        this.facultyId = facultyId;
        this.designation = designation;
        this.specialization = specialization;
        this.userDetails = userDetails;
    }

    // Getters and Setters
    public String getFacultyId() { return facultyId; }
    public void setFacultyId(String facultyId) { this.facultyId = facultyId; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public User getUserDetails() { return userDetails; }
    public void setUserDetails(User userDetails) { this.userDetails = userDetails; }
}