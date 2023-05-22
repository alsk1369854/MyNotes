package commandHandlers;

import utils.ComponentCommandUtil;
import utils.FileUtil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ComponentCommandHandler {
    public static final String OPTION = "c";

    public static void createComponent(String dirPath) {
        char tailChart = dirPath.charAt(dirPath.length() - 1);
        if (tailChart == '\\' || tailChart == '/') {
            System.out.println(getHelpMessage());
            return;
        }

        String[] paths = dirPath.split("[\\,/]");
        String componentName = paths[paths.length - 1];

        try {
            String htmlFilePath = dirPath + "/" + componentName + ".component.html";
            File htmlFile = FileUtil.createFile(htmlFilePath);
            String htmlContent = ComponentCommandUtil.getDefaultHtmlContent(componentName);
            FileUtil.writeFile(htmlFile, htmlContent);
            System.out.println("CREATE " + htmlFile.getPath());

            String tsFilePath = dirPath + "/" + componentName + ".component.ts";
            File tsFile = FileUtil.createFile(tsFilePath);
            String tsContent = ComponentCommandUtil.getDefaultTsContent(componentName);
            FileUtil.writeFile(tsFile, tsContent);
            System.out.println("CREATE " + tsFile.getPath());

            String scssFilePath = dirPath + "/" + componentName + ".component.scss";
            File scssFile = FileUtil.createFile(scssFilePath);
            System.out.println("CREATE " + scssFile.getPath());

            ComponentCommandUtil.updateModuleFile(tsFile.getParentFile(), componentName, "/");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(getHelpMessage());
        }

    }

    public static String getHelpMessage() {
        return "ngg c <component-directory-path>\t\tcreate a angular component";
    }
}
