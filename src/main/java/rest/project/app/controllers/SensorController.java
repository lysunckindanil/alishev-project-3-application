package rest.project.app.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rest.project.app.models.SensorDTO;
import rest.project.app.services.SensorService;
import rest.project.app.util.InvalidSensorNameException;
import rest.project.app.util.ResponseMessage;
import rest.project.app.util.SensorValidator;

import java.util.Objects;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController {
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;

    @PostMapping("/registration")
    private ResponseEntity<?> register(@RequestBody @Valid SensorDTO sensor, BindingResult bindingResult) {
        sensorValidator.validate(sensor, bindingResult);
        if (bindingResult.hasErrors())
            throw new InvalidSensorNameException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());

        sensorService.save(sensorService.toSensor(sensor));
        return new ResponseEntity<>(new ResponseMessage("Sensor registered successfully"), HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ResponseMessage> handleException(InvalidSensorNameException exception) {
        ResponseMessage response = new ResponseMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
