package com.company.entities.validations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @user LuisTroya
 * @date 22/Feb/2017
 */
public class MessageDTO {
    public enum MessageType {
        SUCCESS, INFO, WARNING, ERROR
    }

    private List<MessageBody> messages;
    private MessageType type;

    public MessageDTO(MessageType type, List<MessageBody> messages) {
        this.messages = messages;
        this.type = type;
    }

    public MessageDTO(MessageType type, MessageBody message) {
        this.messages = Collections.singletonList(message);
        this.type = type;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public List<MessageBody> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageBody> messages) {
        this.messages = messages;
    }
}
