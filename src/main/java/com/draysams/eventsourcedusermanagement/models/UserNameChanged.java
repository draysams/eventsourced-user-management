package com.draysams.eventsourcedusermanagement.models;

import java.time.Instant;

public class UserNameChanged implements DomainEvent{
    private final String newNickname;
    private final Instant when;

    public UserNameChanged(String newNickname, Instant when) {
        this.newNickname = newNickname;
        this.when = when;
    }

    public String getNewNickname() {
        return this.newNickname;
    }

    @Override
    public Instant occuredAt() {
        return when;
    }
}
