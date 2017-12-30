package com.konex.chatbot.model;

public class Store {
    private int id;
    private  String name;
    private String address;

    public Store(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Store(String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Store(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
