package com.hybrid.SecondarySchoolSystem.repository;

import com.hybrid.SecondarySchoolSystem.entity.Message;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MessageRepository {

    private final List<Message> messages = new ArrayList<>();

    public Message save(Message message) {
        if (message.getId() == null) {
            message.setId(String.valueOf(messages.size() + 1));
        }
        messages.add(message);
        return message;
    }

    public List<Message> findAll() {
        return messages;
    }

    public Optional<Message> findById(String id) {
        return messages.stream().filter(m -> m.getId().equals(id)).findFirst();
    }

    public List<Message> findByRecipientId(String recipientId) {
        return messages.stream().filter(m -> m.getRecipientId().equals(recipientId)).toList();
    }

    public List<Message> findBySenderId(String senderId) {
        return messages.stream().filter(m -> m.getSenderId().equals(senderId)).toList();
    }

    public List<Message> findByRecipientIdAndIsReadFalse(String recipientId) {
        return messages.stream()
                .filter(m -> m.getRecipientId().equals(recipientId) && !m.getIsRead())
                .toList();
    }
}
