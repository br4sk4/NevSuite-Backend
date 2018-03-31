package net.naffets.nevsuite.backend.timeseries.lang.persistence;

import com.google.gson.Gson;
import lombok.Builder;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesValueDTO;
import net.naffets.nevsuite.framework.core.api.DataTransferObject;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author br4sk4 / created on 22.10.2017
 */
public class ValueMapDocument implements Serializable, DataTransferObject {

    private final static long serialVersionUID = 1L;

    @Builder
    public ValueMapDocument(List<TimeseriesValueDTO> valueMap) {
        this.valueMap = valueMap != null ? valueMap : new LinkedList<>();
    }

    public List<TimeseriesValueDTO> valueMap;

    public static String toJson(ValueMapDocument valueMapDocument) {
        return new Gson().toJson(valueMapDocument);
    }

    public static ValueMapDocument fromJson(String json) {
        return new Gson().fromJson(json, ValueMapDocument.class);
    }

}
