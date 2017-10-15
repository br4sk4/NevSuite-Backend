package net.naffets.nevsuite.backend.timeseries.domain.repository.persistent;

import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User:    Braska<br />
 * Date:    10.11.14<br />
 * <br />
 * <br />
 */
@Repository
public interface TimeseriesHeadRepository extends JpaRepository<TimeseriesHead, String> {
}
