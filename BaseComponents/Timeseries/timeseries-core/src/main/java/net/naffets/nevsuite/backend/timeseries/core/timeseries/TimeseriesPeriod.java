package net.naffets.nevsuite.backend.timeseries.core.timeseries;

import net.naffets.nevsuite.backend.timeseries.lang.exception.PeriodNotSupportedException;

import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;
import java.time.Duration;
import java.time.Instant;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

/**
 * @author br4sk4
 * created on 25.03.2016
 */
public enum TimeseriesPeriod {

    SEC1,
    SEC15,
    SEC30,
    MIN1,
    MIN15,
    MIN30,
    HOUR1,
    HOUR6,
    HOUR12,
    DAY1,
    MONTH1,
    MONTH3,
    MONTH6,
    YEAR1;

    public static TimeseriesPeriod ofDuration(Duration duration) throws PeriodNotSupportedException {
        if (duration == null) throw new PeriodNotSupportedException("Null-Period is not supported.");

        switch (Long.valueOf(duration.getSeconds()).intValue()) {
            case 1:
                return SEC1;
            case 15:
                return SEC15;
            case 30:
                return SEC30;
            case 60:
                return MIN1;
            case 900:
                return MIN15;
            case 1800:
                return MIN30;
            case 3600:
                return HOUR1;
            case 21600:
                return HOUR6;
            case 43200:
                return HOUR12;
            case 86400:
                return DAY1;
            default:
                throw new PeriodNotSupportedException("Period of " + duration.getSeconds() + " seconds is not supported.");
        }
    }

    public static TimeseriesPeriod ofPeriod(Period period) throws PeriodNotSupportedException {

        if (period == null) throw new PeriodNotSupportedException("Null-Period is not supported.");
        if (period.isZero() || period.isNegative())
            throw new PeriodNotSupportedException("Period of " + period.toString() + " is not supported.");

        if (period.getDays() + period.getMonths() + period.getYears() <= 1) {
            if (period.getDays() == 1) return DAY1;
            else if (period.getMonths() == 1) return MONTH1;
            else if (period.getYears() == 1) return YEAR1;
            else throw new PeriodNotSupportedException("Period of " + period.toString() + " is not supported.");
        } else if (period.getMonths() == 3) return MONTH3;
        else if (period.getMonths() == 6) return MONTH6;
        else if (period.getMonths() == 12) return YEAR1;
        else throw new PeriodNotSupportedException("Period of " + period.toString() + " is not supported.");
    }

    public static TimeseriesPeriod ofSeconds(int seconds) throws PeriodNotSupportedException {
        switch (seconds) {
            case 1:
                return SEC1;
            case 15:
                return SEC15;
            case 30:
                return SEC30;
            case 60:
                return MIN1;
            case 900:
                return MIN15;
            case 1800:
                return MIN30;
            case 3600:
                return HOUR1;
            case 21600:
                return HOUR6;
            case 43200:
                return HOUR12;
            case 86400:
                return DAY1;
            default:
                throw new PeriodNotSupportedException("Period of " + seconds + " seconds is not supported.");
        }
    }

    public static TimeseriesPeriod ofMinutes(int minutes) throws PeriodNotSupportedException {
        switch (minutes) {
            case 1:
                return MIN1;
            case 15:
                return MIN15;
            case 30:
                return MIN30;
            case 60:
                return HOUR1;
            case 360:
                return HOUR6;
            case 720:
                return HOUR12;
            case 1440:
                return DAY1;
            default:
                throw new PeriodNotSupportedException("Period of " + minutes + " minutes is not supported.");
        }
    }

    public static TimeseriesPeriod ofHours(int hours) throws PeriodNotSupportedException {
        switch (hours) {
            case 1:
                return HOUR1;
            case 6:
                return HOUR6;
            case 12:
                return HOUR12;
            case 24:
                return DAY1;
            default:
                throw new PeriodNotSupportedException("Period of " + hours + " hours is not supported.");
        }
    }

    public static TimeseriesPeriod ofDays(int days) throws PeriodNotSupportedException {
        if (days == 1) return DAY1;
        else throw new PeriodNotSupportedException("Period of " + days + " days is not supported.");
    }

    public static TimeseriesPeriod ofMonths(int months) throws PeriodNotSupportedException {
        switch (months) {
            case 1:
                return MONTH1;
            case 3:
                return MONTH3;
            case 6:
                return MONTH6;
            case 12:
                return YEAR1;
            default:
                throw new PeriodNotSupportedException("Period of " + months + " months is not supported.");
        }
    }

    public static TimeseriesPeriod ofYears(int years) throws PeriodNotSupportedException {
        if (years == 1) return YEAR1;
        else throw new PeriodNotSupportedException("Period of " + years + " years is not supported.");
    }

    public TemporalAmount toTemporalAmount() {
        switch (this) {
            case SEC1:
                return Duration.ofSeconds(1);
            case SEC15:
                return Duration.ofSeconds(15);
            case SEC30:
                return Duration.ofSeconds(30);
            case MIN1:
                return Duration.ofMinutes(1);
            case MIN15:
                return Duration.ofMinutes(15);
            case MIN30:
                return Duration.ofMinutes(30);
            case HOUR1:
                return Duration.ofHours(1);
            case HOUR6:
                return Duration.ofHours(6);
            case HOUR12:
                return Duration.ofHours(12);
            case DAY1:
                return Period.ofDays(1);
            case MONTH1:
                return Period.ofMonths(1);
            case MONTH3:
                return Period.ofMonths(3);
            case MONTH6:
                return Period.ofMonths(6);
            case YEAR1:
                return Period.ofYears(1);
            default:
                return Duration.ZERO;
        }
    }

    public Long toSeconds() throws IllegalArgumentException {
        if (this.compareTo(HOUR1) <= 0) return this.toTemporalAmount().get(ChronoUnit.SECONDS);
        else
            throw new IllegalArgumentException("Period greater than \"1 hour\" needs a reference to the timeline to get the amount of seconds.");
    }

    public Long toSeconds(Instant timestamp) throws IllegalArgumentException {
        if (this.compareTo(HOUR1) > 0)
            return ChronoUnit.SECONDS.between(timestamp, timestamp.plus(this.toTemporalAmount()));
        else return toSeconds();
    }

    public Long toSeconds(ZonedDateTime timestamp) throws IllegalArgumentException {
        Long seconds = ChronoUnit.SECONDS.between(timestamp.toInstant(), timestamp.plus(this.toTemporalAmount()).toInstant());
        Long hoursOfDay = ChronoUnit.HOURS.between(timestamp.toInstant(), timestamp.plus(DAY1.toTemporalAmount()).toInstant());
        if (this.compareTo(HOUR12) > 0) return seconds;
        else if (this.compareTo(HOUR1) > 0) {
            if (hoursOfDay == 23) return seconds - 3600L;
            else if (hoursOfDay == 25) return seconds + 3600L;
            else return seconds;
        } else return toSeconds();
    }

    public Double getTimeintegralFactor(Unit<?> timeunit) throws IllegalArgumentException {
        if (this.compareTo(HOUR1) <= 0) return getTimeintegralFactor(timeunit, (Instant) null);
        else
            throw new IllegalArgumentException("Period greater than \"1 hour\" needs a reference to the timeline to get the amount of seconds.");
    }

    public Double getTimeintegralFactor(Unit<?> timeunit,
                                        ZonedDateTime timestamp) throws IllegalArgumentException {
        return getTimeintegralFactor(timeunit, this.toSeconds(timestamp).doubleValue());
    }

    public Double getTimeintegralFactor(Unit<?> timeunit,
                                        Instant timestamp) throws IllegalArgumentException {
        return getTimeintegralFactor(timeunit, this.toSeconds(timestamp).doubleValue());
    }

    private Double getTimeintegralFactor(Unit<?> timeunit,
                                         Double seconds) throws IllegalArgumentException {
        Double value = 1.0;
        Double hours = 1.0;

        if (this.compareTo(HOUR1) > 0) hours = seconds / 3600d;

        if (timeunit.equals(NonSI.HOUR)) {
            switch (this.toString()) {
                case "SEC1":
                    value = 3600d;
                    break;
                case "SEC15":
                    value = 900d;
                    break;
                case "SEC30":
                    value = 450d;
                    break;
                case "MIN1":
                    value = 60d;
                    break;
                case "MIN15":
                    value = 4d;
                    break;
                case "MIN30":
                    value = 2d;
                    break;
                case "HOUR1":
                    value = 1d;
                    break;
                case "HOUR6":
                    value = 1d / hours;
                    break;
                case "HOUR12":
                    value = 1d / hours;
                    break;
                case "DAY1":
                    value = 1d / hours;
                    break;
                case "MONTH1":
                    value = 1d / hours;
                    break;
                case "YEAR1":
                    value = 1d / hours;
                    break;
                default:
                    value = 1d;
            }
        } else if (timeunit.equals(SI.SECOND)) {
            switch (this.toString()) {
                case "SEC1":
                    value = 1d;
                    break;
                case "SEC15":
                    value = 1d / 15d;
                    break;
                case "SEC30":
                    value = 1d / 30d;
                    break;
                case "MIN1":
                    value = 1d / 60d;
                    break;
                case "MIN15":
                    value = 1d / 900d;
                    break;
                case "MIN30":
                    value = 1d / 1800d;
                    break;
                case "HOUR1":
                    value = 1d / 3600d;
                    break;
                case "HOUR6":
                    value = 1d / seconds;
                    break;
                case "HOUR12":
                    value = 1d / seconds;
                    break;
                case "DAY1":
                    value = 1d / seconds;
                    break;
                case "MONTH1":
                    value = 1d / seconds;
                    break;
                case "YEAR1":
                    value = 1d / seconds;
                    break;
                default:
                    value = 1d;
            }
        }

        return value;
    }

}
