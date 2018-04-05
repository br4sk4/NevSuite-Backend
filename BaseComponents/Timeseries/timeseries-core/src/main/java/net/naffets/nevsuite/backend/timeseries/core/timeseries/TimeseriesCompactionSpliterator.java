package net.naffets.nevsuite.backend.timeseries.core.timeseries;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author br4sk4
 * created on 04.04.18
 */
public class TimeseriesCompactionSpliterator implements Spliterator<Instant> {

    private List<Instant> instants;
    private TimeseriesInterval interval;
    private TimeseriesPeriod sourcePeriod;
    private TimeseriesPeriod targetPeriod;
    private Instant actTimestamp;

    private Integer current;

    public TimeseriesCompactionSpliterator(Set<Instant> instants, TimeseriesInterval interval, TimeseriesPeriod sourcePeriod, TimeseriesPeriod targetPeriod) {
        this.instants = instants.stream().sorted(Instant::compareTo).collect(Collectors.toList());
        this.interval = interval;
        this.sourcePeriod = sourcePeriod;
        this.targetPeriod = targetPeriod;
        this.actTimestamp = this.instants.stream().findFirst().orElse(null);

        this.current = 0;
    }

    private TimeseriesCompactionSpliterator(List<Instant> instants, TimeseriesInterval interval, TimeseriesPeriod sourcePeriod, TimeseriesPeriod targetPeriod) {
        this.instants = instants;
        this.interval = interval;
        this.sourcePeriod = sourcePeriod;
        this.targetPeriod = targetPeriod;
        this.actTimestamp = instants.stream().findFirst().orElse(null);

        this.current = 0;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Instant> action) {
        action.accept(instants.get(current));
        return true;
    }

    @Override
    public TimeseriesCompactionSpliterator trySplit() {
        Long newValueCount = current + estimateSize();
        List<Instant> instantList = new ArrayList<>();

        if (current < this.instants.size()) {
            for (int x = current; x < newValueCount; x++) {
                instantList.add(instants.get(x));
                current++;
            }

            if (current < this.instants.size()) actTimestamp = this.instants.get(current);
            return new TimeseriesCompactionSpliterator(instantList, this.interval, this.sourcePeriod, this.targetPeriod);
        }

        current = 0;
        return null;
    }

    @Override
    public void forEachRemaining(Consumer<? super Instant> action) {
        instants.forEach(action::accept);
    }

    @Override
    public long estimateSize() {
        return targetPeriod.toSeconds(actTimestamp.atZone(this.interval.zoneId)) / sourcePeriod.toSeconds(actTimestamp.atZone(this.interval.zoneId));
    }

    @Override
    public int characteristics() {
        return SORTED;
    }

    public List<Instant> getInstants() {
        return this.instants;
    }
}
