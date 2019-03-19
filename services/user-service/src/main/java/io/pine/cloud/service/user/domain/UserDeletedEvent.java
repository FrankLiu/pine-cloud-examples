package io.pine.cloud.service.user.domain;

import org.springframework.context.ApplicationEvent;

public class UserDeletedEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param user {@link User}
     */
    public UserDeletedEvent(User user) {
        super(user);
    }
}
