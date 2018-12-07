package com.draysams.eventsourcedusermanagement.repositories

import com.draysams.eventsourcedusermanagement.models.User
import spock.lang.Specification

class UserRepositoryImplTest extends Specification {
    UserRepository userRepository = new UserRepositoryImpl();

    def 'should be able to save and load user' () {
        given:
            UUID uuid = UUID.randomUUID();
        and:
            User someUser = new User(uuid)
        and:
            someUser.activate()
        and:
            someUser.changeNicknameTo("Sam")
        when:
            userRepository.save(someUser)
        and:
            User loadedUser = userRepository.findById(someUser.getUuid())
        then:
            loadedUser.isActivated()
            loadedUser.getNickname() == "Sam"

    }
}
