package com.freightfox.alumni.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "alumni_profiles", uniqueConstraints = @UniqueConstraint(columnNames = "linkedin_url"))
public class AlumniProfile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String name;
    @Column(name = "current_designation")
    private String currentRole;
    @Column(nullable = false) private String university;
    private String location;
    @Column(length = 2000) private String linkedinHeadline;
    private Integer passoutYear;
    @Column(name = "linkedin_url", length = 1000) private String linkedinUrl;

    public AlumniProfile() {}
    public AlumniProfile(String name, String currentRole, String university, String location,
                         String linkedinHeadline, Integer passoutYear, String linkedinUrl) {
        this.name = name; this.currentRole = currentRole; this.university = university;
        this.location = location; this.linkedinHeadline = linkedinHeadline;
        this.passoutYear = passoutYear; this.linkedinUrl = linkedinUrl;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCurrentRole() { return currentRole; }
    public String getUniversity() { return university; }
    public String getLocation() { return location; }
    public String getLinkedinHeadline() { return linkedinHeadline; }
    public Integer getPassoutYear() { return passoutYear; }
    public String getLinkedinUrl() { return linkedinUrl; }
}
