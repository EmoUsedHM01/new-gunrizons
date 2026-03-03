package com.gtnewhorizon.newgunrizons.network;

import java.util.Deque;
import java.util.LinkedList;

import lombok.Getter;

public final class StatusMessageCenter {

    private final Deque<StatusMessageCenter.Message> messageQueue = new LinkedList<>();

    public void addMessage(String message, long duration) {
        long expiresAt = duration < 0L ? Long.MAX_VALUE : System.currentTimeMillis() + duration;

        while (!this.messageQueue.isEmpty()) {
            StatusMessageCenter.Message m = this.messageQueue.removeFirst();
            if (m.expiresAt > expiresAt) {
                this.messageQueue.addFirst(m);
                break;
            }
        }

        this.messageQueue.addFirst(new StatusMessageCenter.Message(message, expiresAt));
    }

    public void addAlertMessage(String message, int count, long duration, long pause) {
        long expiresAt = System.currentTimeMillis();
        this.messageQueue.clear();

        for (int i = 0; i < count; ++i) {
            expiresAt += duration;
            this.messageQueue.addLast(new StatusMessageCenter.Message(message, expiresAt, true));
            expiresAt += pause;
            this.messageQueue.addLast(new StatusMessageCenter.Message("", expiresAt));
        }

    }

    public StatusMessageCenter.Message nextMessage() {
        StatusMessageCenter.Message result = null;

        while (!this.messageQueue.isEmpty()) {
            StatusMessageCenter.Message m = this.messageQueue.removeFirst();
            if (m.expiresAt > System.currentTimeMillis()) {
                result = m;
                this.messageQueue.addFirst(m);
                break;
            }
        }

        return result;
    }

    public static class Message {

        long expiresAt;

        @Getter
        String message;
        @Getter
        boolean isAlert;

        public Message(String message, long expiresAt) {
            this(message, expiresAt, false);
        }

        public Message(String message, long expiresAt, boolean isAlert) {
            this.message = message;
            this.expiresAt = expiresAt;
            this.isAlert = isAlert;
        }

    }
}
