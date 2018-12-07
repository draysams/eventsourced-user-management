package com.draysams.eventsourcedusermanagement.models;

import com.sun.javafx.collections.ImmutableObservableList;
import io.vavr.API;
import io.vavr.Predicates;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static io.vavr.API.Case;
import static io.vavr.Predicates.instanceOf;

public class User {
    private final UUID uuid;
    private String nickName = "";

    public List<DomainEvent> getNewChanges() {
        return changes;
    }

    public void flushChanges() {
        changes.clear();
    }

    public static User recreateFrom(UUID uuid, List<DomainEvent> domainEvents) {
        return io.vavr.collection.List.ofAll(domainEvents).foldLeft(new User(uuid), User::handleEvent);
    }

    private User handleEvent(DomainEvent domainEvent) {
        return io.vavr.API.Match(domainEvent).of(
                io.vavr.API.Case(io.vavr.Predicates.instanceOf(UserActivated.class), userActivated(event));
        );
    }

    enum UserState {
        INITIALIZED,
        ACTIVATED,
        DEACTIVATED
    }

    private UserState state = UserState.INITIALIZED;

    private List<DomainEvent> changes = new ArrayList<>();

    public User(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void activate() {
        if (isActivated()) {
            throw new IllegalStateException();
        }
        userActivated(new UserActivated(Instant.now()));
    }

    private User userActivated(UserActivated event) {
        state = UserState.ACTIVATED;
        changes.add(event);
        return this;
    }

    public void deactivate() {
        if (isDeactivated()) {
            throw new IllegalStateException();
        }
        userDeactivated(new UserDeactivated(Instant.now()));
    }

    private User userDeactivated(UserDeactivated event) {
        state = UserState.DEACTIVATED;
        changes.add(event);
        return this;
    }

    void changeNicknameTo(String newNickname) {
        if (isDeactivated()) {
            throw new IllegalStateException();
        }

        userNameChanged(new UserNameChanged(newNickname, Instant.now()));
    }

    private User userNameChanged(UserNameChanged event) {
        nickName = event.getNewNickname();
        changes.add(event);

        return this;
    }

    public boolean isActivated() {
         return state.equals(UserState.ACTIVATED);
    }

    public boolean isDeactivated() {
        return state.equals(UserState.DEACTIVATED);
    }

    public String getNickname() {
        return this.nickName;
    }
}
