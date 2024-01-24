package rest.project.app.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "sensor")
public class Sensor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
