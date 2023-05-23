package commandHandlers;


public class HelpCommandHandler {
    public static final String OPTION = "-h";

    public static void printHelpMessage() {
        String helpMessage = getHelpMessage() +
                ComponentCommandHandler.getHelpMessage() +
                ServiceCommandHandler.getHelpMessage();
        System.out.println(helpMessage);
    }

    public static String getHelpMessage() {
        return "ngg -h\t\t\t\t\tcommand help info\n";
    }
}
