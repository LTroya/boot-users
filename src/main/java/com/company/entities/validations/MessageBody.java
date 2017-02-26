package com.company.entities.validations;

/**
 * @user LuisTroya
 * @date 26/Feb/2017
 */
public class MessageBody {
    /**
     * message.properties's key
     */
    private String key;

    /**
     * message.properties's value
     */
    private String message;

    public MessageBody(String key, String message) {
        this.key = key;
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
