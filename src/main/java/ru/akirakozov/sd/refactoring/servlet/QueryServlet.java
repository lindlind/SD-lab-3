package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.HtmlResponseBuilder;
import ru.akirakozov.sd.refactoring.db.DbManager;
import ru.akirakozov.sd.refactoring.servlet.handlers.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {

    private final DbManager dbManager;

    public QueryServlet(DbManager manager) {
        dbManager = manager;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        IQueryHandler handler;
        switch (command) {
            case "max":
                handler = new MaxQueryHandler(dbManager);
                break;
            case "min":
                handler = new MinQueryHandler(dbManager);
                break;
            case "sum":
                handler = new SumQueryHandler(dbManager);
                break;
            case "count":
                handler = new CountQueryHandler(dbManager);
                break;
            default:
                handler = new UnknownQueryHandler(command);
        }

        String bodyText = handler.getBodyText();
        new HtmlResponseBuilder(response).fillBodyWithText(bodyText);
    }

}
