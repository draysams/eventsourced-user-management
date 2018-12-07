package com.draysams.eventsourcedusermanagement

import com.draysams.eventsourcedusermanagement.models.User
import spock.lang.Specification

class UserTest extends Specification {

    private user = new User(UUID.randomUUID());

    def 'deactivated user cannot change nickname' () {
        given:
            user.deactivate()
        when:
            user.changeNicknameTo("Sam")
        then:
            thrown(IllegalStateException)
    }

    def 'activated user can change nickname' () {
        given:
            user.activate()
        when:
            user.changeNicknameTo("Sam")
        then:
            user.getNickname() == "Sam"

    }

    def 'new user can be activated' () {
        when:
            user.activate()
        then:
            user.isActivated()
    }

    def 'activated user can be deactivated' () {
        given:
            user.isActivated()
        when:
            user.deactivate()
        then:
            user.isDeactivated()
    }

    def 'activated user cannot be activated' () {
        given:
            user.activate()
        when:
            user.activate()
        then:
            thrown(IllegalStateException)
    }

    def 'deactivated user cannot be deactivated' () {
        given:
            user.deactivate()
        when:
            user.deactivate()
        then:
            thrown(IllegalStateException)
    }
}
