package org.ssau.privatechannel.db_installation.model;

import java.util.HashMap;
import java.util.Map;

public class DbInitParams {
    private String instanceName;
    private String user;
    private String password;
    private String db;
    private String port;

    public DbInitParams(String instanceName, String user, String password, String db, String port) {
        this.instanceName = instanceName;
        this.user = user;
        this.password = password;
        this.db = db;
        this.port = port;
    }

    public DbInitParams() {
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
