package org.example;

import java.sql.SQLException;

public class Utils {
    public static void showErrors(String msg, Exception e) {
        System.out.println(msg);
        System.out.println(e.getMessage());
    }

    public static void getBBDD() {
        try {
            final var METADATA = ConexionBBDD.getConnection().getMetaData();
            final var TABLES = METADATA.getTables(null, null, "%", new String[] {"TABLE"});

            while (TABLES.next()) {
                final String TABLE_NAME = TABLES.getString("TABLE_NAME");

                if (TABLE_NAME.equals("sys_config")) {
                    continue;
                }

                System.out.println(TABLE_NAME);
                final var COLUMN = METADATA.getColumns(null, null, TABLE_NAME, "%");

                while (COLUMN.next()) {
                    final String COLUMN_NAME = COLUMN.getString("COLUMN_NAME");
                    final String COLUMN_TYPE = COLUMN.getString("TYPE_NAME");
                    System.out.println(COLUMN_NAME);
                    System.out.println(COLUMN_TYPE);
                }
                COLUMN.close();

                final String QUERY = "SELECT * FROM " + TABLE_NAME;
                final var STATEMENT = ConexionBBDD.getConnection().createStatement();
                final var RESULT = STATEMENT.executeQuery(QUERY);

                final int N_COLUMNS = RESULT.getMetaData().getColumnCount();
                while (RESULT.next()) {
                    for (int i = 0; i < N_COLUMNS; i++) {
                        final String COLUMN_VALUE = RESULT.getString(i);
                        System.out.println(COLUMN_VALUE + "\t");
                    }
                    System.out.println();
                }

                RESULT.close();
            }

            TABLES.close();
        } catch (SQLException e) {
            Utils.showErrors("Ha habido un error en el muestre de la base de datos.", e);
        }
    }
}
