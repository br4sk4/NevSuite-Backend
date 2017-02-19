package net.naffets.nevsuite.backend.timeseries.domain.finder;

import com.mysema.query.jpa.impl.JPAQuery;
import net.naffets.nevsuite.backend.timeseries.domain.dto.RasterblockDTO;
import net.naffets.nevsuite.backend.timeseries.domain.entity.QRasterblockImpl;
import net.naffets.nevsuite.backend.timeseries.domain.entity.Rasterblock;
import net.naffets.nevsuite.backend.timeseries.domain.valueobject.Interval;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author br4sk4
 * created on 25.10.2016
 */
public class RasterblockFinderImpl implements RasterblockFinder {

    private JPAQuery query;

    public RasterblockFinderImpl(JPAQuery query) {
        this.query = query;
    }

    @Override
    public Rasterblock<RasterblockDTO> byUuid(String uuid) {
        QRasterblockImpl rasterblock = QRasterblockImpl.rasterblockImpl;

        return query
                .from(rasterblock)
                .where(rasterblock.uuid.eq(uuid))
                .uniqueResult(rasterblock);
    }

    @Override
    public List<Rasterblock<RasterblockDTO>> all() {
        QRasterblockImpl rasterblock = QRasterblockImpl.rasterblockImpl;

        return query
                .from(rasterblock)
                .orderBy(rasterblock.interval().timestampFrom.asc())
                .list(rasterblock)
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Rasterblock<RasterblockDTO>> byIdentifier(String timeseriesIdentifier) {
        QRasterblockImpl rasterblock = QRasterblockImpl.rasterblockImpl;

        return query
                .from(rasterblock)
                .where(rasterblock.timeseriesIdentifier.eq(timeseriesIdentifier))
                .orderBy(rasterblock.interval().timestampFrom.asc())
                .list(rasterblock)
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Rasterblock<RasterblockDTO>> byInterval(String timeseriesIdentifier, Interval interval) {
        QRasterblockImpl rasterblock = QRasterblockImpl.rasterblockImpl;

        return query
                .from(rasterblock)
                .where((rasterblock.timeseriesIdentifier.eq(timeseriesIdentifier))
                        .and(rasterblock.interval().timestampFrom.before(interval.getTimestampTo()))
                        .and(rasterblock.interval().timestampFrom.eq(interval.getTimestampFrom())
                                .or(rasterblock.interval().timestampFrom.after(interval.getTimestampFrom()))))
                .orderBy(rasterblock.interval().timestampFrom.asc())
                .list(rasterblock)
                .stream()
                .collect(Collectors.toList());
    }

}
