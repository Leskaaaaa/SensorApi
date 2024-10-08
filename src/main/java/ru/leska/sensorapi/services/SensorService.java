package ru.leska.sensorapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leska.sensorapi.model.Sensor;
import ru.leska.sensorapi.repository.SensorRepository;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Sensor sensor) {
        if (sensorRepository.findByName(sensor.getName()).isEmpty()) {
            sensorRepository.save(sensor);
        } else {
            throw new RuntimeException("Sensor with name " + sensor.getName() + " already exists");
        }
    }
}
