package net.naffets.nevsuite.backend.timeseries.domain.repository.persistent;

import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author br4sk4
 * created on 25.10.2016
 */
@Repository
public interface TimeseriesDocumentRepository extends JpaRepository<TimeseriesDocument, String> {

    List<TimeseriesDocument> findByTimeseriesIdentifier(String identifier);

}
