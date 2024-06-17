package com.api.APIMarcheAvecEliane.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
public class Volunteer {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_volunteer", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "first_name_volunteer", nullable = false, length = 25)
    private String firstName;

    @Column(name = "last_name_volunteer", nullable = false, length = 35)
    private String lastName;

    @Column(name = "address_street_volunteer", nullable = false, length = 100)
    private String addressStreet;

    @Column(name = "address_city_volunteer", nullable = false, length = 50)
    private String addressCity;

    @Column(name = "zip_code_volunteer", nullable = false, length = 10)
    private String zipCode;

    @Column(name = "mobile_number_volunteer", nullable = false, length = 20)
    private String mobileNumber;

    @Column(name = "landline_number_volunteer", nullable = true,  length = 20)
    private String landlineNumber;

    @Column(name = "email_volunteer", nullable = false, length = 50)
    private String emailVolunteer;

    @Column(name = "password_volunteer", nullable = false, length = 64)
    private String password;

    @Column(name = "date_of_birth_volunteer", nullable = true)
    private Date dateOfBirth;
    @Column(name = "entry_date_volunteer", nullable = false)
    private Date entryDate;

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
        return emailVolunteer;
    }

    public void setEmailVolunteer(String emailVolunteer) {
        this.emailVolunteer = emailVolunteer;
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
