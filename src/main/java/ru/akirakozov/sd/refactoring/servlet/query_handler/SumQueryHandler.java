package ru.akirakozov.sd.refactoring.servlet.query_handler;

import ru.akirakozov.sd.refactoring.db.DbManager;
import ru.akirakozov.sd.refactoring.db.DbQueries;

import java.util.ArrayList;

public class SumQueryHandler implements IQueryHandler {

    @Override
    public String getBodyText() throws RuntimeException {
        StringBuilder bodyText = new StringBuilder();
        bodyText.append("Summary price: ").append("\n");

        try {
            ArrayList<Long> results = DbManager.aggregateProducts(DbQueries.aggregateProductSumPrice());
            for (Long result : results) {
                bodyText.append(result).append("\n");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return bodyText.toString();
    }

}
