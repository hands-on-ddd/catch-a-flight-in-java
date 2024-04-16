package io.hands.on.ddd.common.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

/**
 * Forwards event to Spring ApplicationEventPublisher.
 */
@Slf4j
@AllArgsConstructor
public class JustForwardDomainEventPublisher implements DomainEventsPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(DomainEvent event) {
        log.info("Event published. Event id: {}. Event body: {}", event.eventId(), event);
        applicationEventPublisher.publishEvent(event);
    }
}
