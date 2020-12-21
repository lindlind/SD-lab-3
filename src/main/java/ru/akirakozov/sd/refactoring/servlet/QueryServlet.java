package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.HtmlResponseBuilder;
import ru.akirakozov.sd.refactoring.db.DbManager;
import ru.akirakozov.sd.refactoring.db.DbQueries;
import ru.akirakozov.sd.refactoring.entities.DbProduct;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder bodyText = new StringBuilder();
        String command = request.getParameter("command");

        if ("max".equals(command)) {
            try {
                bodyText.append("<h1>Product with max price: </h1>").append("\n");

                ArrayList<DbProduct> products = DbManager.selectProducts(DbQueries.selectFromProductWithMaxPrice());
                for (DbProduct product: products) {
                    bodyText.append(product.getName()).append("\t").append(product.getPrice()).append("</br>").append("\n");
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("min".equals(command)) {
            try {
                bodyText.append("<h1>Product with min price: </h1>").append("\n");

                ArrayList<DbProduct> products = DbManager.selectProducts(DbQueries.selectFromProductWithMinPrice());
                for (DbProduct product: products) {
                    bodyText.append(product.getName()).append("\t").append(product.getPrice()).append("</br>").append("\n");
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("sum".equals(command)) {
            try {
                bodyText.append("Summary price: ").append("\n");

                ArrayList<Long> results = DbManager.aggregateProducts(DbQueries.aggregateProductSumPrice());
                for (Long result: results) {
                    bodyText.append(result).append("\n");
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("count".equals(command)) {
            try {
                bodyText.append("Number of products: ").append("\n");

                ArrayList<Long> results = DbManager.aggregateProducts(DbQueries.aggregateProductCount());
                for (Long result: results) {
                    bodyText.append(result).append("\n");
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            bodyText.append("Unknown command: ").append(command).append("\n");
        }

        new HtmlResponseBuilder(response).fillBodyWithText(bodyText.toString());
    }

}
