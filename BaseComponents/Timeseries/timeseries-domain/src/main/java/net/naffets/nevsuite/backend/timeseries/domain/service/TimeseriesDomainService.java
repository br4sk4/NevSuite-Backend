package net.naffets.nevsuite.backend.timeseries.domain.service;

import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;
import net.naffets.nevsuite.backend.timeseries.domain.repository.document.TimeseriesDocumentMongoRepository;
import net.naffets.nevsuite.backend.timeseries.domain.repository.persistent.TimeseriesDocumentRepository;
import net.naffets.nevsuite.backend.timeseries.domain.repository.persistent.TimeseriesHeadRepository;
import net.naffets.nevsuite.backend.timeseries.domain.repository.temporary.TimeseriesDocumentInMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}