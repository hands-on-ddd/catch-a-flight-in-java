package io.hands.on.ddd.common.event;

import java.util.UUID;

/**
 * Domain event base interface.
 */
public interface DomainEvent {
    UUID eventId();
}
