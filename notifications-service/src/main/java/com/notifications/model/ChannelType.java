package com.notifications.model;

public enum ChannelType {
    slack, email;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
