package org.example;

import jdk.swing.interop.SwingInterOpUtils;

import java.sql.*;

public class Main {
    static final String DB_URL = "jdbc:postgresql://localhost:5433/postgres";
    static final String DB_USER_NAME = "your_user";
    static final String DB_USER_PASSWORD = "your_password";

    public static void main(String[] args) {
        String CREATE_TABLE = "CREATE TABLE student(\n" +
                "    id SERIAL NOT NULL PRIMARY KEY,\n" +
                "    name VARCHAR(50),\n" +
                "    surname VARCHAR (100),\n" +
                "    age INT\n" +
                ")";

//        Establish connection to the database and create the table.
//        createTable(CREATE_TABLE);

//        INSERT INTO THE TABLE
//        insertStudentTable("Gunel", "Velishova",28);
//        SELECT
        selectStudentTable();
//        UPDATE THE DATA IN THE TABLE
//        updateTable(URL, USER_NAME, USER_PASSWORD);
    }
    public static void createTable (String table) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_USER_PASSWORD)){
            Statement statement = connection.createStatement();
            statement.execute(table);
            System.out.println("Table created successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertStudentTable (String name, String surname, Integer age){
        String INSERT_INTO = "INSERT INTO student(name, surname, age)\n" +
                "VALUES (?, ?, ?)";
    try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_USER_PASSWORD)){
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);
        preparedStatement.setString (1, name);
        preparedStatement.setString (2, surname);
        preparedStatement.setInt(3, age);
        preparedStatement.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    public static void selectStudentTable(){
        String SELECT_ALL = "SELECT * FROM student";
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_USER_PASSWORD)){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()){

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
