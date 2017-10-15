package net.naffets.nevsuite.backend.timeseries.domain.service;

import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;
import net.naffets.nevsuite.backend.timeseries.domain.repository.persistent.TimeseriesHeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author br4sk4 / created on 15.10.2017
 */
@Service
public class TimeseriesHeadService {

    @Autowired
    private TimeseriesHeadRepository repository;

    public List<TimeseriesHead> findAll() {
        return repository.findAll();
    }

    public TimeseriesHead findByPrimaryKey(String uuid) {
        return repository.findOne(uuid);
    }

}
