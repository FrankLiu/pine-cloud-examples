package org.pine.cloud.computer.event;

import org.springframework.context.ApplicationEvent;

public class ContentEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param content the object on which the event initially occurred (never {@code null})
     */
    public ContentEvent(final String content) {
        super(content);
    }
}
