package net.naffets.nevsuite.eventsourcing.domain.repository;

import net.naffets.nevsuite.eventsourcing.domain.entity.EventDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author br4sk4 / created on 26.06.2016
 */
@Repository
public interface EventDescriptorRepository extends JpaRepository<EventDescriptor, String> {
}
