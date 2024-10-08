package ru.leska.sensorapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Field name should not been empty")
    private String name;

    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurementsSensor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Measurement> getMeasurementsSensor() {
        return measurementsSensor;
    }

    public void setMeasurementsSensor(List<Measurement> measurementsSensor) {
        this.measurementsSensor = measurementsSensor;
    }
}
