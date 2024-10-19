package com.esad.emasys.model;

import jakarta.persistence.*;

@Entity
@Table(name = "leave_entitlement")
public class LeaveBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee; // Reference to Employee

    @Column(name = "sick", nullable = false, columnDefinition = "int default 10")
    private int sickLeave = 10;

    @Column(name = "annual", nullable = false, columnDefinition = "int default 12")
    private int annualLeave = 12; // Default annual leave balance

    @Column(name = "casual", nullable = false, columnDefinition = "int default 7")
    private int casualLeave = 7; // Default casual leave balance

    @Column(name = "paternity", nullable = false, columnDefinition = "int default 7")
    private int paternityLeave = 7; // Default paternity leave balance

    @Column(name = "maternity", nullable = false, columnDefinition = "int default 30")
    private int maternityLeave = 30; // Default maternity leave balance

    @Column(name = "compensatory", nullable = false, columnDefinition = "int default 0")
    private int compensatoryLeave = 0; // Default compensatory leave balance

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getSickLeave() {
        return sickLeave;
    }

    public void setSickLeave(int sickLeave) {
        this.sickLeave = sickLeave;
    }

    public int getAnnualLeave() {
        return annualLeave;
    }

    public void setAnnualLeave(int annualLeave) {
        this.annualLeave = annualLeave;
    }

    public int getCasualLeave() {
        return casualLeave;
    }

    public void setCasualLeave(int casualLeave) {
        this.casualLeave = casualLeave;
    }

    public int getPaternityLeave() {
        return paternityLeave;
    }

    public void setPaternityLeave(int paternityLeave) {
        this.paternityLeave = paternityLeave;
    }

    public int getMaternityLeave() {
        return maternityLeave;
    }

    public void setMaternityLeave(int maternityLeave) {
        this.maternityLeave = maternityLeave;
    }

    public int getCompensatoryLeave() {
        return compensatoryLeave;
    }

    public void setCompensatoryLeave(int compensatoryLeave) {
        this.compensatoryLeave = compensatoryLeave;
    }
}
