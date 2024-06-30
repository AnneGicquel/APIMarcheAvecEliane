package com.api.APIMarcheAvecEliane.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
public class Outing {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_outing", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "outing_dates", nullable = false)
    private Timestamp[] outingDates;

    @ManyToOne
    @JoinColumn(name = "idElderly", nullable = false)
    private Elderly elderly;

    @ManyToOne
    @JoinColumn(name = "idVolunteer", nullable = true)
    private Volunteer volunteer;

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
}
