package com.konex.chatbot.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectToDB {

    public Connection ConnectToDB() {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:drug_store.db");
//            System.out.println("Opened database successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            return c;
        }
    }
}
