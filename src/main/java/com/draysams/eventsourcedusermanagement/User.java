package com.draysams.eventsourcedusermanagement;

import java.util.UUID;

public class User {
    private final UUID uuid;
    private String nickName = "";

    enum UserState {
        INITIALIZED,
        ACTIVATED,
        DEACTIVATED
    }

    private UserState state = UserState.INITIALIZED;


    public User(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    void activate() {
        if (isActivated()) {
            throw new IllegalStateException();
        }
        state = UserState.ACTIVATED;
    }

    void deactivate() {
        if (isDeactivated()) {
            throw new IllegalStateException();
        }

        state = UserState.DEACTIVATED;
    }

    String changeNicknameTo(String newNickname) {
        if (isDeactivated()) {
            throw new IllegalStateException();
        }

        this.nickName = newNickname;
        return this.nickName;
    }

    boolean isActivated() {
         return state.equals(UserState.ACTIVATED);
    }

    boolean isDeactivated() {
        return state.equals(UserState.DEACTIVATED);
    }

    String getNickname() {
        return this.nickName;
    }
}
