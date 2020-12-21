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
public class GetProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            response.getWriter().println("<html><body>");

            ArrayList<DbProduct> products = DbManager.selectProducts(DbQueries.selectAllFromProduct());
            for (DbProduct product: products) {
                response.getWriter().println(product.getName() + "\t" + product.getPrice() + "</br>");
            }

            response.getWriter().println("</body></html>");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
