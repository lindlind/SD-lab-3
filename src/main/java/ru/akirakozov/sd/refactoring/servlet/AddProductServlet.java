package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.HtmlResponseBuilder;
import ru.akirakozov.sd.refactoring.db.DbManager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akirakozov
 */
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        long price = Long.parseLong(request.getParameter("price"));

        try {
            DbManager.insertProduct(name, price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        new HtmlResponseBuilder(response).fillBodyWithText("OK");
    }
}
