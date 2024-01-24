package rest.project.app.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "value")
    private Integer value;

    @Column(name = "raining")
    private boolean raining;

    @Column(name = "measured")
    private Date measured;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;
}
