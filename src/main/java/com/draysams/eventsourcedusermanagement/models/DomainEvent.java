package com.draysams.eventsourcedusermanagement.models;

import java.time.Instant;

public interface DomainEvent {
    Instant occuredAt();
}
