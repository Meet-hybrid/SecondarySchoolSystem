package com.hybrid.SecondarySchoolSystem.service;

import com.hybrid.SecondarySchoolSystem.entity.Message;
import com.hybrid.SecondarySchoolSystem.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message sendMessage(String senderId, String recipientId, String content) {
        Message message = new Message(senderId, recipientId, content);
        return messageRepository.save(message);
    }

    public List<Message> getReceivedMessages(String recipientId) {
        return messageRepository.findByRecipientId(recipientId);
    }

    public List<Message> getSentMessages(String senderId) {
        return messageRepository.findBySenderId(senderId);
    }

    public List<Message> getUnreadMessages(String recipientId) {
        return messageRepository.findByRecipientIdAndIsReadFalse(recipientId);
    }

    public void markAsRead(String messageId) {
        messageRepository.findById(messageId).ifPresent(message -> {
            message.setIsRead(true);
            messageRepository.save(message);
        });
    }
}
