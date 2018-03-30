package net.naffets.nevsuite.framework.test.context;

import net.naffets.nevsuite.framework.core.base.AbstractAssembler;

/**
 * @author br4sk4 / created on 30.03.2018
 */
public class SampleEntityAssembler extends AbstractAssembler<SampleEntity, SampleEntityFullDTO> {

    @Override
    public SampleEntityFullDTO toDTO(SampleEntity sampleEntity) {
        return SampleEntityFullDTO.builder()
                .primaryKey(sampleEntity.getPrimaryKey())
                .firstName(sampleEntity.getFirstName())
                .lastName(sampleEntity.getLastName())
                .build();
    }

}
