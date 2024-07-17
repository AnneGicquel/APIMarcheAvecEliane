package com.api.APIMarcheAvecEliane.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
public class ElderlyContact {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @NotNull(message = "L'ID ne peut pas être null")
    @Column(name = "id_elderly_contact", updatable = false)
    private UUID id;

    @NotNull(message = "Le prénom ne peut pas être null")
    @Size(max = 25, message = "Le prénom ne peut pas dépasser 25 caractères")
    @Column(name = "first_name_elderly_contact")
    private String firstName;

    @NotNull(message = "Le nom de famille ne peut pas être null")
    @Size(max = 35, message = "Le nom de famille ne peut pas dépasser 35 caractères")
    @Column(name = "last_name_elderly_contact")
    private String lastName;

    @NotNull(message = "Le numéro de mobile ne peut pas être null")
    @Size(max = 20, message = "Le numéro de mobile ne peut pas dépasser 20 caractères")
    @Column(name = "mobile_number_elderly_contact")
    private String mobileNumber;

    @Nullable
    @Size(max = 20, message = "Le numéro de téléphone fixe ne peut pas dépasser 20 caractères")
    @Column(name = "landline_number_elderly_contact")
    private String landlineNumber;

    @Nullable
    @Size(max = 1000, message = "Les commentaires ne peuvent pas dépasser 1000 caractères")
    @Column(name = "comments_elderly_contact")
    private String comments;

    @NotNull(message = "Le lien de parenté ne peut pas être null")
    @Size(max = 20, message = "Le lien de parenté ne peut pas dépasser 100 caractères")
    @Column(name = "relationship_elderly_contact")
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
