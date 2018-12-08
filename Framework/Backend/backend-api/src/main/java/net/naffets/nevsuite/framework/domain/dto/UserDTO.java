package net.naffets.nevsuite.framework.domain.dto;

import net.naffets.nevsuite.framework.core.api.DataTransferObject;

import java.time.Instant;
import java.util.Map;

/**
 * @author br4sk4 / created on 13.06.2018
 */
public class UserDTO implements DataTransferObject {

    public String primaryKey;
    public String nickName;
    public String passwordHash;
    public Map<Instant, String> valueMap;

}
