package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            final Connection CONNECTION = ConexionBBDD.getConnection();
            System.out.println("Conexion establecida con exito" + (CONNECTION != null));
            final Statement STATEMENT = CONNECTION.createStatement();

            final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS Alumnos (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nombre VARCHAR(100)," +
                "edad INT(2)" +
                ");";

            STATEMENT.execute(CREATE_TABLE_SQL);

            final String INSERT_SQL = "INSERT INTO Alumnos (nombre,edad) VALUES" +
                "('Álvaro', 18)," +
                "('Núria', 22)," +
                "('Eduardo', 25);";

            STATEMENT.execute(INSERT_SQL);

            Utils.getBBDD();
        } catch (SQLException e) {
            Utils.showErrors("Ha habido un error en la conexión de la base de datos.", e);
        }
    }
}