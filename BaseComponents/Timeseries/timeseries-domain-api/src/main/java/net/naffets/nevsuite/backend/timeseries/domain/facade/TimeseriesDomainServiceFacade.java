package net.naffets.nevsuite.backend.timeseries.domain.facade;

import net.naffets.nevsuite.backend.framework.core.api.ServiceFacade;
import net.naffets.nevsuite.backend.framework.lang.annotation.Facade;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesDTO;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesHeadDTO;

import javax.ejb.Remote;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author br4sk4
 * created on 17.04.2016
 */
@Remote
@Facade(module = "timeseries-domain-ejb-0.11", implementation = "TimeseriesDomainServiceImpl")
public interface TimeseriesDomainServiceFacade extends ServiceFacade {

    TimeseriesHeadDTO findTimeseriesHeadByUuid(String uuid);

    List<TimeseriesHeadDTO> findTimeseriesHeadByIdentifier(String identifier);

    List<TimeseriesHeadDTO> findAllTimeseriesHeads();

    String insertTimeseriesHead(TimeseriesHeadDTO dto);

    String updateTimeseriesHead(TimeseriesHeadDTO dto);

    String deleteTimeseriesHead(TimeseriesHeadDTO dto);

    TimeseriesDTO findTimeseriesValuesByInterval(String timeseriesIdentifier, ZonedDateTime timestampFrom, ZonedDateTime timestampTo);

    String insertTimeseriesValues(TimeseriesDTO dto);

    String deleteTimeseriesValues(String timeseriesIdentifier, ZonedDateTime timestampFrom, ZonedDateTime timestampTo);

}
