package org.ssau.privatechannel.db_installation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ssau.privatechannel.db_installation.model.DbInitParams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonParametersLoader {
    public static DbInitParams getParams(String fileName) throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(fileName)));
        return new ObjectMapper().readValue(json, DbInitParams.class);
    }
}
