package net.naffets.nevsuite.backend.timeseries.core.valueplugin;

import java.util.LinkedList;
import java.util.List;

/**
 * @author br4sk4
 * created on 21.03.2016
 */
public abstract class ValuePluginBase<T> implements ValuePlugin<T> {

    public List<T> spread(T value,
                          Long estimatedSize,
                          SpreadType spreadType) {
        LinkedList<T> spreadedValues = new LinkedList<>();

        for (int index = 0; index < estimatedSize; index++) {
            if (spreadType.compareTo(SpreadType.EQU) == 0) spreadedValues.addLast(value);
            else if (spreadType.compareTo(SpreadType.DST) == 0)
                spreadedValues.addLast(this.divide(value, this.create(estimatedSize)));
            else spreadedValues.addLast(value);
        }

        return spreadedValues;
    }

    public T compact(List<T> values,
                     CompactType compactType) {
        T compactedValue = this.createZero();

        for (T listedValue : values) {
            if (compactType.compareTo(CompactType.SUM) == 0) compactedValue = this.add(compactedValue, listedValue);
            else if (compactType.compareTo(CompactType.AVG) == 0)
                compactedValue = this.add(compactedValue, listedValue);
            else if (compactType.compareTo(CompactType.MIN) == 0)
                compactedValue = this.min(compactedValue, listedValue);
            else if (compactType.compareTo(CompactType.MAX) == 0)
                compactedValue = this.min(compactedValue, listedValue);
            else compactedValue = this.add(compactedValue, listedValue);
        }

        if (compactType.compareTo(CompactType.AVG) == 0)
            compactedValue = this.divide(compactedValue, this.create(values.size()));

        return compactedValue;
    }

}
