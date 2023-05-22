package utils;

import static utils.StringUtil.capitalize;

public class ServiceCommandUtil {

    public static String serviceNameToClassName(String serviceName) {
        String[] words = serviceName.split("-");
        StringBuilder componentClassName = new StringBuilder();
        for (String word : words) {
            componentClassName.append(capitalize(word));
        }
        componentClassName.append("Service");
        return componentClassName.toString();
    }

    public static String getDefaultTsContent(String serviceName) {
        String serviceClassName = serviceNameToClassName(serviceName);
        return "import { Injectable } from '@angular/core';\n" +
                "\n" +
                "@Injectable({\n" +
                "  providedIn: 'root'\n" +
                "})\n" +
                "export class " + serviceClassName + " {\n" +
                "\n" +
                "  constructor() { }\n" +
                "}\n";
    }
}
