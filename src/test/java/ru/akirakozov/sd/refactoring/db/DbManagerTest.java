package ru.akirakozov.sd.refactoring.db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.akirakozov.sd.refactoring.entities.DbProduct;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DbManagerTest {

    private final String TEST_DB_URL = "jdbc:sqlite:testDb.db";
    private DbManager dbManager = new DbManager(TEST_DB_URL);

    private void clearProductTable() throws SQLException {
        try(Connection c= DriverManager.getConnection(TEST_DB_URL)) {
            Statement stmt=c.createStatement();
            stmt.executeUpdate("DELETE FROM PRODUCT");
            stmt.close();
        }
    }

    @BeforeEach
    void setUp() throws SQLException {
        dbManager.initProductTable();
    }

    @AfterEach
    void tearDown() throws SQLException {
        clearProductTable();
    }

    @Test
    void selectAllProductsEmptyTable() throws SQLException {
        ArrayList<DbProduct> products = dbManager.selectProducts(DbQueries.selectAllFromProduct());
        DbProduct[] expected = {};
        assertArrayEquals(expected, products.toArray());
    }

    @Test
    void selectMaxProductEmptyTable() throws SQLException {
        ArrayList<DbProduct> products = dbManager.selectProducts(DbQueries.selectFromProductWithMaxPrice());
        DbProduct[] expected = {};
        assertArrayEquals(expected, products.toArray());
    }

    @Test
    void selectMinProductEmptyTable() throws SQLException {
        ArrayList<DbProduct> products = dbManager.selectProducts(DbQueries.selectFromProductWithMinPrice());
        DbProduct[] expected = {};
        assertArrayEquals(expected, products.toArray());
    }

    @Test
    void countProductsEmptyTable() throws SQLException {
        ArrayList<Long> products = dbManager.aggregateProducts(DbQueries.aggregateProductCount());
        Long[] expected = {0L};
        assertArrayEquals(expected, products.toArray());
    }

    @Test
    void sumPriceProductsEmptyTable() throws SQLException {
        ArrayList<Long> products = dbManager.aggregateProducts(DbQueries.aggregateProductSumPrice());
        Long[] expected = {0L};
        assertArrayEquals(expected, products.toArray());
    }
    @Test
    void selectAllProductsSmallTable() throws SQLException {
        dbManager.insertProduct("p1", 123L);
        dbManager.insertProduct("p2", 654L);
        ArrayList<DbProduct> products = dbManager.selectProducts(DbQueries.selectAllFromProduct());
        DbProduct[] expected = {new DbProduct("p1", 123L), new DbProduct("p2", 654L)};
        assertArrayEquals(expected, products.toArray());
    }

    @Test
    void selectMaxProductSmallTable() throws SQLException {
        dbManager.insertProduct("p1", 123L);
        dbManager.insertProduct("p2", 654L);
        ArrayList<DbProduct> products = dbManager.selectProducts(DbQueries.selectFromProductWithMaxPrice());
        DbProduct[] expected = {new DbProduct("p2", 654L)};
        assertArrayEquals(expected, products.toArray());
    }

    @Test
    void selectMinProductSmallTable() throws SQLException {
        dbManager.insertProduct("p1", 123L);
        dbManager.insertProduct("p2", 654L);
        ArrayList<DbProduct> products = dbManager.selectProducts(DbQueries.selectFromProductWithMinPrice());
        DbProduct[] expected = {new DbProduct("p1", 123L)};
        assertArrayEquals(expected, products.toArray());
    }

    @Test
    void countProductsSmallTable() throws SQLException {
        dbManager.insertProduct("p1", 123L);
        dbManager.insertProduct("p2", 654L);
        ArrayList<Long> products = dbManager.aggregateProducts(DbQueries.aggregateProductCount());
        Long[] expected = {2L};
        assertArrayEquals(expected, products.toArray());
    }

    @Test
    void sumPriceProductsSmallTable() throws SQLException {
        dbManager.insertProduct("p1", 123L);
        dbManager.insertProduct("p2", 654L);
        ArrayList<Long> products = dbManager.aggregateProducts(DbQueries.aggregateProductSumPrice());
        Long[] expected = {777L};
        assertArrayEquals(expected, products.toArray());
    }
}