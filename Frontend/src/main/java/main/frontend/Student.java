package main.frontend;


// Student Model Class (extends User information)
public class Student {
    private String studentId;
    private String guardianFullName;
    private String guardianStudentRelation;
    private String guardianContact;
    private User userDetails;

    public Student() {}

    public Student(String studentId, String guardianFullName, String guardianStudentRelation,
                   String guardianContact, User userDetails) {
        this.studentId = studentId;
        this.guardianFullName = guardianFullName;
        this.guardianStudentRelation = guardianStudentRelation;
        this.guardianContact = guardianContact;
        this.userDetails = userDetails;
    }

    // Getters and Setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getGuardianFullName() { return guardianFullName; }
    public void setGuardianFullName(String guardianFullName) { this.guardianFullName = guardianFullName; }

    public String getGuardianStudentRelation() { return guardianStudentRelation; }
    public void setGuardianStudentRelation(String guardianStudentRelation) { this.guardianStudentRelation = guardianStudentRelation; }

    public String getGuardianContact() { return guardianContact; }
    public void setGuardianContact(String guardianContact) { this.guardianContact = guardianContact; }

    public User getUserDetails() { return userDetails; }
    public void setUserDetails(User userDetails) { this.userDetails = userDetails; }
}
