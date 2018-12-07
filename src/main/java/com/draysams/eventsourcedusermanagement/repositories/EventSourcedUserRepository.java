package com.draysams.eventsourcedusermanagement.repositories;

import com.draysams.eventsourcedusermanagement.models.DomainEvent;
import com.draysams.eventsourcedusermanagement.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class EventSourcedUserRepository implements UserRepository {

    private final Map<UUID, List<DomainEvent>> changeList = new ConcurrentHashMap<>();

    @Override
    public void save(User user) {
         List<DomainEvent> newChanges = user.getNewChanges();
         List<DomainEvent> currentChanges = changeList.getOrDefault(user.getUuid(), new ArrayList<>());
         currentChanges.addAll(newChanges);

         changeList.put(user.getUuid(), currentChanges);

         user.flushChanges();
    }

    @Override
    public User findById(UUID uuid) {
        if (!changeList.containsKey(uuid)) {
            return null;
        }
        return User.recreateFrom(uuid, changeList.get(uuid));
    }
}
