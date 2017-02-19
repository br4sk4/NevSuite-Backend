package net.naffets.nevsuite.backend.timeseries.domain.finder;

import net.naffets.nevsuite.backend.framework.core.api.Finder;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesHeadDTO;
import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;

import java.util.List;

/**
 * @author br4sk4
 * created on 14.07.2016
 */
public interface TimeseriesHeadFinder extends Finder<TimeseriesHead<TimeseriesHeadDTO>> {

    List<TimeseriesHead<TimeseriesHeadDTO>> byIdentifier(String identifier);

}
