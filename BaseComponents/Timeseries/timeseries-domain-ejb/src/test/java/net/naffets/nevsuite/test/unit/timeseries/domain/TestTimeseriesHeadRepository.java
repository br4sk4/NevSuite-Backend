package net.naffets.nevsuite.test.unit.timeseries.domain;

import net.naffets.nevsuite.backend.framework.lang.annotation.PersistenceUnitMock;
import net.naffets.nevsuite.backend.timeseries.domain.basictype.*;
import net.naffets.nevsuite.backend.timeseries.domain.builder.TimeseriesHeadBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesHeadDTO;
import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;
import net.naffets.nevsuite.backend.timeseries.domain.factory.TimeseriesHeadFactory;
import net.naffets.nevsuite.backend.timeseries.domain.repository.TimeseriesHeadRepository;
import net.naffets.nevsuite.test.unit.timeseries.lang.persistence.TimeseriesHeadRepositoryMock;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * @author br4sk4
 * created on 12.09.2015
 */
@RunWith(CdiTestRunner.class)
public class TestTimeseriesHeadRepository {

    private static final Logger logger = LogManager.getLogger(TimeseriesHeadRepository.class.getName());
    private static TimeseriesHead<TimeseriesHeadDTO> entityReference1;
    private static TimeseriesHead<TimeseriesHeadDTO> entityReference2;
    @Inject
    @PersistenceUnitMock
    private TimeseriesHeadRepositoryMock repository;
    @Inject
    private TimeseriesHeadFactory factory;

    @Before
    public void setUp() {
        repository.beginTransaction();

        entityReference1 = TimeseriesHeadFactory.fromDTO(new TimeseriesHeadBuilder()
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
        repository.insert(entityReference1);

        entityReference2 = TimeseriesHeadFactory.fromDTO(new TimeseriesHeadBuilder()
                .withUuid(UUID.randomUUID().toString())
                .withIdentifier("UT02")
                .withType(TimeseriesType.SOURCE.toString())
                .withDerivationType(TimeseriesDerivationType.NONE.toString())
                .withPersistence(TimeseriesPersistence.PERSISTENT.toString())
                .withPeriodicity(TimeseriesPeriodicity.PERIODIC.toString())
                .withBlockSize(TimeseriesBlocksize.DAY.toString())
                .withRasterType(TimeseriesRastertype.MIN15.toString())
                .build()
        );
    }

    @After
    public void tearDown() {
        repository.rollbackTransaction();
    }

    @Test
    public void testFindByUuid() {
        TimeseriesHead timeseriesHead = repository.find().byUuid(entityReference1.getUuid());

        assertEquals(new Long(1L), timeseriesHead.getPrimaryKey());
        assertEquals(entityReference1.getUuid(), timeseriesHead.getUuid());

        assertEquals(entityReference1.getIdentifier(), timeseriesHead.getIdentifier());
        assertEquals(entityReference1.getType(), timeseriesHead.getType());
        assertEquals(entityReference1.getDerivationType(), timeseriesHead.getDerivationType());
        assertEquals(entityReference1.getPersistence(), timeseriesHead.getPersistence());
        assertEquals(entityReference1.getPeriodicity(), timeseriesHead.getPeriodicity());
        assertEquals(entityReference1.getBlocksize(), timeseriesHead.getBlocksize());
        assertEquals(entityReference1.getRastertype(), timeseriesHead.getRastertype());
    }

    @Test
    public void testInsert() {
        repository.insert(entityReference2);

        List<TimeseriesHead<TimeseriesHeadDTO>> list = repository.find().all();
        assertEquals(2, list.size());

        list.forEach(timeseriesHead -> {
            if (entityReference1.getUuid().equals(timeseriesHead.getUuid())) {
                assertEquals(new Long(1L), timeseriesHead.getPrimaryKey());

                assertEquals(entityReference1.getIdentifier(), timeseriesHead.getIdentifier());
                assertEquals(entityReference1.getType(), timeseriesHead.getType());
                assertEquals(entityReference1.getDerivationType(), timeseriesHead.getDerivationType());
                assertEquals(entityReference1.getPersistence(), timeseriesHead.getPersistence());
                assertEquals(entityReference1.getPeriodicity(), timeseriesHead.getPeriodicity());
                assertEquals(entityReference1.getBlocksize(), timeseriesHead.getBlocksize());
                assertEquals(entityReference1.getRastertype(), timeseriesHead.getRastertype());
            } else if (entityReference2.getUuid().equals(timeseriesHead.getUuid())) {
                assertEquals(new Long(2L), timeseriesHead.getPrimaryKey());

                assertEquals(entityReference2.getIdentifier(), timeseriesHead.getIdentifier());
                assertEquals(entityReference2.getType(), timeseriesHead.getType());
                assertEquals(entityReference2.getDerivationType(), timeseriesHead.getDerivationType());
                assertEquals(entityReference2.getPersistence(), timeseriesHead.getPersistence());
                assertEquals(entityReference2.getPeriodicity(), timeseriesHead.getPeriodicity());
                assertEquals(entityReference2.getBlocksize(), timeseriesHead.getBlocksize());
                assertEquals(entityReference2.getRastertype(), timeseriesHead.getRastertype());
            }
        });
    }

    @Test
    public void testMerge() {
        repository.insert(entityReference1);

        List<TimeseriesHead<TimeseriesHeadDTO>> list = repository.find().all();
        assertEquals(1, list.size());

        list.forEach(timeseriesHead -> {
            if (entityReference1.getUuid().equals(timeseriesHead.getUuid())) {
                assertEquals(new Long(1L), timeseriesHead.getPrimaryKey());

                assertEquals(entityReference1.getIdentifier(), timeseriesHead.getIdentifier());
                assertEquals(entityReference1.getType(), timeseriesHead.getType());
                assertEquals(entityReference1.getDerivationType(), timeseriesHead.getDerivationType());
                assertEquals(entityReference1.getPersistence(), timeseriesHead.getPersistence());
                assertEquals(entityReference1.getPeriodicity(), timeseriesHead.getPeriodicity());
                assertEquals(entityReference1.getBlocksize(), timeseriesHead.getBlocksize());
                assertEquals(entityReference1.getRastertype(), timeseriesHead.getRastertype());
            }
        });
    }

    @Test
    public void testUpdate() {
        repository.update(entityReference1);

        List<TimeseriesHead<TimeseriesHeadDTO>> list = repository.find().all();
        assertEquals(1, list.size());

        list.forEach(timeseriesHead -> {
            if (entityReference1.getUuid().equals(timeseriesHead.getUuid())) {
                assertEquals(new Long(1L), timeseriesHead.getPrimaryKey());

                assertEquals(entityReference1.getIdentifier(), timeseriesHead.getIdentifier());
                assertEquals(entityReference1.getType(), timeseriesHead.getType());
                assertEquals(entityReference1.getDerivationType(), timeseriesHead.getDerivationType());
                assertEquals(entityReference1.getPersistence(), timeseriesHead.getPersistence());
                assertEquals(entityReference1.getPeriodicity(), timeseriesHead.getPeriodicity());
                assertEquals(entityReference1.getBlocksize(), timeseriesHead.getBlocksize());
                assertEquals(entityReference1.getRastertype(), timeseriesHead.getRastertype());
            }
        });
    }

    @Test
    public void testDelete() {
        repository.delete(entityReference1);

        List<TimeseriesHead<TimeseriesHeadDTO>> list = repository.find().all();
        assertEquals(0, list.size());
    }

}
