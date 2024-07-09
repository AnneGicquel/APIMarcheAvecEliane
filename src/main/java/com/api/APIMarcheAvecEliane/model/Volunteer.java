package com.api.APIMarcheAvecEliane.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder // Lombok qui ajoute les methodes build pour le register
@Table (name ="Volunteer")
@Entity
public class Volunteer {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_volunteer", updatable = false, nullable = false)
    private UUID id;

    @NotNull(message = "Le prénom ne peut pas être null")
    @Size(max = 25, message = "Le prénom ne peut pas dépasser 25 caractères")
    @Column(name = "first_name_volunteer")
    private String firstname;

    @NotNull(message = "Le nom de famille ne peut pas être null")
    @Size(max = 35, message = "Le nom de famille ne peut pas dépasser 35 caractères")
    @Column(name = "last_name_volunteer")
    private String lastname;

    @NotNull(message = "L'adresse de la rue ne peut pas être null")
    @Size(max = 100, message = "L'adresse de la rue ne peut pas dépasser 100 caractères")
    @Column(name = "address_street_volunteer")
    private String addressStreet;

    @NotNull(message = "La ville ne peut pas être null")
    @Size(max = 50, message = "La ville ne peut pas dépasser 50 caractères")
    @Column(name = "address_city_volunteer")
    private String addressCity;

    @NotNull(message = "Le code postal ne peut pas être null")
    @Size(max = 10, message = "Le code postal ne peut pas dépasser 10 caractères")
    @Column(name = "zip_code_volunteer")
    private String zipCode;

    @NotNull(message = "Le numéro de mobile ne peut pas être null")
    @Size(max = 20, message = "Le numéro de mobile ne peut pas dépasser 20 caractères")
    @Column(name = "mobile_number_volunteer")
    private String mobileNumber;

    @Nullable
    @Size(max = 20, message = "Le numéro de téléphone fixe ne peut pas dépasser 20 caractères")
    @Column(name = "landline_number_volunteer")
    private String landlineNumber;

    @Email(message = "L'email doit être valide")
    @NotNull(message = "L'email ne peut pas être null")
    @Size(max = 50, message = "L'email ne peut pas dépasser 50 caractères")
    @Column(name = "email_volunteer")
    private String email;

    @NotNull(message = "Le mot de passe ne peut pas être null")
    @Size(max = 64, message = "Le mot de passe ne peut pas dépasser 64 caractères")
    @Column(name = "password_volunteer", nullable = false, length = 64)
    private String password;

    @Nullable
    @Column(name = "date_of_birth_volunteer")
    private Date dateOfBirth;

    @NotNull(message = "La date d'entrée ne peut pas être null")
    @Column(name = "entry_date_volunteer")
    private Date entryDate;

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

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getLandlineNumber() {
        return landlineNumber;
    }

    public void setLandlineNumber(String landlineNumber) {
        this.landlineNumber = landlineNumber;
    }

    public String getEmailVolunteer() {
        return email;
    }

    public void setEmailVolunteer(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }
}
