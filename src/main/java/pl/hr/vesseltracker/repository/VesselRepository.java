package pl.hr.vesseltracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.hr.vesseltracker.entity.Position;
import pl.hr.vesseltracker.entity.Vessel;

@Repository
public interface VesselRepository extends JpaRepository<Vessel, Integer> {
}
