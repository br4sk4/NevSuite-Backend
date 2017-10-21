package net.naffets.nevsuite.backend.framework.core.api;

/**
 * @author br4sk4
 * created on 03.05.2016
 */
public interface EntityBean<DTO extends DataTransferObject> extends Identifiable {

    DTO asDTO();

    Reference asReference();

}
