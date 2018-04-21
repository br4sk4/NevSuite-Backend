package net.naffets.nevsuite.eventsourcing.domain.repository;

import net.naffets.nevsuite.eventsourcing.domain.entity.EventNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author br4sk4
 * created on 30.06.2016
 */
@Repository
public interface EventNotificationRepository extends JpaRepository<EventNotification, String> {
}
