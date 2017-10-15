package net.naffets.nevsuite.backend.timeseries.lang.persistance.unit;

import net.naffets.nevsuite.backend.timeseries.lang.exception.PeriodNotSupportedException;

import java.time.Duration;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

/**
 * @author br4sk4
 * created on 25.03.2016
 */
public enum PersistenceBlocksize {

    MINUTE,
    HOUR,
    DAY,
    MONTH,
    YEAR;

    public static PersistenceBlocksize ofTemporalAmount(TemporalAmount temporalAmount) throws PeriodNotSupportedException {
        switch (Long.valueOf(temporalAmount.get(ChronoUnit.SECONDS)).intValue()) {
            case 60:
                return MINUTE;
            case 3600:
                return HOUR;
            case 86400:
                return DAY;
            default:
                throw new PeriodNotSupportedException("Period of " + temporalAmount.get(ChronoUnit.SECONDS) + " seconds is not supported.");
        }
    }

    public static PersistenceBlocksize ofDuration(Duration duration) throws PeriodNotSupportedException {
        switch (Long.valueOf(duration.getSeconds()).intValue()) {
            case 60:
                return MINUTE;
            case 3600:
                return HOUR;
            case 86400:
                return DAY;
            default:
                throw new PeriodNotSupportedException("Period of " + duration.getSeconds() + " seconds is not supported.");
        }
    }

    public static PersistenceBlocksize ofPeriod(Period period) throws PeriodNotSupportedException {

        if (period == null) throw new PeriodNotSupportedException("Null-Period is not supported.");
        if (period.isZero() || period.isNegative())
            throw new PeriodNotSupportedException("Period of " + period.toString() + " is not supported.");

        if (period.getDays() + period.getMonths() + period.getYears() <= 1) {
            if (period.getDays() == 1) return DAY;
            else if (period.getMonths() == 1) return MONTH;
            else if (period.getYears() == 1) return YEAR;
            else throw new PeriodNotSupportedException("Period of " + period.toString() + " is not supported.");
        } else throw new PeriodNotSupportedException("Period of " + period.toString() + " is not supported.");

    }

    public TemporalAmount toTemporalAmount() {
        switch (this) {
            case MINUTE:
                return Duration.ofMinutes(1);
            case HOUR:
                return Duration.ofHours(1);
            case DAY:
                return Duration.ofDays(1);
            case MONTH:
                return Period.ofMonths(1);
            case YEAR:
                return Period.ofMonths(12);
            default:
                return null;
        }
    }

}
