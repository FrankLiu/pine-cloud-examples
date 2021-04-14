package io.pine.cloud.service.user.domain.event;

import io.pine.cloud.service.user.domain.User;
import org.springframework.context.ApplicationEvent;

public class UserCreatedEvent extends ApplicationEvent {
    public UserCreatedEvent(User user) {
        super(user);
    }
}
