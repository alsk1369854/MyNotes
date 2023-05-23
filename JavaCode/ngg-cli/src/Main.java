import commandHandlers.HelpCommandHandler;
import commandHandlers.ComponentCommandHandler;
import commandHandlers.ServiceCommandHandler;

public class Main {
    public static void main(String[] args) {

        Integer argsLen = args.length;
        if (argsLen < 2) {
            HelpCommandHandler.printHelpMessage();
            return;
        }

        String option = args[0];
        String dirPath = args[1];

        switch (option) {
            case ComponentCommandHandler.OPTION:
                ComponentCommandHandler.createComponent(dirPath);
                break;

            case ServiceCommandHandler.OPTION:
                ServiceCommandHandler.createService(dirPath);
                break;

            case HelpCommandHandler.OPTION:
            default:
                HelpCommandHandler.printHelpMessage();
        }
    }
}