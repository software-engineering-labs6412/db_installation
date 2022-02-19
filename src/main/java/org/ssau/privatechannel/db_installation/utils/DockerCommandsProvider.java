package org.ssau.privatechannel.db_installation.utils;

import org.ssau.privatechannel.db_installation.model.DbInitParams;

public class DockerCommandsProvider {

    private static final String IMAGE_NAME = "postgres";

    public static String getCreateContainerCommand(DbInitParams params) {
        return String.format("docker run " +
                "--name %s " +
                "-e POSTGRES_USER=%s " +
                "-e POSTGRES_PASSWORD=%s " +
                "-e POSTGRES_DB=%s " +
                "-p %s:5432 %s",
                params.getInstanceName(),
                params.getUser(),
                params.getPassword(),
                params.getDb(),
                params.getPort(),
                IMAGE_NAME);
    }
}
