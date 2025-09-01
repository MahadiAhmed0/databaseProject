package main.frontend;

import java.time.LocalDate;
import java.time.LocalDateTime;

// User Model Class
public class User {
    private String userId;
    private String name;
    private String role;
    private String email;
    private String phone;
    private LocalDate dob;
    private String gender;
    private String address;
    private String bloodGroup;
    private LocalDate joinDate;
    private String status;
    private String passwordHash;

    public User() {}

    public User(String userId, String name, String role, String email, String phone,
                LocalDate dob, String gender, String address, String bloodGroup,
                LocalDate joinDate, String status, String passwordHash) {
        this.userId = userId;
        this.name = name;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.bloodGroup = bloodGroup;
        this.joinDate = joinDate;
        this.status = status;
        this.passwordHash = passwordHash;
    }

    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public LocalDate getJoinDate() { return joinDate; }
    public void setJoinDate(LocalDate joinDate) { this.joinDate = joinDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
}