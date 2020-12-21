package ru.akirakozov.sd.refactoring.servlet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.akirakozov.sd.refactoring.db.DbManager;
import ru.akirakozov.sd.refactoring.db.DbQueries;
import ru.akirakozov.sd.refactoring.entities.DbProduct;
import ru.akirakozov.sd.refactoring.servlet.query_handler.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class QueryHandlerTest {

    @Mock
    private DbManager dbManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void MaxQueryHandlerTest() throws SQLException {
        MaxQueryHandler handler = new MaxQueryHandler(dbManager);
        when(dbManager.selectProducts(DbQueries.selectFromProductWithMaxPrice()))
                .thenReturn(new ArrayList<>(Collections.singletonList(new DbProduct("maxProduct", 999L))));

        String result = handler.getBodyText();
        String expected = "<h1>Product with max price: </h1>\nmaxProduct\t999</br>\n";
        assertEquals(expected, result);
    }

    @Test
    void MinQueryHandlerTest() throws SQLException {
        MinQueryHandler handler = new MinQueryHandler(dbManager);
        when(dbManager.selectProducts(DbQueries.selectFromProductWithMinPrice()))
                .thenReturn(new ArrayList<>(Collections.singletonList(new DbProduct("minProduct", 1L))));

        String result = handler.getBodyText();
        String expected = "<h1>Product with min price: </h1>\nminProduct\t1</br>\n";
        assertEquals(expected, result);
    }

    @Test
    void SumQueryHandlerTest() throws SQLException {
        SumQueryHandler handler = new SumQueryHandler(dbManager);
        when(dbManager.aggregateProducts(DbQueries.aggregateProductSumPrice()))
                .thenReturn(new ArrayList<>(Collections.singletonList(1984L)));

        String result = handler.getBodyText();
        String expected = "Summary price: \n1984\n";
        assertEquals(expected, result);
    }

    @Test
    void CountQueryHandlerTest() throws SQLException {
        CountQueryHandler handler = new CountQueryHandler(dbManager);
        when(dbManager.aggregateProducts(DbQueries.aggregateProductCount()))
                .thenReturn(new ArrayList<>(Collections.singletonList(10L)));

        String result = handler.getBodyText();
        String expected = "Number of products: \n10\n";
        assertEquals(expected, result);
    }

}