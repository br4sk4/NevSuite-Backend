package net.naffets.nevsuite.backend.timeseries.core.timeseries;

import net.naffets.nevsuite.backend.framework.lang.util.MeasurementUnitSystem;
import net.naffets.nevsuite.backend.timeseries.core.valueplugin.CompactType;
import net.naffets.nevsuite.backend.timeseries.core.valueplugin.SpreadType;
import org.jscience.economics.money.Money;

import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Energy;
import javax.measure.quantity.Power;
import javax.measure.quantity.Volume;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

/**
 * @author br4sk4
 * created on 25.03.2016
 */
public enum TimeseriesUnit {

    NONE(MeasurementUnitSystem.NONE, Dimensionless.class),
    UNKNOWN(MeasurementUnitSystem.UNKNOWN, Dimensionless.class),

    // UnitDefinitions -> Volume
    CUBIC_METRE(MeasurementUnitSystem.CUBIC_METRE, Volume.class),
    LITER(MeasurementUnitSystem.LITER, Volume.class),

    // UnitDefinitions -> Electric Power
    WATT(MeasurementUnitSystem.WATT, Power.class, SpreadType.EQU, CompactType.AVG),
    KILO_WATT(MeasurementUnitSystem.KILO_WATT, Power.class, SpreadType.EQU, CompactType.AVG),
    MEGA_WATT(MeasurementUnitSystem.MEGA_WATT, Power.class, SpreadType.EQU, CompactType.AVG),
    GIGA_WATT(MeasurementUnitSystem.GIGA_WATT, Power.class, SpreadType.EQU, CompactType.AVG),

    // UnitDefinitions -> Elektric Energy
    WATT_SECOND(MeasurementUnitSystem.WATT_SECOND, Energy.class),
    WATT_HOUR(MeasurementUnitSystem.WATT_HOUR, Energy.class),
    KILO_WATT_HOUR(MeasurementUnitSystem.KILO_WATT_HOUR, Energy.class),
    MEGA_WATT_HOUR(MeasurementUnitSystem.MEGA_WATT_HOUR, Energy.class),
    GIGA_WATT_HOUR(MeasurementUnitSystem.GIGA_WATT_HOUR, Energy.class),

    // UnitDefinitions -> Money
    EURO(MeasurementUnitSystem.EURO, Money.class),
    EURO_CENT(MeasurementUnitSystem.EURO_CENT, Money.class),

    // UnitDefinitions -> Pricing
    EURO_CENT_PER_KILO_WATT_HOUR(MeasurementUnitSystem.EURO_CENT_PER_KILO_WATT_HOUR, SpreadType.EQU, CompactType.AVG),
    EURO_PER_KILO_WATT(MeasurementUnitSystem.EURO_PER_KILO_WATT, SpreadType.EQU, CompactType.AVG),
    EURO_PER_MEGA_WATT(MeasurementUnitSystem.EURO_PER_MEGA_WATT, SpreadType.EQU, CompactType.AVG),

    // UnitDefinitions -> Gas Burn Value
    BURN_VALUE(MeasurementUnitSystem.BURN_VALUE, SpreadType.EQU, CompactType.AVG);

    private Unit<?> unit;
    private Class type;
    private SpreadType spreadType;
    private CompactType compactType;
    TimeseriesUnit(Unit<?> unit) {
        this.unit = unit;
        this.type = null;
        this.spreadType = SpreadType.DST;
        this.compactType = CompactType.SUM;
    }

    TimeseriesUnit(Unit<?> unit, Class type) {
        this.unit = unit;
        this.type = type;
        this.spreadType = SpreadType.DST;
        this.compactType = CompactType.SUM;
    }

    TimeseriesUnit(Unit<?> unit, SpreadType spreadType, CompactType compactType) {
        this.unit = unit;
        this.type = null;
        this.spreadType = spreadType;
        this.compactType = compactType;
    }

    TimeseriesUnit(Unit<?> unit, Class type, SpreadType spreadType, CompactType compactType) {
        this.unit = unit;
        this.type = type;
        this.spreadType = spreadType;
        this.compactType = compactType;
    }

    public static TimeseriesUnit ofMeasurementUnit(Unit<?> unit) {
        if (unit.equals(MeasurementUnitSystem.NONE)) return TimeseriesUnit.NONE;
        else if (unit.equals(MeasurementUnitSystem.CUBIC_METRE)) return TimeseriesUnit.CUBIC_METRE;
        else if (unit.equals(MeasurementUnitSystem.LITER)) return TimeseriesUnit.LITER;
        else if (unit.equals(MeasurementUnitSystem.WATT)) return TimeseriesUnit.WATT;
        else if (unit.equals(MeasurementUnitSystem.KILO_WATT)) return TimeseriesUnit.KILO_WATT;
        else if (unit.equals(MeasurementUnitSystem.MEGA_WATT)) return TimeseriesUnit.MEGA_WATT;
        else if (unit.equals(MeasurementUnitSystem.GIGA_WATT)) return TimeseriesUnit.GIGA_WATT;
        else if (unit.equals(MeasurementUnitSystem.WATT_SECOND)) return TimeseriesUnit.WATT_SECOND;
        else if (unit.equals(MeasurementUnitSystem.WATT_HOUR)) return TimeseriesUnit.WATT_HOUR;
        else if (unit.equals(MeasurementUnitSystem.KILO_WATT_HOUR)) return TimeseriesUnit.KILO_WATT_HOUR;
        else if (unit.equals(MeasurementUnitSystem.MEGA_WATT_HOUR)) return TimeseriesUnit.MEGA_WATT_HOUR;
        else if (unit.equals(MeasurementUnitSystem.GIGA_WATT_HOUR)) return TimeseriesUnit.GIGA_WATT_HOUR;
        else if (unit.equals(MeasurementUnitSystem.EURO)) return TimeseriesUnit.EURO;
        else if (unit.equals(MeasurementUnitSystem.EURO_CENT)) return TimeseriesUnit.EURO_CENT;
        else if (unit.equals(MeasurementUnitSystem.EURO_CENT_PER_KILO_WATT_HOUR))
            return TimeseriesUnit.EURO_CENT_PER_KILO_WATT_HOUR;
        else if (unit.equals(MeasurementUnitSystem.EURO_PER_KILO_WATT)) return TimeseriesUnit.EURO_PER_KILO_WATT;
        else if (unit.equals(MeasurementUnitSystem.EURO_PER_MEGA_WATT)) return TimeseriesUnit.EURO_PER_MEGA_WATT;
        else if (unit.equals(MeasurementUnitSystem.BURN_VALUE)) return TimeseriesUnit.BURN_VALUE;
        else return TimeseriesUnit.UNKNOWN;
    }

    public Unit<?> toMeasurementUnit() {
        return this.unit;
    }

    public Class getMeasurementUnitType() {
        return this.type;
    }

    public SpreadType getSpreadType() {
        return this.spreadType;
    }

    public CompactType getCompactType() {
        return this.compactType;
    }

    public boolean isCompatible(TimeseriesUnit unit) {
        return this.unit.isCompatible(unit.toMeasurementUnit());
    }

    protected Unit<?> extractBaseunit() {
        if (this.unit.toString().contains("s")) return this.unit.divide(SI.SECOND);
        else if (this.unit.toString().contains("h")) return this.unit.divide(NonSI.HOUR);
        else return this.unit;
    }

    protected Unit<?> extractTimeunit() {
        if (this.unit.toString().contains("s")) return SI.SECOND;
        else if (this.unit.toString().contains("h")) return NonSI.HOUR;
        else return this.unit;
    }

    public boolean isTimeintegral() {
        return this.unit.toString().contains("s") || this.unit.toString().contains("h");
    }

    public boolean isTimeintegralOf(TimeseriesUnit unit) {
        return this.isTimeintegral() && extractBaseunit().isCompatible(unit.toMeasurementUnit());
    }

}
