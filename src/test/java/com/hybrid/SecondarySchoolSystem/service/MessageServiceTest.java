package com.hybrid.SecondarySchoolSystem.service;

import com.hybrid.SecondarySchoolSystem.entity.Message;
import com.hybrid.SecondarySchoolSystem.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageServiceTest {
    MessageService messageService;
    MessageRepository messageRepository;

    @BeforeEach
    void setUp() {
        messageRepository = new MessageRepository();
        messageService = new MessageService(messageRepository);
    }

    @Test
    public void sendMessage_Success_Test() {
        Message message = messageService.sendMessage("sender1", "recipient1", "Hello, this is a test message");

        assertNotNull(message);
        assertEquals("sender1", message.getSenderId());
        assertEquals("recipient1", message.getRecipientId());
        assertEquals("Hello, this is a test message", message.getContent());
        assertFalse(message.getIsRead());
    }

    @Test
    public void getReceivedMessages_Success_Test() {
        messageService.sendMessage("sender1", "recipient1", "Message 1");
        messageService.sendMessage("sender2", "recipient1", "Message 2");

        List<Message> messages = messageService.getReceivedMessages("recipient1");
        assertEquals(2, messages.size());
    }

    @Test
    public void getSentMessages_Success_Test() {
        messageService.sendMessage("sender1", "recipient1", "Message 1");
        messageService.sendMessage("sender1", "recipient2", "Message 2");

        List<Message> messages = messageService.getSentMessages("sender1");
        assertEquals(2, messages.size());
    }

    @Test
    public void getUnreadMessages_Success_Test() {
        messageService.sendMessage("sender1", "recipient1", "Message 1");
        Message msg2 = messageService.sendMessage("sender2", "recipient1", "Message 2");
        messageService.markAsRead(msg2.getId());

        List<Message> unreadMessages = messageService.getUnreadMessages("recipient1");
        assertEquals(1, unreadMessages.size());
    }

    @Test
    public void markAsRead_Success_Test() {
        Message message = messageService.sendMessage("sender1", "recipient1", "Test");
        messageService.markAsRead(message.getId());

        Message retrieved = messageRepository.findById(message.getId()).orElse(null);
        assertTrue(retrieved.getIsRead());
    }

    @Test
    public void sendMessage_HasValidId_Test() {
        Message message = messageService.sendMessage("s1", "r1", "Content");
        
        assertNotNull(message.getId());
        assertFalse(message.getId().isEmpty());
    }

    @Test
    public void sendMessage_ReturnsValidMessage_Test() {
        Message message = messageService.sendMessage("s1", "r1", "Content");
        
        assertNotNull(message);
        assertNotNull(message.getId());
    }

    @Test
    public void getReceivedMessages_EmptyList_WhenNoMessages_Test() {
        List<Message> messages = messageService.getReceivedMessages("nonexistent");
        
        assertNotNull(messages);
        assertEquals(0, messages.size());
    }

    @Test
    public void getSentMessages_EmptyList_WhenNoMessages_Test() {
        List<Message> messages = messageService.getSentMessages("nonexistent");
        
        assertNotNull(messages);
        assertEquals(0, messages.size());
    }

    @Test
    public void getUnreadMessages_EmptyList_WhenAllRead_Test() {
        Message msg1 = messageService.sendMessage("s1", "r1", "Msg1");
        Message msg2 = messageService.sendMessage("s2", "r1", "Msg2");
        
        messageService.markAsRead(msg1.getId());
        messageService.markAsRead(msg2.getId());
        
        List<Message> unread = messageService.getUnreadMessages("r1");
        
        assertEquals(0, unread.size());
    }

    @Test
    public void multipleMessages_BetweenDifferentUsers_Test() {
        messageService.sendMessage("user1", "user2", "Hello");
        messageService.sendMessage("user2", "user1", "Hi there");
        messageService.sendMessage("user1", "user3", "Hey");
        messageService.sendMessage("user3", "user1", "Hi");
        
        List<Message> user1Sent = messageService.getSentMessages("user1");
        List<Message> user1Received = messageService.getReceivedMessages("user1");
        
        assertEquals(2, user1Sent.size());
        assertEquals(2, user1Received.size());
    }

    @Test
    public void message_ContainsAllFields_Test() {
        Message message = messageService.sendMessage("sender", "recipient", "Message content");
        
        assertNotNull(message.getId());
        assertNotNull(message.getSenderId());
        assertNotNull(message.getRecipientId());
        assertNotNull(message.getContent());
        assertNotNull(message.getIsRead());
        assertEquals("sender", message.getSenderId());
        assertEquals("recipient", message.getRecipientId());
    }

    @Test
    public void markMultipleMessages_AsRead_Test() {
        Message msg1 = messageService.sendMessage("s1", "r1", "Msg1");
        Message msg2 = messageService.sendMessage("s2", "r1", "Msg2");
        Message msg3 = messageService.sendMessage("s3", "r1", "Msg3");
        
        messageService.markAsRead(msg1.getId());
        messageService.markAsRead(msg3.getId());
        
        List<Message> unread = messageService.getUnreadMessages("r1");
        
        assertEquals(1, unread.size());
        assertEquals(msg2.getId(), unread.get(0).getId());
    }

    @Test
    public void multipleMessages_AreIndependent_Test() {
        Message msg1 = messageService.sendMessage("s1", "r1", "Content1");
        Message msg2 = messageService.sendMessage("s1", "r1", "Content2");
        
        assertNotEquals(msg1.getId(), msg2.getId());
    }

    @Test
    public void sendMessage_LongContent_Test() {
        String longContent = "This is a very long message that contains a lot of text. ".repeat(10);
        Message message = messageService.sendMessage("s1", "r1", longContent);
        
        assertEquals(longContent, message.getContent());
    }

    @Test
    public void getReceivedMessages_FiltersByRecipient_Test() {
        messageService.sendMessage("sender1", "recipient1", "Message 1");
        messageService.sendMessage("sender1", "recipient1", "Message 2");
        messageService.sendMessage("sender1", "recipient2", "Message 3");
        
        List<Message> received = messageService.getReceivedMessages("recipient1");
        
        assertEquals(2, received.size());
    }
}
