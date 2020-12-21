package ru.akirakozov.sd.refactoring.servlet.query_handler;

import ru.akirakozov.sd.refactoring.db.DbManager;
import ru.akirakozov.sd.refactoring.db.DbQueries;

import java.util.ArrayList;

public class CountQueryHandler implements IQueryHandler {

    private final DbManager dbManager;

    public CountQueryHandler() {
        this(new DbManager());
    }

    public CountQueryHandler(DbManager manager) {
        dbManager = manager;
    }

    @Override
    public String getBodyText() throws RuntimeException {
        StringBuilder bodyText = new StringBuilder();
        bodyText.append("Number of products: ").append("\n");

        try {
            ArrayList<Long> results = dbManager.aggregateProducts(DbQueries.aggregateProductCount());
            for (Long result: results) {
                bodyText.append(result).append("\n");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return bodyText.toString();
    }

}
