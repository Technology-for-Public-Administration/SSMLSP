package tech.feily.mystar2.ssmlsp.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        System.out
                .println("Welcome to the MyStar2 SSMLSP platform (Social Science Machine Learning Software Package).");
        System.out.println("Current application version : Alpha 0.1");
        System.out.println("This application is licensed through GNU General Public License version 3 (GPLv3).\n");
        System.out.println("Copyright \u00A92019 MyStar2 SSMLSP. All rights reserved.\n");
        System.out.println("Type 'help' or 'h' for help. Type 'exit' to quit application program.\n");
        boolean isExit = false;
        while (true) {
            if (isExit) {
                break;
            }
            System.out.print("SSMLSP > ");
            Scanner in = new Scanner(System.in);
            String cmd = removeSpace(in.nextLine());
            Map<String, String> map = getOptions(cmd);
            switch (map.get("cmd")) {
            case "lg":
                if (map.get("p") != null && map.get("t") != null) {
                    if (map.get("r") != null && !map.get("r").equals("")) {
                        new MyLinearRegression(map.get("p"), map.get("r").split(","), map.get("t"));
                    } else {
                        new MyLinearRegression(map.get("p"), null, map.get("t"));
                    }
                } else {
                    System.err.print("Error : -p and-t parameters cannot be empty.\n");
                }
                break;
            case "h":
            case "help":
                String lg = "1. Linear Regression Command and Parameters.\n" + "    lg -r ... -t ... -p ...";
                String lgDetails = "\n        -r Columns to be excluded, Non negative.\n        -t Target class, Non negative.\n        -p File path.";
                String lgExample = "\n    eg. lg -r 1,2,3 -t 10 -p C:\\Users\\Administrator\\Desktop\\doc\\data.csv";
                //String moreDetails = "\n\n    For mare details, please visit : https://ssmlsp.mystar2.feily.tech";
                System.out.println(lg + lgDetails + lgExample);
                String rt = "2. Regression Tree Model Command and Parameters.\n" + "    rt -r ... -t ... -p ...";
                String rtDetails = "\n        -r Columns to be excluded, Non negative.\n        -t Target class, Non negative.\n        -p File path.";
                String rtExample = "\n    eg. rt -r 1,2,3 -t 10 -p C:\\Users\\Administrator\\Desktop\\doc\\data.csv";
                System.out.println(rt + rtDetails + rtExample);
                String dt = "3. Decision Tree Model Commands and Parameters.\n" + "    dt -r ... -t ... -p ...";
                String dtDetails = "\n        -r Columns to be excluded, Non negative.\n        -t Target class, Non negative.\n        -p File path.";
                String dtExample = "\n    eg. dt -r 1,2,3 -t 10 -p C:\\Users\\Administrator\\Desktop\\doc\\data.csv\n";
                System.out.println(dt + dtDetails + dtExample + "\nFor more details, please visit : https://mystar2.feily.tech/.");
                break;
            case "exit":
                isExit = true;
                break;
            }
        }
        System.out.println("Thanks for your using. Bye.");
    }

    public static String removeSpace(String cmd) {
        String finalCmd = "";
        char[] options = cmd.toCharArray();
        for (char c : options) {
            if (c != ' ') {
                finalCmd += c;
            }
        }
        return finalCmd;
    }

    public static Map<String, String> getOptions(String cmd) {
        Map<String, String> map = new HashMap<String, String>();
        String[] parts = cmd.split("-");
        map.put("cmd", parts[0]);
        for (int i = 1; i < parts.length; i++) {
            switch (parts[i].toCharArray()[0]) {
            case 'r':
                map.put("r", parts[i].substring(1));
                break;
            case 't':
                map.put("t", parts[i].substring(1));
                break;
            case 'p':
                map.put("p", parts[i].substring(1));
                break;
            }
        }
        return map;
    }
}
