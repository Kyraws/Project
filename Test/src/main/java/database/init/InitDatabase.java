package database.init;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static database.DatabaseConnection.getInitialConnection;

public class InitDatabase {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("Hello World!");
        InitDatabase init = new InitDatabase();
        init.initDatabase();

    }

    private void initDatabase() throws SQLException, ClassNotFoundException {
        Connection connection = getInitialConnection();
        Statement statement = connection.createStatement();
        statement.execute("CREATE DATABASE EVOL");
        statement.close();
        statement.close();
    }
}
