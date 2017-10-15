package net.naffets.nevsuite.backend.timeseries.core.valueplugin;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author br4sk4
 * created on 21.03.2016
 */
public interface ValuePlugin<T> {

    T create(Integer value);

    T create(Long value);

    T create(Double value);

    T create(BigDecimal value);

    T createZero();

    T createOne();

    T add(T t1, T t2);

    T subtract(T t1, T t2);

    T multiply(T t1, T t2);

    T divide(T t1, T t2);

    T min(T t1, T t2);

    T max(T t1, T t2);

    List<T> spread(T value,
                   Long estimatedSize,
                   SpreadType spreadType);

    T compact(List<T> value,
              CompactType compactType);

    Integer intValue(T value);

    Long longValue(T value);

    Double doubleValue(T value);

    BigDecimal bigDecimalValue(T value);

}
