package com.draysams.eventsourcedusermanagement.repositories;

import com.draysams.eventsourcedusermanagement.models.User;

import java.util.UUID;

public interface UserRepository {
    void save(User user);

    User findById(UUID uuid);
}
