package com.esad.emasys.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false, name = "photo_url", length = 500)
    private String photoUrl;

    @Column(nullable = false, unique = true) // Ensure the email is unique
    private String email;

    private String password; // Nullable for Google login users

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.EMPLOYEE;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    // Getters and Setters
    public enum Role {
        ADMIN,
        EMPLOYEE,
        MANAGER
    }

    // Getter and Setter for ID
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter and Setter for FirstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter and Setter for LastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter and Setter for PhotoUrl
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    // Getter for Department
    public Department getDepartment() {
        return department;
    }

    // Setter for Department (with null-check and initialization)
    public void setDepartment(Department department) {
        this.department = department;
    }

    // Getter and Setter for Department ID
    public Integer getDepartmentId() {
        return department != null ? department.getId() : null;
    }

    public void setDepartmentId(Integer departmentId) {
        if (this.department == null) {
            this.department = new Department(); // Initialize department object
        }
        this.department.setId(departmentId);
    }

    // Getter for Position
    public Position getPosition() {
        return position;
    }

    // Setter for Position (with null-check and initialization)
    public void setPosition(Position position) {
        this.position = position;
    }

    // Getter and Setter for Position ID
    public Integer getPositionId() {
        return position != null ? position.getId() : null;
    }

    public void setPositionId(Integer positionId) {
        if (this.position == null) {
            this.position = new Position(); // Initialize position object
        }
        this.position.setId(positionId);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

