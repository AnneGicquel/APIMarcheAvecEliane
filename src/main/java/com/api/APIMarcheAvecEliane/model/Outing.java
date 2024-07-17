package com.api.APIMarcheAvecEliane.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.UUID;

@Table (name ="Outing")
@Entity
public class Outing {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @NotNull(message = "L'ID ne peut pas être null")
    @Column(name = "id_outing", updatable = false)
    private UUID id;

    @NotNull(message = "Les dates de sortie ne peuvent pas être nulles")
    @Column(name = "outing_dates")
    private Timestamp[] outingDates;

    @ManyToOne
    @NotNull(message = "L'ID Elderly ne peut pas être null")
    @JoinColumn(name = "idElderly")
    private Elderly elderly;

    @ManyToOne
    @JoinColumn(name = "idVolunteer", nullable = true)
    private Volunteer volunteer;

    @ManyToOne
    @NotNull(message = "L ID Coordinator ne peut pas être null")
    @JoinColumn(name = "idCoordinator")
    private Coordinator coordinator;

    // GUETTERS & SETTERS

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Timestamp[] getOutingDates() {
        return outingDates;
    }

    public void setOutingDates(Timestamp[] outingDates) {
        this.outingDates = outingDates;
    }

    public Elderly getElderly() {
        return elderly;
    }

    public void setElderly(Elderly elderly) {
        this.elderly = elderly;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public Coordinator getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Coordinator coordinator) {
        this.coordinator = coordinator;
    }
}
