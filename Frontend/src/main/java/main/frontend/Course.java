package main.frontend;

// Course Model Class
public class Course {
    private int courseId;
    private String courseCode;
    private String courseName;
    private double credit;
    private int semester;

    public Course() {}

    public Course(int courseId, String courseCode, String courseName, double credit, int semester) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credit = credit;
        this.semester = semester;
    }

    // Getters and Setters
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public double getCredit() { return credit; }
    public void setCredit(double credit) { this.credit = credit; }

    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }
}