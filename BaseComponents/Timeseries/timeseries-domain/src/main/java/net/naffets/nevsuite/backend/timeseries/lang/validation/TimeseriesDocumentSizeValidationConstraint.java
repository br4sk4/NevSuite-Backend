package net.naffets.nevsuite.backend.timeseries.lang.validation;

import net.naffets.nevsuite.backend.framework.core.api.ValidationConstraint;
import net.naffets.nevsuite.backend.timeseries.domain.basictype.TimeseriesPersistence;
import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;
import net.naffets.nevsuite.backend.timeseries.lang.exception.InvalidTimeseriesConfigException;
import net.naffets.nevsuite.backend.timeseries.lang.util.TimeseriesDocumentSizeHelper;

/**
 * @author br4sk4
 * created on 03.07.2015
 */
public class TimeseriesDocumentSizeValidationConstraint implements ValidationConstraint<TimeseriesHead> {

    public boolean validate(TimeseriesHead object) throws Exception {

        if (TimeseriesDocumentSizeHelper.getRasterblockungValueCount(object.getRastertype(), object.getBlocksize()) < 1) {
            throw new InvalidTimeseriesConfigException(object);
        }

        if (TimeseriesDocumentSizeHelper.getRasterblockungValueCount(object.getRastertype(), object.getBlocksize()) > 10000
                && TimeseriesPersistence.PERSISTENT.equals(object.getPersistence())) {
            throw new InvalidTimeseriesConfigException(object);
        }

        return true;
    }
}
