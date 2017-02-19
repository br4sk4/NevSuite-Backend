package net.naffets.nevsuite.test.unit.timeseries.domain;

import net.naffets.nevsuite.backend.framework.lang.annotation.PersistenceUnitMock;
import net.naffets.nevsuite.backend.timeseries.domain.basictype.*;
import net.naffets.nevsuite.backend.timeseries.domain.builder.TimeseriesBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.builder.TimeseriesHeadBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.dto.RasterblockDTO;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesHeadDTO;
import net.naffets.nevsuite.backend.timeseries.domain.entity.Rasterblock;
import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;
import net.naffets.nevsuite.backend.timeseries.domain.factory.RasterblockFactory;
import net.naffets.nevsuite.backend.timeseries.domain.factory.TimeseriesHeadFactory;
import net.naffets.nevsuite.backend.timeseries.domain.repository.RasterblockRepository;
import net.naffets.nevsuite.backend.timeseries.domain.valueobject.Interval;
import net.naffets.nevsuite.test.unit.timeseries.lang.persistence.RasterblockRepositoryMock;
import net.naffets.nevsuite.test.unit.timeseries.lang.persistence.TimeseriesHeadRepositoryMock;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * @author br4sk4
 * created on 21.10.2016
 */
@RunWith(CdiTestRunner.class)
public class TestRasterblockRepository {

    private static final Logger logger = LogManager.getLogger(RasterblockRepository.class.getName());
    private static TimeseriesHead<TimeseriesHeadDTO> headReference1;
    private static Rasterblock<RasterblockDTO> entityReference1;
    private static Rasterblock<RasterblockDTO> entityReference2;
    @Inject
    @PersistenceUnitMock
    private TimeseriesHeadRepositoryMock headRepository;
    @Inject
    private TimeseriesHeadFactory headFactory;
    @Inject
    @PersistenceUnitMock
    private RasterblockRepositoryMock rblRepository;
    @Inject
    private RasterblockFactory rblFactory;

    @Before
    public void setUp() {
        Random rand = new Random(Instant.now().toEpochMilli());

        headRepository.beginTransaction();
        headReference1 = TimeseriesHeadFactory.fromDTO(new TimeseriesHeadBuilder()
                .withUuid(UUID.randomUUID().toString())
                .withIdentifier("UT01")
                .withType(TimeseriesType.SOURCE.toString())
                .withDerivationType(TimeseriesDerivationType.NONE.toString())
                .withPersistence(TimeseriesPersistence.PERSISTENT.toString())
                .withPeriodicity(TimeseriesPeriodicity.PERIODIC.toString())
                .withBlockSize(TimeseriesBlocksize.DAY.toString())
                .withRasterType(TimeseriesRastertype.MIN15.toString())
                .build()
        );
        headRepository.insert(headReference1);
        headRepository.commitTransaction();

        rblRepository.beginTransaction();

        entityReference1 = rblFactory.createEmpty();
        entityReference1.setUuid(UUID.randomUUID().toString());
        entityReference1.setTimeseriesIdentifier(headReference1.getIdentifier());
        entityReference1.setInterval(new Interval(Instant.parse("2016-01-01T00:00:00Z"), null));
        rblRepository.insert(entityReference1);

        entityReference2 = rblFactory.createEmpty();
        entityReference2.setUuid(UUID.randomUUID().toString());
        entityReference2.setTimeseriesIdentifier(headReference1.getIdentifier());
        entityReference2.setInterval(new Interval(Instant.parse("2016-01-02T00:00:00Z"), null));
    }

    @After
    public void tearDown() {
        rblRepository.rollbackTransaction();
    }

    @Test
    public void testFindByUuid() {
        Rasterblock rbl = rblRepository.find().byUuid(entityReference1.getUuid());

        assertEquals(Long.valueOf(500L), rbl.getPrimaryKey());
        assertEquals(entityReference1.getTimeseriesIdentifier(), rbl.getTimeseriesIdentifier());
        assertEquals(entityReference1.getInterval().getInstantFrom(), rbl.getInterval().getInstantFrom());
    }

    @Test
    public void testInsert() {
        rblRepository.insert(entityReference2);

        List<Rasterblock<RasterblockDTO>> list = rblRepository.find().all();
        assertEquals(2, list.size());

        list.forEach(rbl -> {
            if (entityReference1.getUuid().equals(rbl.getUuid())) {
                assertEquals(Long.valueOf(500L), rbl.getPrimaryKey());
                assertEquals(entityReference1.getTimeseriesIdentifier(), rbl.getTimeseriesIdentifier());
                assertEquals(entityReference1.getInterval().getInstantFrom(), rbl.getInterval().getInstantFrom());
            } else if (entityReference2.getUuid().equals(rbl.getUuid())) {
                assertEquals(Long.valueOf(501L), rbl.getPrimaryKey());
                assertEquals(entityReference2.getTimeseriesIdentifier(), rbl.getTimeseriesIdentifier());
                assertEquals(entityReference2.getInterval().getInstantFrom(), rbl.getInterval().getInstantFrom());
            }
        });
    }

    @Test
    public void testMerge() {
        rblRepository.insert(entityReference1);

        List<Rasterblock<RasterblockDTO>> list = rblRepository.find().all();
        assertEquals(1, list.size());

        list.forEach(rbl -> {
            if (entityReference1.getUuid().equals(rbl.getUuid())) {
                assertEquals(Long.valueOf(500L), rbl.getPrimaryKey());
                assertEquals(entityReference1.getTimeseriesIdentifier(), rbl.getTimeseriesIdentifier());
                assertEquals(entityReference1.getInterval().getInstantFrom(), rbl.getInterval().getInstantFrom());
            }
        });
    }

    @Test
    public void testUpdate() {
        rblRepository.update(entityReference1);

        List<Rasterblock<RasterblockDTO>> list = rblRepository.find().all();
        assertEquals(1, list.size());

        list.forEach(rbl -> {
            if (entityReference1.getUuid().equals(rbl.getUuid())) {
                assertEquals(Long.valueOf(500L), rbl.getPrimaryKey());
                assertEquals(entityReference1.getTimeseriesIdentifier(), rbl.getTimeseriesIdentifier());
                assertEquals(entityReference1.getInterval().getInstantFrom(), rbl.getInterval().getInstantFrom());
            }
        });
    }

    @Test
    public void testDelete() {
        rblRepository.delete(entityReference1);

        List<Rasterblock<RasterblockDTO>> list = rblRepository.find().all();
        assertEquals(0, list.size());
    }

    @Test
    public void testDTO() {
        rblRepository.insert(entityReference2);

        List<Rasterblock<RasterblockDTO>> list = rblRepository.find().all();
        assertEquals(2, list.size());

        logger.info("\r\n" + new TimeseriesBuilder()
                .withTimeseriesHead(new TimeseriesHeadBuilder()
                        .withIdentifier("FOOBAR")
                        .build())
                .setPrettyPrintingEnabled(true)
                .toXml()
        );
    }

}
