package com.sanjay.studentmanagement.student;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String branch;

    @Min(1)
    @Column(nullable = false)
    private Integer year;

    public Student() {}

    public Student(String name, String email, String branch, Integer year) {
        this.name = name;
        this.email = email;
        this.branch = branch;
        this.year = year;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getBranch() { return branch; }
    public Integer getYear() { return year; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setBranch(String branch) { this.branch = branch; }
    public void setYear(Integer year) { this.year = year; }
}
