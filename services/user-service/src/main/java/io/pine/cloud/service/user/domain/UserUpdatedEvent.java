package io.pine.cloud.service.user.domain;

import org.springframework.context.ApplicationEvent;

public class UserUpdatedEvent extends ApplicationEvent {
    public UserUpdatedEvent(User user) {
        super(user);
    }
}
