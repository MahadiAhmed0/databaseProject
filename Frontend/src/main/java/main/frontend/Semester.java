package main.frontend;

import java.time.LocalDate;

// Semester Model Class
public class Semester {
    private int semesterId;
    private String term;
    private LocalDate startDate;

    public Semester() {}

    public Semester(int semesterId, String term, LocalDate startDate) {
        this.semesterId = semesterId;
        this.term = term;
        this.startDate = startDate;
    }

    // Getters and Setters
    public int getSemesterId() { return semesterId; }
    public void setSemesterId(int semesterId) { this.semesterId = semesterId; }

    public String getTerm() { return term; }
    public void setTerm(String term) { this.term = term; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
}