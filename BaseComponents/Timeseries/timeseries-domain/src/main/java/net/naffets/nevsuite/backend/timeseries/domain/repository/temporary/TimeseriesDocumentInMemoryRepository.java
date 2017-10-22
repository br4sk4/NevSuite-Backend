package net.naffets.nevsuite.backend.timeseries.domain.repository.temporary;

import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author br4sk4 / created on 22.10.2017
 */
@Repository
public interface TimeseriesDocumentInMemoryRepository extends JpaRepository<TimeseriesDocument, String> {
}
