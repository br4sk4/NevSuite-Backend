package net.naffets.nevsuite.backend.framework.domain.assembler;

import net.naffets.nevsuite.backend.framework.domain.entity.User;
import net.naffets.nevsuite.framework.core.base.AbstractAssembler;
import net.naffets.nevsuite.framework.domain.dto.UserDTO;

import java.util.HashMap;

/**
 * @author br4sk4 / created on 13.06.2018
 */
public class UserAssembler extends AbstractAssembler<User, UserDTO> {

    @Override
    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();

        dto.primaryKey = user.getPrimaryKey();
        dto.nickName = user.getNickName();
        dto.passwordHash = user.getPasswordHash();

        dto.valueMap = new HashMap<>();
        user.getTimelinedAttributeValues()
                .forEach(timelinedAttributeValue -> dto.valueMap.put(
                        timelinedAttributeValue.getTimestamp(),
                        timelinedAttributeValue.getValue()));

        return dto;
    }

}
