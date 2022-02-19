package org.ssau.privatechannel.db_installation;

import org.ssau.privatechannel.db_installation.utils.ScriptsRunner;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> arguments = Objects.isNull(args)?null:Arrays.asList(args);
        try {
            ScriptsRunner.runDockerPostgresInit(arguments);
        } catch (Exception e) {}
    }
}
