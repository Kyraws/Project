package database.init;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static database.DatabaseConnection.getConnection;
import static database.DatabaseConnection.getInitialConnection;

public class InitDatabase {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        InitDatabase init = new InitDatabase();
        init.initDatabase();

    }

    private void initDatabase() throws Exception {
        Connection connection = getInitialConnection();
        connection = getConnection();
        Statement statement = connection.createStatement();
//        statement.execute("CREATE DATABASE EVOL");
    createTables(connection);

    insertSampleCustomers(connection);
    insertSampleVehicles(connection);
        statement.close();

    }

    public static void createTables(Connection connection) {
        try {
            Statement statement = connection.createStatement();

            // Creating Vehicle table
            String createVehicleTable = "CREATE TABLE IF NOT EXISTS Vehicle (" +
                    "vehicle_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "brand VARCHAR(50) NOT NULL," +
                    "model VARCHAR(50) NOT NULL," +
                    "color VARCHAR(20) NOT NULL," +
                    "range_or_mileage INT NOT NULL," +
                    "cost DECIMAL(10,2) NOT NULL" +
                    ")";
            statement.executeUpdate(createVehicleTable);

            // Creating Car table
            String createCarTable = "CREATE TABLE IF NOT EXISTS Car (" +
                    "vehicle_id INT PRIMARY KEY," +
                    "type VARCHAR(20) NOT NULL," +
                    "passenger_number INT NOT NULL," +
                    "FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id)" +
                    ")";
            statement.executeUpdate(createCarTable);


            // Creating Motorcycle table
            String createMotorcycleTable = "CREATE TABLE IF NOT EXISTS Motorcycle (" +
                    "vehicle_id INT PRIMARY KEY," +
                    // Add additional attributes for motorcycles here
                    "FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id)" +
                    ")";
            statement.executeUpdate(createMotorcycleTable);

            // Creating Bike table
            String createBikeTable = "CREATE TABLE IF NOT EXISTS Bike (" +
                    "vehicle_id INT PRIMARY KEY," +
                    // Add additional attributes for bikes here
                    "bike_id VARCHAR(20) NOT NULL," +
                    "FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id)" +
                    ")";
            statement.executeUpdate(createBikeTable);

            // Creating Scooter table
            String createScooterTable = "CREATE TABLE IF NOT EXISTS Scooter (" +
                    "vehicle_id INT PRIMARY KEY," +
                    // Add additional attributes for scooters here
                    "scooter_id VARCHAR(20) NOT NULL," +
                    "FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id)" +
                    ")";
            statement.executeUpdate(createScooterTable);

            String createCustomerTable = "CREATE TABLE IF NOT EXISTS Customer (" +
                    "customer_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(100) NOT NULL," +
                    "address VARCHAR(255) NOT NULL," +
                    "date_of_birth DATE NOT NULL," +
                    "driver_license VARCHAR(20)," +
                    "card_details VARCHAR(100)" +
                    ")";
            statement.executeUpdate(createCustomerTable);

            String createRentTable = "CREATE TABLE IF NOT EXISTS Rent (" +
                    "rent_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "customer_name VARCHAR(100) NOT NULL," +
                    "date_of_rent DATE NOT NULL," +
                    "rent_duration INT NOT NULL," +
                    "cost DECIMAL(10,2) NOT NULL" +
                    ")";
            statement.executeUpdate(createRentTable);


            System.out.println("Tables created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void insertSampleCustomers(Connection connection) {
        try {
            Statement statement = connection.createStatement();

            // Inserting sample customer data into the Customer table
            String insertCustomer1 = "INSERT INTO Customer (name, address, date_of_birth, driver_license, card_details) VALUES ('John Doe', '123 Main St', '1990-05-15', 'DL12345', '1234-5678-9012-3456')";
            String insertCustomer2 = "INSERT INTO Customer (name, address, date_of_birth, driver_license, card_details) VALUES ('Alice Smith', '456 Elm St', '1985-09-20', NULL, '9876-5432-1098-7654')";

            statement.executeUpdate(insertCustomer1);
            statement.executeUpdate(insertCustomer2);

            System.out.println("Sample customers inserted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertSampleVehicles(Connection connection) {
        try {
            Statement statement = connection.createStatement();

            // Inserting sample vehicle data into the Vehicle table
            String insertVehicle1 = "INSERT INTO Vehicle (brand, model, color, range_or_mileage, cost) VALUES ('Toyota', 'Corolla', 'Black', 50000, 250.00)";
            String insertVehicle2 = "INSERT INTO Vehicle (brand, model, color, range_or_mileage, cost) VALUES ('Honda', 'Civic', 'Red', 60000, 300.00)";

            statement.executeUpdate(insertVehicle1);
            statement.executeUpdate(insertVehicle2);

            System.out.println("Sample vehicles inserted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
