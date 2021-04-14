package io.pine.cloud.service.user.infrastructure.messaging;

import io.pine.cloud.service.user.domain.event.UserCreatedEvent;
import io.pine.cloud.service.user.domain.event.UserDeletedEvent;
import io.pine.cloud.service.user.domain.event.UserUpdatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UserListener implements SmartApplicationListener {
    private final static Logger logger = LoggerFactory.getLogger(UserListener.class);

    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == UserCreatedEvent.class
                || eventType == UserUpdatedEvent.class
                || eventType == UserDeletedEvent.class;
    }

    @Override
    public boolean supportsSourceType(final Class<?> sourceType) {
        return sourceType == String.class;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        logger.info("收到用户事件：{}", event);
        // TODO, send event to MQ for next processing

    }

    @Override
    public int getOrder() {
        return 1;
    }
}
