package com.hybrid.SecondarySchoolSystem.service.interfaces;

import com.hybrid.SecondarySchoolSystem.entity.Message;

import java.util.List;

public interface IMessageService {
    Message sendMessage(String senderId, String recipientId, String content);
    List<Message> getReceivedMessages(String recipientId);
    List<Message> getSentMessages(String senderId);
    List<Message> getUnreadMessages(String recipientId);
    void markAsRead(String messageId);
}
