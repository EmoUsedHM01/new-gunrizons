package com.gtnewhorizon.newgunrizons.network;

import java.util.Deque;
import java.util.LinkedList;

public final class StatusMessageManager {
   public static final StatusMessageManager INSTANCE = new StatusMessageManager();
   private final Deque<StatusMessageManager.Message> messageQueue = new LinkedList<>();

   public void addMessage(String message, long duration) {
      long expiresAt = duration < 0L ? Long.MAX_VALUE : System.currentTimeMillis() + duration;

      while (!this.messageQueue.isEmpty()) {
         StatusMessageManager.Message m = this.messageQueue.removeFirst();
         if (m.expiresAt > expiresAt) {
            this.messageQueue.addFirst(m);
            break;
         }
      }

      this.messageQueue.addFirst(new StatusMessageManager.Message(message, expiresAt));
   }

   public void addAlertMessage(String message, int count, long duration, long pause) {
      long expiresAt = System.currentTimeMillis();
      this.messageQueue.clear();

      for (int i = 0; i < count; i++) {
         long var10 = expiresAt + duration;
         this.messageQueue.addLast(new StatusMessageManager.Message(message, var10, true));
         expiresAt = var10 + pause;
         this.messageQueue.addLast(new StatusMessageManager.Message("", expiresAt));
      }
   }

   public StatusMessageManager.Message nextMessage() {
      StatusMessageManager.Message result = null;

      while (!this.messageQueue.isEmpty()) {
         StatusMessageManager.Message m = this.messageQueue.removeFirst();
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
      String message;
      boolean isAlert;

      public Message(String message, long expiresAt) {
         this(message, expiresAt, false);
      }

      public Message(String message, long expiresAt, boolean isAlert) {
         this.message = message;
         this.expiresAt = expiresAt;
         this.isAlert = isAlert;
      }

      public String getMessage() {
         return this.message;
      }

      public boolean isAlert() {
         return this.isAlert;
      }
   }
}
