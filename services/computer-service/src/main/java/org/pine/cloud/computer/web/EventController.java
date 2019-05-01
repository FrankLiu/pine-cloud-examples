package org.pine.cloud.computer.web;

import org.pine.cloud.computer.event.ContentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
    @Autowired
    private ApplicationContext applicationContext;

    @ResponseBody
    @RequestMapping(path = "/event", method = RequestMethod.GET)
    public String sendEvent() {
        applicationContext.publishEvent(new ContentEvent("今年是猪年"));
        return "success";
    }

}
