package ru.leska.sensorapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.leska.sensorapi.dto.SensorDTO;
import ru.leska.sensorapi.exception.SensorErrorResponse;
import ru.leska.sensorapi.exception.SensorNotCreatedException;
import ru.leska.sensorapi.model.Sensor;
import ru.leska.sensorapi.services.SensorService;

import java.util.List;

@RestController
public class SensorController {
    private final SensorService sensorService;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/sensors/registration")
    public void createdSensor(@RequestBody @Valid SensorDTO sensorDTO,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorsMessage = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                errorsMessage.append(fieldError.getField())
                        .append(": ")
                        .append(fieldError.getDefaultMessage())
                        .append("; ");
            }

            throw new SensorNotCreatedException(errorsMessage.toString());
        }

        Sensor sensor = convertToSensor(sensorDTO);
        sensorService.save(sensor);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
