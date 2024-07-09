package com.api.APIMarcheAvecEliane.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder // pour le register
@Table(name ="Coordinator")
@Entity
public class Coordinator {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_coordinator", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "first_name_coordinator", nullable = false, length = 25)
    private String firstname;

    @Column(name = "last_name_coordinator", nullable = false, length = 35)
    private String lastname;

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
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
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