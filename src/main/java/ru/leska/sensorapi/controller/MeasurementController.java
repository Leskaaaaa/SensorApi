package ru.leska.sensorapi.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.leska.sensorapi.dto.MeasurementDTO;
import ru.leska.sensorapi.dto.SensorDTO;
import ru.leska.sensorapi.model.Measurement;
import ru.leska.sensorapi.model.Sensor;
import ru.leska.sensorapi.services.MeasurementService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<MeasurementDTO> getAllMeasurements() {
        return measurementService.findAll()
                .stream()
                .map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDaysCount() {
        return measurementService.findRainyDaysCount();
    }

    @PostMapping("/add")
    public void createMeasurementSensor(@RequestBody MeasurementDTO measurementDTO) {
        SensorDTO sensorDTO = measurementDTO.getSensor();
        Measurement measurement = convertToMeasurement(measurementDTO);
        measurementService.save(measurement);
    }



    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
