package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.HtmlResponseBuilder;
import ru.akirakozov.sd.refactoring.servlet.handlers.GetProductsHandler;
import ru.akirakozov.sd.refactoring.servlet.handlers.IQueryHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        IQueryHandler handler = new GetProductsHandler();
        String bodyText = handler.getBodyText();
        new HtmlResponseBuilder(response).fillBodyWithText(bodyText);
    }
}
