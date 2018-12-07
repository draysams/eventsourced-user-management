package com.draysams.eventsourcedusermanagement.repositories;

import com.draysams.eventsourcedusermanagement.models.User;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepositoryImpl implements UserRepository{

    private final Map<UUID, User> users = new ConcurrentHashMap<>();
    @Override
    public void save(User user) {
        users.put(user.getUuid(), user);
    }

    @Override
    public User findById(UUID uuid) {
        return users.get(uuid);
    }
}
