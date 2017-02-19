package net.naffets.nevsuite.backend.timeseries.lang.validation;

import net.naffets.nevsuite.backend.framework.core.api.ValidationConstraint;
import net.naffets.nevsuite.backend.timeseries.domain.basictype.TimeseriesDerivationType;
import net.naffets.nevsuite.backend.timeseries.domain.basictype.TimeseriesType;
import net.naffets.nevsuite.backend.timeseries.domain.entity.TimeseriesHead;
import net.naffets.nevsuite.backend.timeseries.lang.exception.InvalidTimeseriesConfigException;

/**
 * @author br4sk4
 * created on 03.07.2015
 */
public class TimeseriesTypeValidationConstraint implements ValidationConstraint<TimeseriesHead> {

    public boolean validate(TimeseriesHead object) throws InvalidTimeseriesConfigException {

        if ((TimeseriesType.SOURCE.equals(object.getType()) && !TimeseriesDerivationType.NONE.equals(object.getDerivationType()))
                || (TimeseriesType.DERIVATION.equals(object.getType()) && TimeseriesDerivationType.NONE.equals(object.getDerivationType())))
            throw new InvalidTimeseriesConfigException(object);

        return true;

    }
}
