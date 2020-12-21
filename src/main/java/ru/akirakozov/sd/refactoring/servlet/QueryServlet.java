package ru.akirakozov.sd.refactoring.servlet;

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
        String command = request.getParameter("command");

        if ("max".equals(command)) {
            try {
                response.getWriter().println("<html><body>");
                response.getWriter().println("<h1>Product with max price: </h1>");

                ArrayList<DbProduct> products = DbManager.selectProducts(DbQueries.selectFromProductWithMaxPrice());
                for (DbProduct product: products) {
                    response.getWriter().println(product.getName() + "\t" + product.getPrice() + "</br>");
                }

                response.getWriter().println("</body></html>");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("min".equals(command)) {
            try {
                response.getWriter().println("<html><body>");
                response.getWriter().println("<h1>Product with min price: </h1>");

                ArrayList<DbProduct> products = DbManager.selectProducts(DbQueries.selectFromProductWithMinPrice());
                for (DbProduct product: products) {
                    response.getWriter().println(product.getName() + "\t" + product.getPrice() + "</br>");
                }

                response.getWriter().println("</body></html>");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("sum".equals(command)) {
            try {
                response.getWriter().println("<html><body>");
                response.getWriter().println("Summary price: ");

                ArrayList<Long> results = DbManager.aggregateProducts(DbQueries.aggregateProductSumPrice());
                for (Long result: results) {
                    response.getWriter().println(result);
                }

                response.getWriter().println("</body></html>");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("count".equals(command)) {
            try {
                response.getWriter().println("<html><body>");
                response.getWriter().println("Number of products: ");

                ArrayList<Long> results = DbManager.aggregateProducts(DbQueries.aggregateProductCount());
                for (Long result: results) {
                    response.getWriter().println(result);
                }

                response.getWriter().println("</body></html>");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            response.getWriter().println("Unknown command: " + command);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
