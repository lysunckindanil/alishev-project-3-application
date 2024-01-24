package rest.project.app.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rest.project.app.models.Measurement;
import rest.project.app.models.MeasurementDTO;
import rest.project.app.repositories.MeasurementRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;
    private final ModelMapper modelMapper;

    @Transactional
    public void save(Measurement measurement) {
        measurementRepository.save(measurement);
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public Measurement toMeasurement(MeasurementDTO measurementDTO) {
        Measurement measurement = modelMapper.map(measurementDTO, Measurement.class);
        measurement.setSensor(sensorService.findByName(measurementDTO.getSensor().getName()).get());
        return measurement;
    }

    public MeasurementDTO toMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
