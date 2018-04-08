package net.naffets.nevsuite.backend.timeseries.domain.service;

import net.naffets.nevsuite.backend.timeseries.core.dataprovider.TimeseriesDataProvider;
import net.naffets.nevsuite.backend.timeseries.core.dataprovider.TimeseriesDataProviderBase;
import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesInterval;
import net.naffets.nevsuite.backend.timeseries.core.timeseries.TimeseriesPeriod;
import net.naffets.nevsuite.backend.timeseries.core.valueplugin.ValuePlugin;
import net.naffets.nevsuite.backend.timeseries.domain.repository.persistent.TimeseriesDocumentRepository;
import net.naffets.nevsuite.backend.timeseries.lang.persistence.ValueMapDocument;
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

    private String timeseriesIdentifier = "";

    @Autowired
    private TimeseriesDocumentRepository timeseriesDocumentRepository;

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

    public void setTimeseriesIdentifier(String timeseriesIdentifier) {
        this.timeseriesIdentifier = timeseriesIdentifier;
    }

    @Override
    public HashMap<Instant, T> load(TimeseriesInterval interval) {
        LinkedHashMap<Instant, T> map = new LinkedHashMap<>();
        timeseriesDocumentRepository.findByTimeseriesIdentifier(timeseriesIdentifier).stream()
                .map(doc -> ValueMapDocument.fromJson(doc.getValueMap()).valueMap)
                .forEach(valueList -> valueList.stream()
                        .filter(valueDto -> Instant.parse(valueDto.timestamp).compareTo(interval.getTimestampFrom()) > 0
                                && Instant.parse(valueDto.timestamp).compareTo(interval.getTimestampTo()) <= 0)
                        .sorted(Comparator.comparing(timeseriesValueDTO -> timeseriesValueDTO.timestamp))
                        .forEach(timeseriesValueDTO -> map.put(
                                Instant.parse(timeseriesValueDTO.timestamp),
                                valuePlugin.create(timeseriesValueDTO.value))));

        this.valueMap = map;
        return this.valueMap;
    }

    @SuppressWarnings("CloneDoesntCallSuperClone")
    public TimeseriesDataProvider<T> clone() {
        return new TimeseriesDataProviderService<>(this);
    }

}
