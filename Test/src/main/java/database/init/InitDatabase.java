package database.init;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.DatabaseConnection.getConnection;
import static database.DatabaseConnection.getInitialConnection;

public class InitDatabase {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        InitDatabase init = new InitDatabase();
        init.createDatabase();
        init.initDatabase();

    }

    private void createDatabase() throws Exception {
        Connection connection = getInitialConnection();
        Statement statement = connection.createStatement();
        statement.execute("CREATE DATABASE IF NOT EXISTS EVOL");
        statement.close();
    }

    private void initDatabase() throws Exception {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        createTables(connection);

//        insertSampleCustomers(connection);
//        insertSampleVehicles(connection);
        statement.close();

    }

    public static void createTables(Connection connection) {
        try {
            Statement statement = connection.createStatement();

            // Creating Vehicle table
            String createVehicleTable = "CREATE TABLE IF NOT EXISTS Vehicle (" +
                    "vehicle_id INT PRIMARY KEY," +
                    "brand VARCHAR(255) NOT NULL," +
                    "model VARCHAR(255) NOT NULL," +
                    "color VARCHAR(255) NOT NULL," +
                    "range_in_kms INT NOT NULL," +
                    "registration_number VARCHAR(255) UNIQUE," +
                    "category VARCHAR(50) NOT NULL," +
                    "status VARCHAR(20) CHECK (status IN ('Available', 'Rented', 'Damaged', 'Being Repaired'))" +
                    ")";
            statement.executeUpdate(createVehicleTable);

            // Creating Car table
            String createCarTable = "CREATE TABLE IF NOT EXISTS Car (" +
                    "vehicle_id INT PRIMARY KEY," +
                    "type VARCHAR(255) NOT NULL," +
                    "passenger_number INT NOT NULL," +
                    "daily_rental_cost DECIMAL(10, 2)," +
                    "insurance_cost DECIMAL(10, 2)," +
                    "FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id)" +
                    ")";
            statement.executeUpdate(createCarTable);


            // Creating Motorcycle table
            String createMotorcycleTable = "CREATE TABLE IF NOT EXISTS Motorcycle (" +
                    "vehicle_id INT PRIMARY KEY," +
                    "daily_rental_cost DECIMAL(10, 2)," +
                    "insurance_cost DECIMAL(10, 2)," +
                    "FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id)" +
                    ")";
            statement.executeUpdate(createMotorcycleTable);

            // Creating Bike table
            String createBikeTable = "CREATE TABLE IF NOT EXISTS Bike (" +
                    "vehicle_id INT PRIMARY KEY," +
                    // Add additional attributes for bikes here
                    "daily_rental_cost DECIMAL(10, 2)," +
                    "insurance_cost DECIMAL(10, 2)," +
                    "FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id)" +
                    ")";
            statement.executeUpdate(createBikeTable);

            // Creating Scooter table
            String createScooterTable = "CREATE TABLE IF NOT EXISTS Scooter (" +
                    "vehicle_id INT PRIMARY KEY," +
                    // Add additional attributes for scooters here
                    "daily_rental_cost DECIMAL(10, 2)," +
                    "insurance_cost DECIMAL(10, 2)," +
                    "FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id)" +
                    ")";
            statement.executeUpdate(createScooterTable);

            String createCustomerTable = "CREATE TABLE IF NOT EXISTS Customer (" +
                    "customer_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(100) NOT NULL," +
                    "address VARCHAR(255) NOT NULL," +
                    "date_of_birth DATE NOT NULL," +
                    "driver_license VARCHAR(255)," +
                    "card_details VARCHAR(100)" +
                    ")";
            statement.executeUpdate(createCustomerTable);

            String createRentTable = "CREATE TABLE IF NOT EXISTS Rent (" +
                    "rent_id INT PRIMARY KEY AUTO_INCREMENT," +
                    "customer_id INT NOT NULL," +
                    "vehicle_id INT NOT NULL," +
                    "date_of_rent DATE NOT NULL," +
                    "rent_duration INT NOT NULL," +
                    "total_cost DECIMAL(10,2) NOT NULL," +
                    "driver_license VARCHAR(255)," +
                    "FOREIGN KEY(customer_id) REFERENCES Customer(customer_id)," +
                    "FOREIGN KEY(vehicle_id) REFERENCES Vehicle(vehicle_id), " +
                    "FOREIGN KEY(driver_license) REFERENCES Customer(driver_license) " +
                    ")";
            statement.executeUpdate(createRentTable);


            System.out.println("Tables created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void RegisterCustomer(String name, String address, String date_of_birth, String driver_license, String card_details, Connection connection) {
        String sql = "INSERT INTO Customer (customer_id, name, address, date_of_birth, driver_license, card_details) " +
                "VALUES (NULL, ?, ?, ?, ?, ?)";


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // Set values for the placeholders
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, date_of_birth);
            preparedStatement.setString(4, driver_license);
            preparedStatement.setString(5, card_details);

            // Execute the update
            preparedStatement.executeUpdate();
            System.out.println("User Registered Successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        {


        }
    }

    public static void RegisterCar(String brand, String model, String color, int range_or_mileage, double cost, String type, int passenger_number, Connection connection) {
        String sql = "INSERT INTO Vehicle (vehicle_id, brand, model, color, range_or_mileage, cost) " +
                "VALUES (NULL, ?, ?, ?, ?, ?)";
        String sql2 = "INSERT INTO Car (vehicle_id, type, passenger_number) " +
                "VALUES (LAST_INSERT_ID(), ?, ?)";

        try {
            PreparedStatement VehicleStatement = connection.prepareStatement(sql);
            PreparedStatement CarStatement = connection.prepareStatement(sql2);
            // Set values for the placeholders
            VehicleStatement.setString(1, brand);
            VehicleStatement.setString(2, model);
            VehicleStatement.setString(3, color);
            VehicleStatement.setInt(4, range_or_mileage);
            VehicleStatement.setDouble(5, cost);
            CarStatement.setString(1, type);
            CarStatement.setInt(2, passenger_number);

            // Execute the update
            VehicleStatement.executeUpdate();
            CarStatement.executeUpdate();
            System.out.println("Car Registered Successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void RegisterMotorcycle(String brand, String model, String color, int range_or_mileage, double cost, Connection connection) {
        String sql = "INSERT INTO Vehicle (vehicle_id, brand, model, color, range_or_mileage, cost) " +
                "VALUES (NULL, ?, ?, ?, ?, ?)";
        String sql2 = "INSERT INTO Motorcycle (vehicle_id) " +
                "VALUES (LAST_INSERT_ID())";

        try {
            PreparedStatement VehicleStatement = connection.prepareStatement(sql);
            PreparedStatement MotorcycleStatement = connection.prepareStatement(sql2);
            // Set values for the placeholders
            VehicleStatement.setString(1, brand);
            VehicleStatement.setString(2, model);
            VehicleStatement.setString(3, color);
            VehicleStatement.setInt(4, range_or_mileage);
            VehicleStatement.setDouble(5, cost);

            // Execute the update
            VehicleStatement.executeUpdate();
            MotorcycleStatement.executeUpdate();
            System.out.println("Motorcycle Registered Successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void RegisterBike(String brand, String model, String color, int range_or_mileage, double cost, String bike_id, Connection connection) {
        String sql = "INSERT INTO Vehicle (vehicle_id, brand, model, color, range_or_mileage, cost) " +
                "VALUES (NULL, ?, ?, ?, ?, ?)";
        String sql2 = "INSERT INTO Bike (vehicle_id, bike_id) " +
                "VALUES (LAST_INSERT_ID(), ?)";

        try {
            PreparedStatement VehicleStatement = connection.prepareStatement(sql);
            PreparedStatement BikeStatement = connection.prepareStatement(sql2);
            // Set values for the placeholders
            VehicleStatement.setString(1, brand);
            VehicleStatement.setString(2, model);
            VehicleStatement.setString(3, color);
            VehicleStatement.setInt(4, range_or_mileage);
            VehicleStatement.setDouble(5, cost);

            BikeStatement.setString(1, bike_id);

            // Execute the update
            VehicleStatement.executeUpdate();
            BikeStatement.executeUpdate();
            System.out.println("Bike Registered Successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void RegisterScooter(String brand, String model, String color, int range_or_mileage, double cost, String scooter_id, Connection connection) {
        String sql = "INSERT INTO Vehicle (vehicle_id, brand, model, color, range_or_mileage, cost) " +
                "VALUES (NULL, ?, ?, ?, ?, ?)";
        String sql2 = "INSERT INTO Scooter (vehicle_id, scooter_id) " +
                "VALUES (LAST_INSERT_ID(), ?)";

        try {
            PreparedStatement VehicleStatement = connection.prepareStatement(sql);
            PreparedStatement ScooterStatement = connection.prepareStatement(sql2);
            // Set values for the placeholders
            VehicleStatement.setString(1, brand);
            VehicleStatement.setString(2, model);
            VehicleStatement.setString(3, color);
            VehicleStatement.setInt(4, range_or_mileage);
            VehicleStatement.setDouble(5, cost);

            ScooterStatement.setString(1, scooter_id);

            // Execute the update
            VehicleStatement.executeUpdate();
            ScooterStatement.executeUpdate();
            System.out.println("Scooter Registered Successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertSampleCustomers(Connection connection) {
        RegisterCustomer("Orestis", "Ntilinta", "2000-05-15", "DL12345", "1234-5678-9012-3456", connection);
        RegisterCustomer("John Doe", "123 Main St", "1990-05-15", "DL12345", "1234-5678-9012-3456", connection);
        RegisterCustomer("Alice Smith", "456 Elm St", "1985-09-20", null, "9876-5432-1098-7654", connection);

    }

    public static void insertSampleVehicles(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            RegisterCar("ToyotA", "Corolla", "Black", 50000, 250.00, "Sedan", 5, connection);
            RegisterBike("BMX", "BMX", "Black", 50000, 250.00, "BMX123", connection);
            RegisterMotorcycle("BMW", "BMW", "Black", 50000, 250.00, connection);
            RegisterScooter("Scooter", "Scooter", "Black", 50000, 250.00, "Scooter123", connection);


            System.out.println("Sample vehicles inserted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static List<String> searchAvailableCars(String category,Connection connection) {
        List<String> availableCars = new ArrayList<String>();
        String sql = "SELECT v.brand, v.model, v.color, v.range_or_mileage, v.cost, c.type, c.passenger_number " +
                "FROM Vehicle v " +
                "JOIN Car c ON v.vehicle_id = c.vehicle_id " +
                "WHERE c.type = ?";

        try {
             PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set value for the placeholder in the SQL query
            preparedStatement.setString(1, category);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                String carDetails = String.format("Brand: %s, Model: %s, Color: %s, Range/Mileage: %d, Cost: %.2f, Type: %s, Passenger Number: %d",
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getString("color"),
                        resultSet.getInt("range_or_mileage"),
                        resultSet.getDouble("cost"),
                        resultSet.getString("type"),
                        resultSet.getInt("passenger_number"));

                availableCars.add(carDetails);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception based on your application's error handling strategy
        }

        return availableCars;
    }
}
