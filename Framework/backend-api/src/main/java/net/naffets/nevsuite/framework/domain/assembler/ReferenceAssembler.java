package net.naffets.nevsuite.framework.domain.assembler;

import net.naffets.nevsuite.framework.core.api.Reference;
import net.naffets.nevsuite.framework.core.base.AbstractAssembler;
import net.naffets.nevsuite.framework.core.base.AbstractEntityBean;
import net.naffets.nevsuite.framework.domain.dto.ReferenceDTO;

/**
 * @author br4sk4 / created on 30.03.2018
 */
public class ReferenceAssembler  extends AbstractAssembler<AbstractEntityBean, ReferenceDTO> {

    @Override
    public ReferenceDTO toDTO(AbstractEntityBean entity) {
        Reference ref = entity.asReference();
        return ReferenceDTO.builder()
                .primaryKey(ref.getPrimaryKey())
                .typeDiscriminator(ref.getTypeDiscriminator())
                .representableName(ref.getRepresentableName())
                .build();
    }

}
