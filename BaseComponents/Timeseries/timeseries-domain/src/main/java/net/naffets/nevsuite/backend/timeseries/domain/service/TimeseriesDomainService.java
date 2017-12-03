package net.naffets.nevsuite.backend.timeseries.domain.service;

import net.naffets.nevsuite.backend.timeseries.domain.basictype.*;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesHeadDTO;
import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;
import net.naffets.nevsuite.backend.timeseries.domain.repository.document.TimeseriesDocumentMongoRepository;
import net.naffets.nevsuite.backend.timeseries.domain.repository.persistent.TimeseriesDocumentRepository;
import net.naffets.nevsuite.backend.timeseries.domain.repository.persistent.TimeseriesHeadRepository;
import net.naffets.nevsuite.backend.timeseries.domain.repository.temporary.TimeseriesDocumentInMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author br4sk4 / created on 22.10.2017
 */
@Service
public class TimeseriesDomainService {

    @Autowired
    private TimeseriesHeadRepository timeseriesHeadRepository;

    @Autowired
    private TimeseriesDocumentRepository timeseriesDocumentRepository;

    @Autowired
    private TimeseriesDocumentMongoRepository timeseriesDocumentMongoRepository;

    @Autowired
    private TimeseriesDocumentInMemoryRepository timeseriesDocumentInMemoryRepository;

    public List<TimeseriesHead> findAllTimeseriesHeads() {
        return timeseriesHeadRepository.findAll();
    }

    public TimeseriesHead findTimeseriesHeadByPrimaryKey(String uuid) {
        return timeseriesHeadRepository.findOne(uuid);
    }

    public TimeseriesHead saveTimeseriesHead(TimeseriesHeadDTO dto) {
        TimeseriesHead timeseriesHead;
        if ( Optional.ofNullable(dto.getPrimaryKey()).isPresent() ) {
            final Optional<TimeseriesHead> persistentTimeseriesHead = Optional.ofNullable(
                    this.timeseriesHeadRepository.findOne(dto.getPrimaryKey())
            );
            timeseriesHead = persistentTimeseriesHead.orElseGet(TimeseriesHead::new);
        } else {
            timeseriesHead = new TimeseriesHead();
        }

        timeseriesHead.setIdentifier(dto.getIdentifier());
        timeseriesHead.setType(TimeseriesType.valueOf(dto.getType()));
        timeseriesHead.setDerivationType(TimeseriesDerivationType.valueOf(dto.getDerivationType()));
        timeseriesHead.setPersistence(TimeseriesPersistence.valueOf(dto.getPersistence()));
        timeseriesHead.setPeriodicity(TimeseriesPeriodicity.valueOf(dto.getPeriodicity()));
        timeseriesHead.setBlockSize(TimeseriesBlocksize.valueOf(dto.getBlockSize()));
        timeseriesHead.setRasterType(TimeseriesRastertype.valueOf(dto.getRasterType()));

        return this.timeseriesHeadRepository.save(timeseriesHead);
    }

}
