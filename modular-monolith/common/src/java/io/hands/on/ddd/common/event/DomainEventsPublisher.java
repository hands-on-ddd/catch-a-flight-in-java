package io.hands.on.ddd.common.event;

/**
 * Domain event publisher.
 */
public interface DomainEventsPublisher {
    void publish(DomainEvent event);
}
