package commandHandlers;


public class HelpCommandHandler {
    public static String getHelpMessage() {
        return ComponentCommandHandler.getHelpMessage() +
                ServiceCommandHandler.getHelpMessage();
    }
}
