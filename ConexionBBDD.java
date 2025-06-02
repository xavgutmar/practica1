package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDD {
    private static final String URL = "jdbc:mysql://192.168.139:3306/";
    private static final String USER = "xavi";
    private static final String PASS = "10789228x!";
    private static Connection conexion = null;

    public ConexionBBDD() {
        try {
            conexion = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            Utils.showErrors("Ha habido un error al conectarse a la base de datos.", e);
        }
    }

    public static Connection getConnection() {
        if (conexion == null) {
            new ConexionBBDD();
        }

        return conexion;
    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            Utils.showErrors("Ha habido un error en el inicializador.", e);
        }
    }
}
