package rest.project.app.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import rest.project.app.models.MeasurementDTO;
import rest.project.app.services.SensorService;

@Component
@RequiredArgsConstructor
public class MeasurementValidator implements Validator {
    private final SensorService sensorService;

    @Override
    public boolean supports(Class<?> aClass) {
        return MeasurementDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurement = (MeasurementDTO) target;
        if (sensorService.findByName(measurement.getSensor().getName()).isEmpty()) {
            errors.rejectValue("sensor", "", "Sensor doesn't exist!");
        }
    }
}
