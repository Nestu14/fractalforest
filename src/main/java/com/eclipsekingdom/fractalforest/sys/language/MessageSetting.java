package com.eclipsekingdom.fractalforest.sys.language;

public class MessageSetting {

    private String label;
    private String message;

    public MessageSetting(String label, String message) {
        this.label = label;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLabel() {
        return label;
    }

    public String getMessage() {
        return message;
    }

}

