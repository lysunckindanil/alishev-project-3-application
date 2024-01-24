package rest.project.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rest.project.app.models.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
}
