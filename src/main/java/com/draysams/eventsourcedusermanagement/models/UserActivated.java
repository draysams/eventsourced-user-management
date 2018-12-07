package com.draysams.eventsourcedusermanagement.models;

import java.time.Instant;

public class UserActivated implements DomainEvent{
    private final Instant when;
    public UserActivated(Instant when) {
        this.when = when;
    }

    @Override
    public Instant occuredAt() {
        return when;
    }
}
