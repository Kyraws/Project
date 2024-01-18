<!-- RegisterVehicle.jsp -->

<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="database.init.InitDatabase" %>
<%@ page import="database.DatabaseConnection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>

<%
    // Check if the form is submitted
    if (request.getMethod().equals("POST")) {
        String vehicleType = request.getParameter("vehicleType");



        if (request.getMethod().equals("POST")) {
            Connection connection = null;
            try {
                // Obtain a database connection
                connection = DatabaseConnection.getConnection();

                // Call the appropriate function based on the selected vehicleType
                switch (vehicleType) {
                    case "Car":
                        InitDatabase.RegisterCar(request.getParameter("brand"), request.getParameter("model"), request.getParameter("color"),
                                Integer.valueOf(request.getParameter("range_in_km")), request.getParameter("registration_number"),
                                vehicleType, Integer.valueOf(request.getParameter("passenger_number")) ,Integer.valueOf(request.getParameter("daily_rental_cost")),
                                Integer.valueOf(request.getParameter("insurance_cost")),connection);
                        break;
                    case "Bike":
                        InitDatabase.RegisterBike( request.getParameter("brand"), request.getParameter("model"), request.getParameter("color"),
                                Integer.valueOf(request.getParameter("range_in_km")), request.getParameter("registration_number"),
                                 Integer.valueOf(request.getParameter("daily_rental_cost")),
                                Integer.valueOf(request.getParameter("insurance_cost")),connection);
                        break;
                    case "Motorcycle":
                        InitDatabase.RegisterMotorcycle( request.getParameter("brand"), request.getParameter("model"), request.getParameter("color"),
                                Integer.valueOf(request.getParameter("range_in_km")), request.getParameter("registration_number"),
                                Integer.valueOf(request.getParameter("daily_rental_cost")),
                                Integer.valueOf(request.getParameter("insurance_cost")),connection);
                        break;
                    case "Scooter":
                        InitDatabase.RegisterScooter( request.getParameter("brand"), request.getParameter("model"), request.getParameter("color"),
                                Integer.valueOf(request.getParameter("range_in_km")), request.getParameter("registration_number"),
                                Integer.valueOf(request.getParameter("daily_rental_cost")),
                                Integer.valueOf(request.getParameter("insurance_cost")),connection);
                        break;
                    default:
                        out.println("Invalid vehicleType");
                }

                out.println("User Registered Successfully");
            } catch (SQLException e) {
                out.println("Error: " + e.getMessage());
            } finally {
                // Close the database connection
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }

%>

<html>
<head>
    <title>Register Vehicle</title>
</head>
<body>

<h1>Register Vehicle</h1>

<form method="post" action="AddVehicle.jsp">
    <label for="vehicleType">Select Vehicle Type:</label>
    <select name="vehicleType" required>
        <option value="Motorcycle">Motorcycle</option>
        <option value="Bike">Bike</option>
        <option value="Car">Car</option>
        <option value="Scooter">Scooter</option>
    </select><br>

    <!-- Common vehicle fields -->
    <label for="brand">Brand:</label>
    <input type="text" name="brand" required><br>

    <label for="model">Model:</label>
    <input type="text" name="model" required><br>

    <label for="color">Color:</label>
    <input type="text" name="color" required><br>

    <label for="range_in_km">Range (in km):</label>
    <input type="text" name="range_in_km" required><br>

    <label for="registration_number">Registration Number:</label>
    <input type="text" name="registration_number" required><br>


    <label for="status">Status:</label>
    <input type="text" name="status" required><br>

    <label for="daily_rental_cost">Daily Rental Cost:</label>
    <input type="text" name="daily_rental_cost"><br>

    <label for="insurance_cost">Insurance Cost:</label>
    <input type="text" name="insurance_cost"><br>

    <!-- Vehicle type-specific fields -->
    <div id="carFields" style="display: none;">
        <label for="carType">Car Type:</label>
        <input type="text" name="carType"><br>

        <label for="passenger_number">Passenger Number:</label>
        <input type="text" name="passenger_number"><br>


    </div>


    <input type="submit" value="Register Vehicle">

</form>

<script>
    document.querySelector('select[name="vehicleType"]').addEventListener('change', function () {
        var selectedType = this.value;

        // Hide all type-specific fields
        document.querySelectorAll('[id$="Fields"]').forEach(function (element) {
            element.style.display = 'none';
        });

        // Show fields specific to the selected type
        document.getElementById(selectedType.toLowerCase() + 'Fields').style.display = 'block';
    });
</script>

</body>
</html>


<%--<!-- RegisterVehicle.jsp -->--%>

<%--<%@ page import="java.sql.Connection" %>--%>
<%--<%@ page import="java.sql.SQLException" %>--%>
<%--<%@ page import="database.init.InitDatabase" %>--%>
<%--<%@ page import="database.DatabaseConnection" %>--%>
<%--<%@ page import="static database.init.InitDatabase.RegisterCar" %>--%>
<%--<%@ page import="static database.init.InitDatabase.RegisterScooter" %>--%>
<%--<%@ page import="static database.init.InitDatabase.*" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Register Vehicle</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--<h1>Register Vehicle</h1>--%>

<%--<form method="post" action="RegisterVehicle.jsp">--%>
<%--    <label for="vehicleType">Select Vehicle Type:</label>--%>
<%--    <select name="vehicleType" required>--%>
<%--        <option value="Motorcycle">Motorcycle</option>--%>
<%--        <option value="Bike">Bike</option>--%>
<%--        <option value="Car">Car</option>--%>
<%--        <option value="Scooter">Scooter</option>--%>
<%--    </select><br>--%>

<%--    <!-- Common vehicle fields -->--%>
<%--    <label for="brand">Brand:</label>--%>
<%--    <input type="text" name="brand" required><br>--%>

<%--    <label for="model">Model:</label>--%>
<%--    <input type="text" name="model" required><br>--%>

<%--    <label for="color">Color:</label>--%>
<%--    <input type="text" name="color" required><br>--%>

<%--    <label for="range_in_km">Range (in km):</label>--%>
<%--    <input type="text" name="range_in_km" required><br>--%>

<%--    <label for="registration_number">Registration Number:</label>--%>
<%--    <input type="text" name="registration_number" required><br>--%>


<%--    <label for="status">Status:</label>--%>
<%--    <input type="text" name="status" required><br>--%>

<%--    <label for="daily_rental_cost">Daily Rental Cost:</label>--%>
<%--    <input type="text" name="daily_rental_cost"><br>--%>

<%--    <label for="insurance_cost">Insurance Cost:</label>--%>
<%--    <input type="text" name="insurance_cost"><br>--%>

<%--    <!-- Vehicle type-specific fields -->--%>
<%--    <div id="carFields" style="display: none;">--%>
<%--        <label for="carType">Car Type:</label>--%>
<%--        <input type="text" name="carType"><br>--%>

<%--        <label for="passenger_number">Passenger Number:</label>--%>
<%--        <input type="text" name="passenger_number"><br>--%>


<%--    </div>--%>


<%--    <input type="submit" value="Register Vehicle">--%>
<%--</form>--%>

<%--<script>--%>
<%--    document.querySelector('select[name="vehicleType"]').addEventListener('change', function() {--%>
<%--        var selectedType = this.value;--%>

<%--        // Hide all type-specific fields--%>
<%--        document.querySelectorAll('[id$="Fields"]').forEach(function(element) {--%>
<%--            element.style.display = 'none';--%>
<%--        });--%>

<%--        // Show fields specific to the selected type--%>
<%--        document.getElementById(selectedType.toLowerCase() + 'Fields').style.display = 'block';--%>
<%--    });--%>
<%--</script>--%>

<%--</body>--%>
<%--</html>--%>
