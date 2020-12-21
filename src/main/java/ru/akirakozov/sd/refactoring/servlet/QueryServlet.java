package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.HtmlResponseBuilder;
import ru.akirakozov.sd.refactoring.servlet.handlers.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        IQueryHandler handler;
        switch (command) {
            case "max":
                handler = new MaxQueryHandler();
                break;
            case "min":
                handler = new MinQueryHandler();
                break;
            case "sum":
                handler = new SumQueryHandler();
                break;
            case "count":
                handler = new CountQueryHandler();
                break;
            default:
                handler = new UnknownQueryHandler(command);
        }

        String bodyText = handler.getBodyText();
        new HtmlResponseBuilder(response).fillBodyWithText(bodyText);
    }

}
