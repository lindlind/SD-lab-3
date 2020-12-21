package ru.akirakozov.sd.refactoring.db;

import ru.akirakozov.sd.refactoring.entities.DbProduct;
import java.sql.*;
import java.util.ArrayList;

public class DbManager {

    private final String url;

    public DbManager() {
        this("jdbc:sqlite:mainDb.db");
    }

    public DbManager(String url) {
        this.url = url;
    }

    public void initProductTable() throws SQLException {
        try (Connection c = DriverManager.getConnection(url)) {
            Statement stmt = c.createStatement();
            stmt.executeUpdate(DbQueries.createProductTable());
            stmt.close();
        }
    }

    public void insertProduct(String name, Long price) throws SQLException {
        try (Connection c = DriverManager.getConnection(url)) {
            Statement stmt = c.createStatement();
            stmt.executeUpdate(DbQueries.insertIntoProduct(name, price));
            stmt.close();
        }
    }

    public ArrayList<DbProduct> selectProducts(String query) throws SQLException {
        try (Connection c = DriverManager.getConnection(url)) {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<DbProduct> products = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name");
                long price = rs.getLong("price");
                products.add(new DbProduct(name, price));
            }

            rs.close();
            stmt.close();

            return products;
        }
    }

    public ArrayList<Long> aggregateProducts(String query) throws SQLException {
        try (Connection c = DriverManager.getConnection(url)) {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<Long> results = new ArrayList<>();
            if (rs.next()) {
                long result = rs.getLong(1);
                results.add(result);
            }

            rs.close();
            stmt.close();

            return results;
        }
    }

}
