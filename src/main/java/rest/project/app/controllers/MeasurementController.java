package rest.project.app.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rest.project.app.models.Measurement;
import rest.project.app.models.MeasurementDTO;
import rest.project.app.services.MeasurementService;
import rest.project.app.util.InvalidMeasurementException;
import rest.project.app.util.MeasurementValidator;
import rest.project.app.util.ResponseMessage;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/measurements")
@RequiredArgsConstructor
public class MeasurementController {
    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;

    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> add(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        measurementValidator.validate(measurementDTO, bindingResult);
        if (bindingResult.hasErrors())
            throw new InvalidMeasurementException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());

        Measurement measurement = measurementService.toMeasurement(measurementDTO);
        measurement.setMeasured(new Date(System.currentTimeMillis()));
        measurementService.save(measurement);
        return new ResponseEntity<>(new ResponseMessage("Measurement added"), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Measurement>> getAllMeasurements() {
        return new ResponseEntity<>
                (measurementService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/rainyDaysCount")
    public Long getRainyDays() {
        return measurementService.findAll().stream().filter(Measurement::isRaining).count();
    }

    @ExceptionHandler
    private ResponseEntity<ResponseMessage> handleException(InvalidMeasurementException exception) {
        ResponseMessage response = new ResponseMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
