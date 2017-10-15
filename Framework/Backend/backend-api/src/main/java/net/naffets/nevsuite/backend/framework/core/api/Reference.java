package net.naffets.nevsuite.backend.framework.core.api;

/**
 * @author br4sk4
 * created on 19.04.2016
 */
public interface Reference extends Identifiable {

    String getRepresentableName();

    String getTypeDiscriminator();

}
