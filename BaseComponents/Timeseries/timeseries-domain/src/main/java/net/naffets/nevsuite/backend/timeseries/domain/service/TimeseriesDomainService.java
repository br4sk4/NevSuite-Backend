package net.naffets.nevsuite.backend.timeseries.domain.service;

import net.naffets.nevsuite.backend.timeseries.domain.basictype.*;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesHeadDTO;
import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;
import net.naffets.nevsuite.backend.timeseries.domain.repository.document.TimeseriesDocumentMongoRepository;
import net.naffets.nevsuite.backend.timeseries.domain.repository.persistent.TimeseriesDocumentRepository;
import net.naffets.nevsuite.backend.timeseries.domain.repository.persistent.TimeseriesHeadRepository;
import net.naffets.nevsuite.backend.timeseries.domain.repository.temporary.TimeseriesDocumentInMemoryRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * @author br4sk4 / created on 22.10.2017
 */
@Service
public class TimeseriesDomainService {

    private TimeseriesHeadRepository timeseriesHeadRepository;
    private TimeseriesDocumentRepository timeseriesDocumentRepository;
    private TimeseriesDocumentMongoRepository timeseriesDocumentMongoRepository;
    private TimeseriesDocumentInMemoryRepository timeseriesDocumentInMemoryRepository;

    @Inject
    public TimeseriesDomainService(TimeseriesHeadRepository timeseriesHeadRepository, TimeseriesDocumentRepository timeseriesDocumentRepository, TimeseriesDocumentMongoRepository timeseriesDocumentMongoRepository, TimeseriesDocumentInMemoryRepository timeseriesDocumentInMemoryRepository) {
        this.timeseriesHeadRepository = timeseriesHeadRepository;
        this.timeseriesDocumentRepository = timeseriesDocumentRepository;
        this.timeseriesDocumentMongoRepository = timeseriesDocumentMongoRepository;
        this.timeseriesDocumentInMemoryRepository = timeseriesDocumentInMemoryRepository;
    }

    public List<TimeseriesHead> findAllTimeseriesHeads() {
        return timeseriesHeadRepository.findAll();
    }

    public TimeseriesHead findTimeseriesHeadByPrimaryKey(String uuid) {
        return timeseriesHeadRepository.getOne(uuid);
    }

    public TimeseriesHead saveTimeseriesHead(TimeseriesHeadDTO dto) {
        TimeseriesHead timeseriesHead;
        if ( Optional.ofNullable(dto.primaryKey).isPresent() ) {
            final Optional<TimeseriesHead> persistentTimeseriesHead = Optional.of(this.timeseriesHeadRepository.getOne(dto.primaryKey));
            timeseriesHead = persistentTimeseriesHead.orElseGet(TimeseriesHead.builder()::build);
        } else {
            timeseriesHead = TimeseriesHead.builder().build();
        }

        timeseriesHead.setIdentifier(dto.identifier);
        timeseriesHead.setType(TimeseriesType.valueOf(dto.type));
        timeseriesHead.setDerivationType(TimeseriesDerivationType.valueOf(dto.derivationType));
        timeseriesHead.setPersistence(TimeseriesPersistence.valueOf(dto.persistence));
        timeseriesHead.setPeriodicity(TimeseriesPeriodicity.valueOf(dto.periodicity));
        timeseriesHead.setBlockSize(TimeseriesBlocksize.valueOf(dto.blockSize));
        timeseriesHead.setRasterType(TimeseriesRastertype.valueOf(dto.rasterType));

        return this.timeseriesHeadRepository.save(timeseriesHead);
    }

}
