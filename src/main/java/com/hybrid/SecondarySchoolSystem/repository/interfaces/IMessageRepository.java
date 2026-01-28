package com.hybrid.SecondarySchoolSystem.repository.interfaces;

import com.hybrid.SecondarySchoolSystem.entity.Message;

import java.util.List;
import java.util.Optional;

public interface IMessageRepository {
    Message save(Message message);
    List<Message> findAll();
    Optional<Message> findById(String id);
    List<Message> findByRecipientId(String recipientId);
    List<Message> findBySenderId(String senderId);
    List<Message> findByRecipientIdAndIsReadFalse(String recipientId);
}
