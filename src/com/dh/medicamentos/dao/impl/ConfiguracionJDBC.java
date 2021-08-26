package com.dh.medicamentos.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfiguracionJDBC {
    private String jdbcDriver;
    private String url;
    private String user;
    private String password;


    public ConfiguracionJDBC(String jdbcDriver, String url, String user, String password) {
        this.jdbcDriver = jdbcDriver;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public ConfiguracionJDBC() {
        this.jdbcDriver = "org.h2.Driver";
        this.url = "jdbc:h2:~/test; INIT = RUNSCRIPT FROM 'create.sql'";
        this.user = "sa";
        this.password = null;
    }

    public Connection conectarConBaseDeDatos(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}

