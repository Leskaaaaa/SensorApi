package ru.leska.sensorapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leska.sensorapi.model.Measurement;
import ru.leska.sensorapi.model.Sensor;
import ru.leska.sensorapi.repository.MeasurementRepository;
import ru.leska.sensorapi.repository.SensorRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorRepository sensorRepository) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    public int findRainyDaysCount() {
        return measurementRepository.countByRainingTrue();
    }

    @Transactional
    public void save(Measurement measurement) {
        Sensor sensor = sensorRepository.findByName(measurement.getSensor().getName())
                .orElseThrow(() -> new RuntimeException("Sensor not found"));

        measurement.setSensor(sensor);
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setCreatedAt(LocalDateTime.now());
    }
}
