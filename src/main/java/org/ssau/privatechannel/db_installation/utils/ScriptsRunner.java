package org.ssau.privatechannel.db_installation.utils;

import org.ssau.privatechannel.db_installation.model.DbInitParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public abstract class ScriptsRunner {

    private static Map<String, String> parameters;
    private static final String SETTINGS_FILE_PARAM = "settingsFile";

    public static void runDockerPostgresInit(List<String> args) throws IOException {
        parseParameters(args);
        if (Objects.isNull(parameters) || !isParameterProvided(SETTINGS_FILE_PARAM)) {
            printMessage("Settings JSON file not specified");
            printMessage("Please run application with parameter settingsFile");
            printMessage("Example: java -jar program.jar -settingsFile=test.json");
            return;
        }

        if (!isParametersValid()) {
            return;
        }

        List<String> commandsSequence = getCommandSequence();
        for (String command: commandsSequence) {
            runCommand(command);
        }
    }

    private static void parseParameters(List<String> args) throws IOException {
        if (Objects.isNull(args) || args.size() == 0) {
            return;
        }
        Map<String, String> result = new HashMap<>();
        for (String arg : args) {
            if (arg.charAt(0) != '-') {
                printMessage("Bad input: " + arg +  "; parameters must be started with \'-\'");
                throw new IllegalArgumentException("Bad input: " + arg);
            }
            arg = arg.substring(1);
            try {
                String[] argNameAndValue = arg.split("=");
                result.put(argNameAndValue[0], argNameAndValue[1]);
            }
            catch (Exception e) {
                printMessage("Bad input: " + arg +  "; parameters must have value");
                throw new IllegalArgumentException("Bad input: " + arg);
            }
        }
        parameters = result;
    }

    private static boolean isParametersValid() throws IOException {
        List<String> allowedParams = getWhiteListParams();
        for (String argName : parameters.keySet()) {
            if (!allowedParams.contains(argName)) {
                printMessage("Wrong parameter: " + argName);
                return false;
            }
        }
        return true;
    }

    private static List<String> getWhiteListParams() {
        return Arrays.asList(SETTINGS_FILE_PARAM);
    }

    private static List<String> getCommandSequence() throws IOException {
        DbInitParams params = JsonParametersLoader.getParams(parameters.get(SETTINGS_FILE_PARAM));
        return new ArrayList<>(){{
            add("docker pull postgres");
            add(DockerCommandsProvider.getCreateContainerCommand(params));
        }};
    }

    private static void runCommand(String command) throws IOException {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", command);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        }
    }

    private static void printMessage(String message) throws IOException {
        runCommand("echo " + message);
    }

    private static boolean isParameterProvided(String paramName) {
        return parameters.containsKey(paramName) && !parameters.get(paramName).isEmpty();
    }
}
