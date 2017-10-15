package net.naffets.nevsuite.backend.framework.core.api;

/**
 * @author br4sk4
 * created on 03.05.2016
 */
public interface EntityBean<ENTITY extends EntityBean, DTO extends DataTransferObject> extends Identifiable {

    void setPrimaryKey(Long primaryKey);

    void setUuid(String uuid);

    boolean isEntity();

    DTO asDTO();

    ENTITY fromDTO(DTO dto);

    Reference asReference();

}
