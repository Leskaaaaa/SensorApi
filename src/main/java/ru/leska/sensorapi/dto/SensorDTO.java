package ru.leska.sensorapi.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

public class SensorDTO {

    @NotNull(message = "This field should not have been empty")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
