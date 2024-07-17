package com.api.APIMarcheAvecEliane.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "L'ID ne peut pas être null")
    @Column(name = "id_coordinator", updatable = false)
    private UUID id;

    @NotNull(message = "Le prénom ne peut pas être null")
    @Size(max = 25, message = "Le prénom ne peut pas dépasser 25 caractères")
    @Column(name = "first_name_coordinator")
    private String firstname;

    @NotNull(message = "Le nom de famille ne peut pas être null")
    @Size(max = 35, message = "Le nom de famille ne peut pas dépasser 35 caractères")
    @Column(name = "last_name_coordinator")
    private String lastname;

    @Email(message = "L'email doit être valide")
    @NotNull(message = "L'email ne peut pas être null")
    @Size(max = 320, message = "L'email ne peut pas dépasser 320 caractères")
    @Column(name = "email_coordinator")
    private String email;

    @NotNull(message = "Le mot de passe ne peut pas être null")
    @Size(max = 64, message = "Le mot de passe ne peut pas dépasser 64 caractères")
    @Column(name = "password_coordinator")
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