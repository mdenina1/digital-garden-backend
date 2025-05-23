package com.markdenina.digital_garden_backend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")  // "user" is a reserved keyword in some DBs, so rename table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "theme_pref")
    private String themePref;

    // Bidirectional relationships (optional)
    @OneToMany(mappedBy = "user")
    private List<Topic> topics = new ArrayList<Topic>();

    @OneToMany(mappedBy = "user")
    private List<Page> pages = new ArrayList<Page>();

    @OneToMany(mappedBy = "user")
    private List<Link> links = new ArrayList<Link>();

    // Getters, setters, constructors
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getThemePref() {
        return themePref;
    }

    public void setThemePref(String themePref) {
        this.themePref = themePref;
    }
}
