package org.example;

import java.sql.*;

public class MainJdbc {
    public static void main(String[] args) {
        // JDBC URL for PostgreSQL
        final String url = "jdbc:postgresql://localhost:5433/postgres";
        final String username = "your_user";
        final String password = "your_password";

        // SQL query to create the Student table
        String CREATE_TABLE = "CREATE TABLE Student(\n" +
                "    id INT PRIMARY KEY,\n" +
                "    name VARCHAR(40),\n" +
                "    surname VARCHAR(70),\n" +
                "    age INT\n" +
                ")";

        insertTable(url, username, password);
//        selectAll(url, username, password);
        UpdateData(url, username, password);
    }

//        try {
//            // Establish connection to the database
//            Connection connection = DriverManager.getConnection(url, username, password);
//            Statement statement = connection.createStatement();
//            statement.execute(CREATE_TABLE); // Execute the SQL query to create the table
//            System.out.println("Table created successfully.");
//        } catch (Exception e) {
//            e.printStackTrace(); // Print any exceptions
//        }


    public static void insertTable(String url, String username, String password) {
        String INSERT_INTO = "INSERT INTO Student (id, name, surname, age) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
            preparedStatement.setInt(1, 7);
            preparedStatement.setString(2, "hEEY");
            preparedStatement.setString(3, "Novruzov");
            preparedStatement.setInt(4, 12);

            preparedStatement.setInt(1, 9);
            preparedStatement.setString(2, "Jey");
            preparedStatement.setString(3, "Hiyov");
            preparedStatement.setInt(4, 34);

            preparedStatement.executeUpdate();
//            System.out.println("table insert into:");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void selectAll(String url, String username, String password) {
        String SELECT_ALL = "Select * from Student";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");
                System.out.println("Id: " + id + " student name: " + name + " surname: " + surname + " age: " + age);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void UpdateData(String url, String username, String password) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String UPDATE_COLUMN = "UPDATE student SET name = ?, age = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COLUMN);
            preparedStatement.setString(1, "Ali"); // New name value
            preparedStatement.setInt(2, 21); // New age value
            preparedStatement.setInt(3, 6); // ID of the student to be updated

            preparedStatement.executeUpdate();
            System.out.println("Update successful");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
