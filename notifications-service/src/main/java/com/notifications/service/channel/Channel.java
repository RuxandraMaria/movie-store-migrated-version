package com.notifications.service.channel;

import com.notifications.model.Message;
import com.notifications.model.ChannelType;

public interface Channel {
    default void notify(Message msg) {
        throw new RuntimeException("Notify method is not implemented yet.");
    }

    default boolean supports(ChannelType type) {
        throw new RuntimeException("Notify method is not implemented yet.");
    }
}
