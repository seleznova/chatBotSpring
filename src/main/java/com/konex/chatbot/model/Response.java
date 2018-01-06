package com.konex.chatbot.model;

import java.util.Map;

public class Response {
    private String message;
    private String template;
    private boolean isUser;
    private boolean isSelect;
    private Map<String, String> options;

    public Response() {
    }

    public Response(String message) {
        this.message = message;
        this.isUser = false;
        this.isSelect = false;
    }

    public Response(String message, boolean isUser) {
        this.message = message;
        this.isUser = isUser;
        this.isSelect = false;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
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

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }
}
