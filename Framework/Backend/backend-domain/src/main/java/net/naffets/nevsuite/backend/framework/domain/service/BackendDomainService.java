package net.naffets.nevsuite.backend.framework.domain.service;

import net.naffets.nevsuite.backend.framework.domain.assembler.UserAssembler;
import net.naffets.nevsuite.backend.framework.domain.entity.TimelinedAttributeValue;
import net.naffets.nevsuite.backend.framework.domain.entity.User;
import net.naffets.nevsuite.backend.framework.domain.repository.persistent.UserRepository;
import net.naffets.nevsuite.framework.domain.dto.UserDTO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author br4sk4 / created on 13.06.2018
 */
@Service
public class BackendDomainService {

    private final UserRepository userRepository;

    @Inject
    public BackendDomainService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findUsers() {
        return new UserAssembler().toDTO(this.userRepository.findAll());
    }

    public UserDTO createUser() {
        List<TimelinedAttributeValue<User>> timelinedAttributeValues = new ArrayList<>();
        timelinedAttributeValues.add(new TimelinedAttributeValue<>("salary", Instant.parse("2017-01-01T00:00:00Z"), new BigDecimal(255000)));
        timelinedAttributeValues.add(new TimelinedAttributeValue<>("salary", Instant.parse("2018-01-01T00:00:00Z"), new BigDecimal(505000)));

        User user = User.builder()
                .nickName("br4sk4")
                .password("spring")
                .timelinedAttributeValues(timelinedAttributeValues)
                .build();

        user = userRepository.save(user);
        return new UserAssembler().toDTO(user);
    }

}
