package main.frontend;

// AssessmentType Model Class
public class AssessmentType {
    private int assessmentTypeId;
    private String name;
    private double marksPercentage;

    public AssessmentType() {}

    public AssessmentType(int assessmentTypeId, String name, double marksPercentage) {
        this.assessmentTypeId = assessmentTypeId;
        this.name = name;
        this.marksPercentage = marksPercentage;
    }

    // Getters and Setters
    public int getAssessmentTypeId() { return assessmentTypeId; }
    public void setAssessmentTypeId(int assessmentTypeId) { this.assessmentTypeId = assessmentTypeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getMarksPercentage() { return marksPercentage; }
    public void setMarksPercentage(double marksPercentage) { this.marksPercentage = marksPercentage; }
}