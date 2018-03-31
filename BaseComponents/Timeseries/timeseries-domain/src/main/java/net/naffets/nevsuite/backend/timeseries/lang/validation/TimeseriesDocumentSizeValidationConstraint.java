package net.naffets.nevsuite.backend.timeseries.lang.validation;

import net.naffets.nevsuite.backend.timeseries.domain.basictype.TimeseriesPersistence;
import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;
import net.naffets.nevsuite.backend.timeseries.lang.exception.InvalidTimeseriesConfigException;
import net.naffets.nevsuite.backend.timeseries.lang.util.TimeseriesDocumentSizeHelper;

/**
 * @author br4sk4
 * created on 03.07.2015
 */
public class TimeseriesDocumentSizeValidationConstraint {

    public boolean validate(TimeseriesHead timeseriesHead) throws Exception {

        if (TimeseriesDocumentSizeHelper.getRasterblockungValueCount(timeseriesHead.getRasterType(), timeseriesHead.getBlockSize()) < 1) {
            throw new InvalidTimeseriesConfigException(timeseriesHead);
        }

        if (TimeseriesDocumentSizeHelper.getRasterblockungValueCount(timeseriesHead.getRasterType(), timeseriesHead.getBlockSize()) > 10000
                && TimeseriesPersistence.PERSISTENT.equals(timeseriesHead.getPersistence())) {
            throw new InvalidTimeseriesConfigException(timeseriesHead);
        }

        return true;
    }

}
