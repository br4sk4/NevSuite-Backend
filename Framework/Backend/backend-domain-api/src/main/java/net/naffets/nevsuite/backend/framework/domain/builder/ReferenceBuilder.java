package net.naffets.nevsuite.backend.framework.domain.builder;

import net.naffets.nevsuite.backend.framework.core.base.AbstractDataTransferObjectBuilder;
import net.naffets.nevsuite.backend.framework.domain.dto.ReferenceDTO;

/**
 * @author br4sk4
 * created on 19.10.2016
 */
public class ReferenceBuilder extends AbstractDataTransferObjectBuilder<ReferenceDTO> {

    public ReferenceBuilder() {
        super(new ReferenceDTO());
    }

    @Override
    public AbstractDataTransferObjectBuilder<ReferenceDTO> fromDTO(ReferenceDTO referenceDTO) {
        this.dto.setPrimaryKey(referenceDTO.getPrimaryKey());
        this.dto.setUuid(referenceDTO.getUuid());
        return this;
    }

    public ReferenceBuilder withPrimaryKey(Long value) {
        dto.setPrimaryKey(value);
        return this;
    }

    public ReferenceBuilder withUuid(String value) {
        dto.setUuid(value);
        return this;
    }

}
