package net.naffets.nevsuite.framework.core.api;

import java.util.List;

/**
 * @author br4sk4 / created on 30.03.2018
 */
public interface Assembler<ENTITY, DTO extends DataTransferObject> {

    DTO toDTO (ENTITY entity);

    List<DTO> toDTO(List<ENTITY> entityList);

}
