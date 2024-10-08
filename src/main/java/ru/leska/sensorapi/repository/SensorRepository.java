package ru.leska.sensorapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leska.sensorapi.model.Sensor;

import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> findByName(String name);
}
