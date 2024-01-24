package rest.project.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MeasurementDTO {
    @NotNull(message = "Value should exist")
    @Max(value = 100, message = "Value should be less than 100")
    @Min(value = -100, message = "value should be more than -100")
    private Integer value;

    @NotNull(message = "Raining value should exist")
    private Boolean raining;

    @NotNull(message = "Sensor value should exist")
    private SensorDTO sensor;
}
