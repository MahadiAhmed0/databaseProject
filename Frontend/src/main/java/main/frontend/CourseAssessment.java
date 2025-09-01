package main.frontend;

// CourseAssessment Model Class
public class CourseAssessment {
    private int assessmentId;
    private int offeringId;
    private int assessmentTypeId;
    private String assessmentName;
    private double totalMarks;
    private String courseOffering;
    private String assessmentType;

    public CourseAssessment() {}

    public CourseAssessment(int assessmentId, int offeringId, int assessmentTypeId,
                            String assessmentName, double totalMarks, String courseOffering,
                            String assessmentType) {
        this.assessmentId = assessmentId;
        this.offeringId = offeringId;
        this.assessmentTypeId = assessmentTypeId;
        this.assessmentName = assessmentName;
        this.totalMarks = totalMarks;
        this.courseOffering = courseOffering;
        this.assessmentType = assessmentType;
    }

    // Getters and Setters
    public int getAssessmentId() { return assessmentId; }
    public void setAssessmentId(int assessmentId) { this.assessmentId = assessmentId; }

    public int getOfferingId() { return offeringId; }
    public void setOfferingId(int offeringId) { this.offeringId = offeringId; }

    public int getAssessmentTypeId() { return assessmentTypeId; }
    public void setAssessmentTypeId(int assessmentTypeId) { this.assessmentTypeId = assessmentTypeId; }

    public String getAssessmentName() { return assessmentName; }
    public void setAssessmentName(String assessmentName) { this.assessmentName = assessmentName; }

    public double getTotalMarks() { return totalMarks; }
    public void setTotalMarks(double totalMarks) { this.totalMarks = totalMarks; }

    public String getCourseOffering() { return courseOffering; }
    public void setCourseOffering(String courseOffering) { this.courseOffering = courseOffering; }

    public String getAssessmentType() { return assessmentType; }
    public void setAssessmentType(String assessmentType) { this.assessmentType = assessmentType; }
}
