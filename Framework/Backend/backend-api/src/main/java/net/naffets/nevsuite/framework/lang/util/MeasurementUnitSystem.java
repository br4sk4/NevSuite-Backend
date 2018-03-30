package net.naffets.nevsuite.framework.lang.util;

import org.jscience.economics.money.Currency;
import org.jscience.economics.money.Money;

import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Energy;
import javax.measure.quantity.Power;
import javax.measure.quantity.Volume;
import javax.measure.unit.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author br4sk4
 * created on 17.12.2015
 */
public final class MeasurementUnitSystem extends SystemOfUnits {

    public static final Unit<Dimensionless> NONE;
    public static final Unit<Dimensionless> UNKNOWN;
    // Volume
    public static final Unit<Volume> CUBIC_METRE;
    public static final Unit<Volume> LITER;
    // Power
    public static final Unit<Power> WATT;
    public static final Unit<Power> KILO_WATT;
    public static final Unit<Power> MEGA_WATT;
    public static final Unit<Power> GIGA_WATT;
    // Energy
    public static final Unit<Energy> WATT_SECOND;
    public static final Unit<Energy> WATT_HOUR;
    public static final Unit<Energy> KILO_WATT_HOUR;
    public static final Unit<Energy> MEGA_WATT_HOUR;
    public static final Unit<Energy> GIGA_WATT_HOUR;
    // Money
    public static final Unit<Money> EURO;
    public static final Unit<Money> EURO_CENT;
    // Pricing
    public static final Unit<?> EURO_CENT_PER_KILO_WATT_HOUR;
    public static final Unit<?> EURO_PER_KILO_WATT;
    public static final Unit<?> EURO_PER_MEGA_WATT;
    // Gas Burn Value
    public static final Unit<?> BURN_VALUE;
    private static final MeasurementUnitSystem INSTANCE = new MeasurementUnitSystem();
    private static HashSet<Unit<?>> UNITS = new HashSet();

    static {

        NONE = MeasurementUnitSystem(Unit.ONE);
        UNKNOWN = MeasurementUnitSystem(Unit.ONE);

        // UnitDefinitions -> Volume
        CUBIC_METRE = MeasurementUnitSystem(SI.CUBIC_METRE);
        LITER = MeasurementUnitSystem(NonSI.LITER);

        // UnitDefinitions -> Electric Power
        WATT = MeasurementUnitSystem(SI.WATT);
        KILO_WATT = MeasurementUnitSystem(SI.KILO(SI.WATT));
        MEGA_WATT = MeasurementUnitSystem(SI.MEGA(SI.WATT));
        GIGA_WATT = MeasurementUnitSystem(SI.GIGA(SI.WATT));

        // UnitDefinitions -> Elektric Energy
        WATT_SECOND = MeasurementUnitSystem(WATT.times(SI.SECOND).asType(Energy.class));
        WATT_HOUR = MeasurementUnitSystem(WATT.times(NonSI.HOUR).asType(Energy.class));
        KILO_WATT_HOUR = MeasurementUnitSystem(SI.KILO(WATT_HOUR).asType(Energy.class));
        MEGA_WATT_HOUR = MeasurementUnitSystem(SI.MEGA(WATT_HOUR).asType(Energy.class));
        GIGA_WATT_HOUR = MeasurementUnitSystem(SI.GIGA(WATT_HOUR).asType(Energy.class));

        // UnitDefinitions -> Money
        EURO = MeasurementUnitSystem(Currency.EUR);
        EURO_CENT = MeasurementUnitSystem(Currency.EUR.divide(100));

        // UnitDefinitions -> Pricing
        EURO_CENT_PER_KILO_WATT_HOUR = MeasurementUnitSystem(EURO_CENT.divide(KILO_WATT_HOUR));
        EURO_PER_KILO_WATT = MeasurementUnitSystem(EURO.divide(KILO_WATT));
        EURO_PER_MEGA_WATT = MeasurementUnitSystem(EURO.divide(MEGA_WATT));

        // UnitDefinitions -> Gas Burn Value
        BURN_VALUE = MeasurementUnitSystem(KILO_WATT_HOUR.divide(CUBIC_METRE));

        // Labels
        UnitFormat.getInstance().label(MeasurementUnitSystem.WATT_SECOND, "Ws");
        UnitFormat.getInstance().label(MeasurementUnitSystem.WATT_HOUR, "Wh");
        UnitFormat.getInstance().label(MeasurementUnitSystem.KILO_WATT_HOUR, "kWh");
        UnitFormat.getInstance().label(MeasurementUnitSystem.MEGA_WATT_HOUR, "MWh");
        UnitFormat.getInstance().label(MeasurementUnitSystem.GIGA_WATT_HOUR, "GWh");
        UnitFormat.getInstance().label(MeasurementUnitSystem.EURO, "EUR");
        UnitFormat.getInstance().label(MeasurementUnitSystem.EURO_CENT, "ct");

        Currency.setReferenceCurrency(Currency.EUR);

    }

    private MeasurementUnitSystem() {
    }

    public static MeasurementUnitSystem getInstance() {
        return INSTANCE;
    }

    private static <U extends Unit<?>> U MeasurementUnitSystem(U unit) {
        UNITS.add(unit);
        return unit;
    }

    public Set<Unit<?>> getUnits() {
        return Collections.unmodifiableSet(UNITS);
    }

}
