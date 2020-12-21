package ru.akirakozov.sd.refactoring.servlet.handlers;

import ru.akirakozov.sd.refactoring.db.DbManager;
import ru.akirakozov.sd.refactoring.db.DbQueries;

import java.util.ArrayList;

public class SumQueryHandler implements IQueryHandler {

    private final DbManager dbManager;

    public SumQueryHandler(DbManager manager) {
        dbManager = manager;
    }

    @Override
    public String getBodyText() throws RuntimeException {
        StringBuilder bodyText = new StringBuilder();
        bodyText.append("Summary price: ").append("\n");

        try {
            ArrayList<Long> results = dbManager.aggregateProducts(DbQueries.aggregateProductSumPrice());
            for (Long result : results) {
                bodyText.append(result).append("\n");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return bodyText.toString();
    }

}
