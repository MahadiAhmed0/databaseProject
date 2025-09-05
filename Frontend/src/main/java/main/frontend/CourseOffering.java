package main.frontend;

import java.time.LocalDate;

// CourseOffering Model Class
public class CourseOffering {
    private int offeringId;
    private int courseId;
    private int semesterId;
    private String facultyId;
    private String courseCode;
    private String courseName;
    private String semesterTerm;
    private LocalDate startDate;
    private String facultyName;

    public CourseOffering() {}

    public CourseOffering(int offeringId, int courseId, int semesterId, String facultyId,
                          String courseCode, String courseName, String semesterTerm,
                          LocalDate startDate, String facultyName) {
        this.offeringId = offeringId;
        this.courseId = courseId;
        this.semesterId = semesterId;
        this.facultyId = facultyId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.semesterTerm = semesterTerm;
        this.startDate = startDate;
        this.facultyName = facultyName;
    }

    // Getters and Setters
    public int getOfferingId() { return offeringId; }
    public void setOfferingId(int offeringId) { this.offeringId = offeringId; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public int getSemesterId() { return semesterId; }
    public void setSemesterId(int semesterId) { this.semesterId = semesterId; }

    public String getFacultyId() { return facultyId; }
    public void setFacultyId(String facultyId) { this.facultyId = facultyId; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getSemesterTerm() { return semesterTerm; }
    public void setSemesterTerm(String semesterTerm) { this.semesterTerm = semesterTerm; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public String getFacultyName() { return facultyName; }
    public void setFacultyName(String facultyName) { this.facultyName = facultyName; }
}