package com.konex.chatbot.model;

import java.util.List;

public class Response {
    private String message;
    private boolean isUser;
    private boolean isSelect;
    private List<String> options;

    public Response() {
    }

    public Response(String message) {
        this.message = message;
        this.isUser = false;
        this.isSelect = false;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
