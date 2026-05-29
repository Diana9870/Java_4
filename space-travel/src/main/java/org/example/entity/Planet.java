package org.example.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "planet")
public class Planet {

    @Id
    @Column(length = 20)
    private String id;

    @Column(nullable = false, length = 500)
    private String name;

    @OneToMany(
            mappedBy = "fromPlanet",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Ticket> departures = new ArrayList<>();

    @OneToMany(
            mappedBy = "toPlanet",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Ticket> arrivals = new ArrayList<>();

    public Planet() {
    }

    public Planet(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ticket> getDepartures() {
        return departures;
    }

    public void setDepartures(List<Ticket> departures) {
        this.departures = departures;
    }

    public List<Ticket> getArrivals() {
        return arrivals;
    }

    public void setArrivals(List<Ticket> arrivals) {
        this.arrivals = arrivals;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}