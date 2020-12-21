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
public class GetProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder bodyText = new StringBuilder();

        try {
            ArrayList<DbProduct> products = new DbManager().selectProducts(DbQueries.selectAllFromProduct());
            for (DbProduct product: products) {
                bodyText.append(product.getName()).append("\t").append(product.getPrice()).append("</br>").append("\n");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        new HtmlResponseBuilder(response).fillBodyWithText(bodyText.toString());
    }
}
