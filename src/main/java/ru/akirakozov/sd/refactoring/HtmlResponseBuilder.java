package ru.akirakozov.sd.refactoring;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HtmlResponseBuilder {

    private HttpServletResponse response;

    public HtmlResponseBuilder(HttpServletResponse response) {
        this.response = response;
    }

    public void fillBodyWithText(String body) throws IOException {
        response.setContentType("text/html");

        response.getWriter().println("<html><body>");
        String[] rows = body.split("\\r?\\n");
        for (String row: rows) {
            response.getWriter().println(row);
        }
        response.getWriter().println("</body></html>");

        response.setStatus(HttpServletResponse.SC_OK);
    }

}
