package net.naffets.nevsuite.backend.timeseries.domain.repository.document;

import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author br4sk4 / created on 21.10.2017
 */
@Repository
public interface TimeseriesDocumentMongoRepository extends MongoRepository<TimeseriesDocument, String> {
}
