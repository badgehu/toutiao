package com.badgehu.async;

import java.util.List;

/**
 * Created by badgehu on 2016/7/16.
 */
public interface EventHandler {
    void doHandle(EventModel model);
    List<EventType> getSupportEventTypes();
}
