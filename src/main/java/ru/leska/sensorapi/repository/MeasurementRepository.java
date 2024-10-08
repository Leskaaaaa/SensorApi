package ru.leska.sensorapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leska.sensorapi.model.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    int countByRainingTrue();
}
