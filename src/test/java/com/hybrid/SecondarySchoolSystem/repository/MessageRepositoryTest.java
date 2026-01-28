package com.hybrid.SecondarySchoolSystem.repository;

import com.hybrid.SecondarySchoolSystem.entity.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageRepositoryTest {
    MessageRepository messageRepository;

    @BeforeEach
    void setUp() {
        messageRepository = new MessageRepository();
    }

    @Test
    public void save_Success_Test() {
        Message message = new Message("sender1", "recipient1", "Hello");
        Message saved = messageRepository.save(message);

        assertNotNull(saved.getId());
    }

    @Test
    public void findByRecipientId_Success_Test() {
        messageRepository.save(new Message("sender1", "recipient1", "Message 1"));
        messageRepository.save(new Message("sender2", "recipient1", "Message 2"));

        List<Message> messages = messageRepository.findByRecipientId("recipient1");
        assertEquals(2, messages.size());
    }

    @Test
    public void findBySenderId_Success_Test() {
        messageRepository.save(new Message("sender1", "recipient1", "Message 1"));
        messageRepository.save(new Message("sender1", "recipient2", "Message 2"));

        List<Message> messages = messageRepository.findBySenderId("sender1");
        assertEquals(2, messages.size());
    }

    @Test
    public void findByRecipientIdAndIsReadFalse_Success_Test() {
        messageRepository.save(new Message("sender1", "recipient1", "Message 1"));
        Message msg2 = messageRepository.save(new Message("sender2", "recipient1", "Message 2"));
        msg2.setIsRead(true);
        messageRepository.save(msg2);

        List<Message> unreadMessages = messageRepository.findByRecipientIdAndIsReadFalse("recipient1");
        assertEquals(1, unreadMessages.size());
    }
}
