package utils;

import java.io.File;

import static utils.StringUtil.capitalize;

public class ComponentCommandUtil {

    public static String componentNameToClassName(String componentName) {
        String[] words = componentName.split("-");
        StringBuilder componentClassName = new StringBuilder();
        for (String word : words) {
            componentClassName.append(capitalize(word));
        }
        componentClassName.append("Component");
        return componentClassName.toString();
    }

    public static void updateModuleFile(File componentDir, String componentName, String componentImportPath) {

        if (componentDir.isDirectory()) {
            File[] moduleFileList = componentDir.listFiles((subFile) -> {
                String subFileName = subFile.getName();
                if (subFileName.endsWith("-routing.module.ts")) {
                    return false;
                } else if (subFileName.endsWith("module.ts")) {
                    return true;
                }
                return false;
            });
            if (moduleFileList.length > 0) {
                File moduleFile = moduleFileList[0];

                String componentClassName = componentNameToClassName(componentName);
                String completeComponentImportPath = "./" + componentImportPath + componentName + ".component";
                String componentImport = "import { " + componentClassName + " } from '" + completeComponentImportPath + "';\n";

                String moduleContent = FileUtil.readFile(moduleFile);
                moduleContent = componentImport + moduleContent;
                moduleContent = moduleContent.replaceFirst("declarations:[\\s]{0,}\\[", "declarations: [\n\t" + componentClassName + ",");
                FileUtil.writeFile(moduleFile, moduleContent);
                System.out.println("UPDATE " + moduleFile.getPath());
                return;
            }
        }
        String newComponentImportPath = componentDir.getName() + componentImportPath;
        updateModuleFile(componentDir.getParentFile(), componentName, newComponentImportPath);
    }

    public static String getDefaultHtmlContent(String componentName) {
        return "<p>" + componentName + " works!</p>\n";
    }

    public static String getDefaultTsContent(String componentName) {
        String selectorName = "app-" + componentName;
        String htmlFilePath = "./" + componentName + ".component.html";
        String scssFilePath = "./" + componentName + ".component.scss";
        String className = componentNameToClassName(componentName);

        return "import { Component } from '@angular/core';\n" + "\n" + "@Component({\n" + "  selector: '" + selectorName + "',\n" + "  templateUrl: '" + htmlFilePath + "',\n" + "  styleUrls: ['" + scssFilePath + "']\n" + "})\n" + "export class " + className + " {\n" + "\n" + "}\n";
    }
}
