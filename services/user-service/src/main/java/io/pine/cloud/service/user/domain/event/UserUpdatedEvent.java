package io.pine.cloud.service.user.domain.event;

import io.pine.cloud.service.user.domain.User;
import org.springframework.context.ApplicationEvent;

public class UserUpdatedEvent extends ApplicationEvent {
    public UserUpdatedEvent(User user) {
        super(user);
    }
}
