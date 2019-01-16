package com.badgehu.async.handler;

import com.badgehu.async.EventHandler;
import com.badgehu.async.EventModel;
import com.badgehu.async.EventType;
import com.badgehu.service.MessageService;
import com.badgehu.model.Message;
import com.badgehu.util.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by badgehu on 2016/7/16.
 */
@Component
public class LoginExceptionHandler implements EventHandler {
    @Autowired
    MessageService messageService;

    @Autowired
    MailSender mailSender;


    @Override
    public void doHandle(EventModel model) {
        // 判断是否有异常登陆
        Message message = new Message();
        message.setToId(model.getActorId());
        message.setContent("你上次的登陆ip异常");
        message.setFromId(3);
        message.setCreatedDate(new Date());
        messageService.addMessage(message);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", model.getExt("username"));
        mailSender.sendWithHTMLTemplate(model.getExt("email"), "登陆异常", "mails/welcome.html",
                map);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LOGIN);
    }
}
