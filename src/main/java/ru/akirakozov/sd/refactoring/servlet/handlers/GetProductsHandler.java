package ru.akirakozov.sd.refactoring.servlet.handlers;

import ru.akirakozov.sd.refactoring.db.DbManager;
import ru.akirakozov.sd.refactoring.db.DbQueries;
import ru.akirakozov.sd.refactoring.entities.DbProduct;

import java.util.ArrayList;

public class GetProductsHandler implements IQueryHandler {

    private final DbManager dbManager;

    public GetProductsHandler(DbManager manager) {
        dbManager = manager;
    }

    @Override
    public String getBodyText() throws RuntimeException {
        StringBuilder bodyText = new StringBuilder();
        bodyText.append("<h1>All existed products: </h1>").append("\n");

        try {
            ArrayList<DbProduct> products = dbManager.selectProducts(DbQueries.selectAllFromProduct());
            for (DbProduct product: products) {
                bodyText.append(product.getName()).append("\t").append(product.getPrice()).append("</br>").append("\n");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return bodyText.toString();
    }

}
