package com.api.APIMarcheAvecEliane.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Elderly {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_elderly", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "pseudo_elderly", nullable = false, length = 25)
    private String pseudo;

    @Column(name = "address_street_elderly", nullable = false, length = 100)
    private String addressStreet;

    @Column(name = "address_city_elderly", nullable = false, length = 50)
    private String addressCity;

    @Column(name = "ZIP_code_elderly", nullable = false, length = 10)
    private String zipCode;

    @Column(name = "address_details_elderly", nullable = true, length = 255)
    private String addressDetails;

    @Column(name = "mobile_number_elderly", nullable = true, length = 20)
    private String mobileNumber;

    @Column(name = "landline_number_elderly", nullable = false, length = 20)
    private String landlineNumber;
    @Column(name = "date_of_birth_elderly", nullable = true)
    private LocalDate dateOfBirth;
    @Column(name = "entry_date_elderly", nullable = true)
    private LocalDate entryDate;

    @Column(name = "comments_elderly", nullable = true, length = 1000)
    private String comments;

    @ManyToOne
    @JoinColumn(name = "idCoordinator", nullable = false)
    private Coordinator coordinator;

    @ManyToOne
    @JoinColumn(name = "idElderlyContact", nullable = true)
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

    public Coordinator getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Coordinator coordinator) {
        this.coordinator = coordinator;
    }

    public ElderlyContact getElderlyContact() {
        return elderlyContact;
    }

    public void setElderlyContact(ElderlyContact elderlyContact) {
        this.elderlyContact = elderlyContact;
    }
}