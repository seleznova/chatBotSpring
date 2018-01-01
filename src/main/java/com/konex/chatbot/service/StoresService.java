package com.konex.chatbot.service;

import com.konex.chatbot.model.Goods;
import com.konex.chatbot.model.Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StoresService {
    public List<Store> selectByGoodsName(String name) {
        List<Store> storeList = new ArrayList<Store>();

        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:drug_store.sqlite");
            c.setAutoCommit(false);

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM store AS s WHERE s.id IN (" +
                    "SELECT gs.id_store FROM goods_store AS gs WHERE gs.id_goods IN (" +
                    "SELECT g.id FROM goods AS g WHERE g.name LIKE '%" + name.toLowerCase() + "%'" +
                    ")" +
                    ");");

            while (rs.next()) {
                Store store = new Store();
                store.setId(rs.getInt("id"));
                store.setName(rs.getString("name"));
                store.setAddress(rs.getString("address"));
                storeList.add(store);
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            return storeList;
        }
    }

    public List<Store> selectByGoodsId(Long id) {
        List<Store> storeList = new ArrayList<Store>();

        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:drug_store.sqlite");
            c.setAutoCommit(false);

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM store AS s WHERE s.id IN (" +
                    "SELECT gs.id_store FROM goods_store AS gs WHERE gs.id_goods IN (" +
                    "SELECT g.id FROM goods AS g WHERE g.id =" + id +
                    ")" +
                    ");");

            while (rs.next()) {
                Store store = new Store();
                store.setId(rs.getInt("id"));
                store.setName(rs.getString("name"));
                store.setAddress(rs.getString("address"));
                storeList.add(store);
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            return storeList;
        }
    }

    public void insert(Goods goods) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:drug_store.sqlite");
            c.setAutoCommit(false);
//            System.out.println("Opened database successfully");

            Statement stmt = c.createStatement();
            String sql = "INSERT INTO goods (name, price) " +
                    "VALUES (" + goods.getName() + "," + goods.getPrice() + ");";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
//        System.out.println("Records created successfully");
    }

    public void update(Goods goods) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:drug_store.sqlite");
            c.setAutoCommit(false);
//            System.out.println("Opened database successfully");

            Statement stmt = c.createStatement();
            String sql = "UPDATE goods set name=" + goods.getName() + ", price=" + goods.getPrice() + " where ID=" + goods.getId() + ";";
            stmt.executeUpdate(sql);
            c.commit();

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
//        System.out.println("Operation done successfully");
    }

    public void delete(Goods goods) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:drug_store.sqlite");
            c.setAutoCommit(false);
//            System.out.println("Opened database successfully");

            Statement stmt = c.createStatement();
            String sql = "DELETE from goods where ID=" + goods.getId() + ";";
            stmt.executeUpdate(sql);
            c.commit();

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
//        System.out.println("Operation done successfully");
    }
}
