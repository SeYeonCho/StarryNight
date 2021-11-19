package com.ssafy.starry.common.utils.notification;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationManager {

    private Logger log = LoggerFactory.getLogger(NotificationManager.class);

    private final MatterMostSender mmSender;

    public void sendNotification(Exception ex, String uri, String params) {
        log.info("## Send Notifications ##");
        mmSender.sendMessage(ex, uri, params);
    }
}
