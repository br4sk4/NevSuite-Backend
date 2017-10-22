package net.naffets.nevsuite.backend.timeseries.lang.persistence;

import net.naffets.nevsuite.backend.framework.core.base.AbstractDataTransferObjectBuilder;
import net.naffets.nevsuite.backend.timeseries.domain.dto.TimeseriesValueDTO;

import java.util.List;

/**
 * @author br4sk4 / created on 22.10.2017
 */
public class ValueMapDocumentBuilder extends AbstractDataTransferObjectBuilder<ValueMapDocument> {

    public ValueMapDocumentBuilder() {
        super(new ValueMapDocument());
    }

    @Override
    public ValueMapDocumentBuilder fromDTO(ValueMapDocument valueMapDocument) {
        this.dto.getValueMap().addAll(valueMapDocument.getValueMap());
        return this;
    }

    public ValueMapDocumentBuilder withValues(List<TimeseriesValueDTO> values) {
        this.dto.getValueMap().addAll(values);
        return this;
    }

}
