package com.api.APIMarcheAvecEliane.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Elderly {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @NotNull(message = "L'ID ne peut pas être null")
    @Column(name = "id_elderly", updatable = false)
    private UUID id;

    @NotNull(message = "Le pseudo ne peut pas être null")
    @Size(max = 25, message = "Le pseudo ne peut pas dépasser 25 caractères")
    @Column(name = "pseudo_elderly")
    private String pseudo;

    @NotNull(message = "L'adresse de la rue ne peut pas être null")
    @Size(max = 100, message = "L'adresse de la rue ne peut pas dépasser 100 caractères")
    @Column(name = "address_street_elderly")
    private String addressStreet;

    @NotNull(message = "La ville ne peut pas être null")
    @Size(max = 50, message = "La ville ne peut pas dépasser 50 caractères")
    @Column(name = "address_city_elderly")
    private String addressCity;

    @NotNull(message = "Le code postal ne peut pas être null")
    @Size(max = 10, message = "Le code postal ne peut pas dépasser 10 caractères")
    @Column(name = "ZIP_code_elderly")
    private String zipCode;

    @Nullable
    @Size(max = 255, message = "Les détails de l'adresse ne peuvent pas dépasser 255 caractères")
    @Column(name = "address_details_elderly")
    private String addressDetails;

    @Nullable
    @Size(max = 20, message = "Le numéro de téléphone mobile ne peut pas dépasser 20 caractères")
    @Column(name = "mobile_number_elderly")
    private String mobileNumber;

    @NotNull(message = "Le numéro de téléphone fixe ne peut pas être null")
    @Size(max = 20, message = "Le numéro de téléphone fixe ne peut pas dépasser 20 caractères")
    @Column(name = "landline_number_elderly")
    private String landlineNumber;

    @Nullable
    @Column(name = "date_of_birth_elderly")
    private LocalDate dateOfBirth;
    @Nullable
    @Column(name = "entry_date_elderly")
    private LocalDate entryDate;

    @Nullable
    @Size(max = 20, message = "Les commentaires ne peuvent pas dépasser 1000 caractères")
    @Column(name = "comments_elderly")
    private String comments;


    @Nullable
    @ManyToOne
    @JoinColumn(name = "idElderlyContact")
    private ElderlyContact elderlyContact;


    // GETTER & SETTER
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
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

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ElderlyContact getElderlyContact() {
        return elderlyContact;
    }

    public void setElderlyContact(ElderlyContact elderlyContact) {
        this.elderlyContact = elderlyContact;
    }
}