package rest.project.app.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import rest.project.app.models.SensorDTO;
import rest.project.app.services.SensorService;

@Component
@RequiredArgsConstructor
public class SensorValidator implements Validator {
    public final SensorService sensorService;

    @Override
    public boolean supports(Class<?> aClass) {
        return SensorDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensor = (SensorDTO) target;
        if (sensorService.findByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name", "", "Sensor with this name already exists!");
        } else if (sensor.getName().length() < 3 || sensor.getName().length() > 30) {
            errors.rejectValue("name", "", "Sensor's name should be between 3 and 30 characters!");
        }
    }
}
