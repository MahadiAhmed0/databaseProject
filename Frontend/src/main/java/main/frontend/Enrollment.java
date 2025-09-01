package main.frontend;
// Enrollment Model Class
public class Enrollment {
    private int enrollmentId;
    private String studentId;
    private int offeringId;
    private String studentName;
    private String courseName;
    private String courseCode;
    private String semesterTerm;
    private String semesterStartDate;
    private String facultyName;

    public Enrollment() {}

    public Enrollment(int enrollmentId, String studentId, int offeringId, String studentName,
                      String courseName, String courseCode, String semesterTerm,
                      String semesterStartDate, String facultyName) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.offeringId = offeringId;
        this.studentName = studentName;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.semesterTerm = semesterTerm;
        this.semesterStartDate = semesterStartDate;
        this.facultyName = facultyName;
    }

    // Getters and Setters
    public int getEnrollmentId() { return enrollmentId; }
    public void setEnrollmentId(int enrollmentId) { this.enrollmentId = enrollmentId; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public int getOfferingId() { return offeringId; }
    public void setOfferingId(int offeringId) { this.offeringId = offeringId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getSemesterTerm() { return semesterTerm; }
    public void setSemesterTerm(String semesterTerm) { this.semesterTerm = semesterTerm; }

    public String getSemesterStartDate() { return semesterStartDate; }
    public void setSemesterStartDate(String semesterStartDate) { this.semesterStartDate = semesterStartDate; }

    public String getFacultyName() { return facultyName; }
    public void setFacultyName(String facultyName) { this.facultyName = facultyName; }
}
