package com.api.APIMarcheAvecEliane.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
public class Coordinator {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_coordinator", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "first_name_coordinator", nullable = false, length = 25)
    private String firstName;

    @Column(name = "last_name_coordinator", nullable = false, length = 35)
    private String lastName;

    @Column(name = "email_coordinator", nullable = false, length = 50)
    private String email;

    @Column(name = "password_coordinator", nullable = false, length = 64)
    private String password;

    // GETTERS & SETTERS

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}