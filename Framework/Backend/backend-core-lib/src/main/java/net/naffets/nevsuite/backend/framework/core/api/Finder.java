package net.naffets.nevsuite.backend.framework.core.api;

import java.util.List;

/**
 * @author br4sk4
 * created on 13.07.2016
 */
public interface Finder<ENTITY> {

    ENTITY byUuid(String uuid);

    List<ENTITY> all();

}
