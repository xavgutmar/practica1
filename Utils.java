package org.example;

import java.sql.SQLException;

public class Utils {
    public static void showErrors(String msg, Exception e) {
        System.out.println(msg);
        System.out.println(e.getMessage());
    }
}
