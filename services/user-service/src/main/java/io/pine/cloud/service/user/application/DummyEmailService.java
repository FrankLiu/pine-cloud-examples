package io.pine.cloud.service.user.application;

import io.pine.cloud.service.user.domain.User;
import io.pine.cloud.service.user.domain.UserCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class DummyEmailService {
    public void sendEmail(User user) {
        System.out.println("发送邮件到: " + user.getName() + "[" + user.getEmail() + "]");
    }

    @EventListener
    public void userNoticeListener(UserCreatedEvent userCreatedEvent) {
        sendEmail((User)userCreatedEvent.getSource());
    }

}
