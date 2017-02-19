package net.naffets.nevsuite.backend.timeseries.domain.finder;

import net.naffets.nevsuite.backend.framework.core.api.Finder;
import net.naffets.nevsuite.backend.timeseries.domain.dto.RasterblockDTO;
import net.naffets.nevsuite.backend.timeseries.domain.entity.Rasterblock;
import net.naffets.nevsuite.backend.timeseries.domain.valueobject.Interval;

import java.util.List;

/**
 * @author br4sk4
 * created on 25.10.2016
 */
public interface RasterblockFinder extends Finder<Rasterblock<RasterblockDTO>> {
    List<Rasterblock<RasterblockDTO>> byIdentifier(String timeseriesIdentifier);

    List<Rasterblock<RasterblockDTO>> byInterval(String timeseriesIdentifier, Interval interval);
}
