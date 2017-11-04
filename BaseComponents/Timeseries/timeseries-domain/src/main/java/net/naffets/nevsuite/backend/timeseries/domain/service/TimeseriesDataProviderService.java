package net.naffets.nevsuite.backend.timeseries.domain.service;

import net.naffets.nevsuite.backend.timeseries.core.dataprovider.TimeseriesDataProvider;
import net.naffets.nevsuite.backend.timeseries.core.dataprovider.TimeseriesDataProviderBase;
import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesInterval;
import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesPeriod;
import net.naffets.nevsuite.backend.timeseries.core.valueplugin.ValuePlugin;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesValueDTO;
import net.naffets.nevsuite.backend.timeseries.domain.repository.persistent.TimeseriesDocumentRepository;
import net.naffets.nevsuite.backend.timeseries.lang.persistence.ValueMapDocumentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author br4sk4 / created on 04.11.2017
 */
@Service
public class TimeseriesDataProviderService<T> extends TimeseriesDataProviderBase<T> {

    @Autowired
    TimeseriesDocumentRepository timeseriesDocumentRepository;

    public TimeseriesDataProviderService() {
        super(null, TimeseriesPeriod.DAY1);
    }

    public TimeseriesDataProviderService(ValuePlugin<T> valuePlugin, TimeseriesPeriod period) {
        super(valuePlugin, period);
    }

    public TimeseriesDataProviderService(TimeseriesDataProviderBase<T> dataProvider) {
        super(dataProvider);
    }

    public void setValuePlugin(ValuePlugin<T> valuePlugin) {
        this.valuePlugin = valuePlugin;
    }

    @Override
    public HashMap<Instant, T> load(TimeseriesInterval interval) {
        LinkedHashMap<Instant, T> map = new LinkedHashMap<>();
        timeseriesDocumentRepository.findAll().stream()
                .map(doc -> new ValueMapDocumentBuilder().fromJson(doc.getValueMap()).getValueMap())
                .forEach(valueList -> valueList.stream()
                        .sorted(Comparator.comparing(TimeseriesValueDTO::getTimestamp))
                        .forEach(value -> map.put(
                                Instant.parse(value.getTimestamp()),
                                valuePlugin.create(value.getValue()))));

        return map;
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    public TimeseriesDataProvider<T> clone() {
        return new TimeseriesDataProviderService<>(this);
    }

}
