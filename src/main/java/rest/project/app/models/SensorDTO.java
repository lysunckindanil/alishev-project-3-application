package rest.project.app.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SensorDTO {
    @NotEmpty(message = "name should not be empty")
    private String name;
}