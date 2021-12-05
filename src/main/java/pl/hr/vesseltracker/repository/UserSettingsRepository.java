package pl.hr.vesseltracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.hr.vesseltracker.entity.UserSettings;

@Repository
public interface UserSettingsRepository extends JpaRepository<UserSettings, Long>
{
}
