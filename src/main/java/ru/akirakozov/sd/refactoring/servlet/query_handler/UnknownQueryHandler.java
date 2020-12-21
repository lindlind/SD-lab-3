package ru.akirakozov.sd.refactoring.servlet.query_handler;

public class UnknownQueryHandler implements IQueryHandler {

    private String wrongCommand;

    public UnknownQueryHandler(String command) {
        wrongCommand = command;
    }

    @Override
    public String getBodyText() {
        return "Unknown command: " + wrongCommand + "\n";
    }

}
