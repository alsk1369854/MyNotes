package commandHandlers;

import utils.ComponentCommandUtil;
import utils.FileUtil;
import utils.ServiceCommandUtil;

import java.io.File;
import java.io.IOException;

public class ServiceCommandHandler {
    public static final String OPTION = "s";


    public static void createService(String dirPath) {
        char tailChart = dirPath.charAt(dirPath.length() - 1);
        if (tailChart == '\\' || tailChart == '/') {
            System.out.println(getHelpMessage());
            return;
        }

        String[] paths = dirPath.split("[\\,/]");
        String serviceName = paths[paths.length - 1];
        StringBuilder serviceDir = new StringBuilder();
        for (int i = 0; i < paths.length - 1; i++) {
            String path = paths[i];
            serviceDir.append(path + "/");
        }

        try {
            String tsFilePath = serviceDir.toString() + serviceName + ".service.ts";
            File tsFile = FileUtil.createFile(tsFilePath);
            String tsContent = ServiceCommandUtil.getDefaultTsContent(serviceName);
            FileUtil.writeFile(tsFile, tsContent);
            System.out.println("CREATE " + tsFile.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getHelpMessage() {
        return "ngg s <service-path>\t\t\tcreate a angular service\n";
    }
}
