package rest.project.app.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rest.project.app.models.Sensor;
import rest.project.app.models.SensorDTO;
import rest.project.app.repositories.SensorRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;

    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public Optional<Sensor> findById(Long id) {
        return sensorRepository.findById(id);
    }

    public Optional<Sensor> findByName(String name) {
        return sensorRepository.findByName(name);
    }


    public Sensor toSensor(SensorDTO sensor) {
        return modelMapper.map(sensor, Sensor.class);
    }

    public SensorDTO toSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

}
