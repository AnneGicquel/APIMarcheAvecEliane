package com.api.APIMarcheAvecEliane.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
public class ElderlyContact {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_elderly_contact", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "first_name_elderly_contact", nullable = false, length = 25)
    private String firstName;

    @Column(name = "last_name_elderly_contact",nullable = false, length = 35)
    private String lastName;

    @Column(name = "mobile_number_elderly_contact",nullable = false, length = 20)
    private String mobileNumber;

    @Column(name = "landline_number_elderly_contact",nullable = true, length = 20)
    private String landlineNumber;

    @Column(name = "comments_elderly_contact", nullable = true, length = 1000)
    private String comments;

    @Column(name = "relationship_elderly_contact", nullable = false, length = 100)
    private String relationship;

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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
