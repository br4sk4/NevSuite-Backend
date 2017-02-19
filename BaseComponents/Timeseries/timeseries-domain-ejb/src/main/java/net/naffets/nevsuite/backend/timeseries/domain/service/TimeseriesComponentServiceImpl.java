package net.naffets.nevsuite.backend.timeseries.domain.service;

import net.naffets.nevsuite.backend.timeseries.domain.adapter.BackendComponentServiceAdapter;
import net.naffets.nevsuite.backend.timeseries.domain.facade.TimeseriesComponentServiceFacade;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author br4sk4
 * created on 25.12.2016
 */
@Stateless
@Remote(TimeseriesComponentServiceFacade.class)
@Local(TimeseriesComponentService.class)
public class TimeseriesComponentServiceImpl implements TimeseriesComponentService {

    @Inject
    private BackendComponentServiceAdapter commonServiceFacade;

    @Override
    public String respond() {
        return commonServiceFacade.respond();
    }

}
