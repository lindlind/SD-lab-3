package ru.akirakozov.sd.refactoring.servlet.query_handler;

import ru.akirakozov.sd.refactoring.db.DbManager;
import ru.akirakozov.sd.refactoring.db.DbQueries;
import ru.akirakozov.sd.refactoring.entities.DbProduct;

import java.util.ArrayList;

public class MinQueryHandler implements IQueryHandler {

    @Override
    public String getBodyText() throws RuntimeException {
        StringBuilder bodyText = new StringBuilder();
        bodyText.append("<h1>Product with min price: </h1>").append("\n");

        try {
            ArrayList<DbProduct> products = new DbManager().selectProducts(DbQueries.selectFromProductWithMinPrice());
            for (DbProduct product: products) {
                bodyText.append(product.getName()).append("\t").append(product.getPrice()).append("</br>").append("\n");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return bodyText.toString();
    }

}
