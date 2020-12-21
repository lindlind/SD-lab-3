package ru.akirakozov.sd.refactoring.servlet.handlers;

public class UnknownQueryHandler implements IQueryHandler {

    private final String wrongCommand;

    public UnknownQueryHandler(String command) {
        wrongCommand = command;
    }

    @Override
    public String getBodyText() {
        return "Unknown command: " + wrongCommand + "\n";
    }

}
