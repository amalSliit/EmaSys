package com.esad.emasys.model;

import jakarta.persistence.*;

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

    @Column(nullable = false, name = "photo_url")
    private String photoUrl;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

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

    // Getter and Setter for User
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}

