package com.tsa;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {
    public static final String FIRST_DB_CONNECTION = "sample";
    public static final String MAIN_DB_NAME = "SeriousDB";
    public static final String LOGIN = "***";
    public static final String PASSWORD = "***";

    public String nameTable1;
    public String nameTable2;
    public String nameTable3;
    public String nameTable4;
    public String nameDataBase;

    //Подключение к БД
    private static Connection getDBConnection(String dataBaseName) {
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Something wrong with the class path");
        }
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dataBaseName, LOGIN, PASSWORD);
            System.out.println("CONNECTED");
            return connection;
        } catch (SQLException e) {
            System.out.println("NOT CONNECTED");
            //return null;
        }
        return connection;
    }

    //Методы для создания каждой таблцы
    public void createTable1(String tableName, String dbName) throws SQLException {

        Connection connection = null;
        CallableStatement callableStatement = null;
        connection = getDBConnection(dbName);

        try {
            String SQL = "{call create_table1 (?)}";
            callableStatement = connection.prepareCall(SQL);
            callableStatement.setString(1, tableName);

            //callableStatement.registerOutParameter(1,Types.VARCHAR);
            System.out.println("Executing procedure...");
            callableStatement.execute();

            nameTable1 = tableName;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void createTable2(String tableName, String dbName) throws SQLException {
        Connection connection = null;
        CallableStatement callableStatement = null;

        connection = getDBConnection(dbName);

        try {
            String SQL = "{call create_table2 (?)}";
            callableStatement = connection.prepareCall(SQL);
            callableStatement.setString(1, tableName);

            //callableStatement.registerOutParameter(1,Types.VARCHAR);
            System.out.println("Executing procedure...");
            callableStatement.execute();

            nameTable2 = tableName;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void createTable3(String tableName, String dbName) throws SQLException {
        Connection connection = null;
        CallableStatement callableStatement = null;

        connection = getDBConnection(dbName);

        try {
            String SQL = "{call create_table3 (?)}";
            callableStatement = connection.prepareCall(SQL);
            callableStatement.setString(1, tableName);

            //callableStatement.registerOutParameter(1,Types.VARCHAR);
            System.out.println("Executing procedure...");
            callableStatement.execute();
            nameTable3 = tableName;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void createTable4(String tableName, String dbName) throws SQLException {
        Connection connection = null;
        CallableStatement callableStatement = null;

        connection = getDBConnection(dbName);

        try {
            String SQL = "{call create_table4 (?)}";
            callableStatement = connection.prepareCall(SQL);
            callableStatement.setString(1, tableName);

            //callableStatement.registerOutParameter(1,Types.VARCHAR);
            System.out.println("Executing procedure...");
            callableStatement.execute();

            nameTable4 = tableName;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void createDb(String dbName) throws SQLException {
        Connection connection = null;
        CallableStatement callableStatement = null;
        connection = getDBConnection(FIRST_DB_CONNECTION);

        try {
            String SQL = "{call create_db (?)}";
            callableStatement = connection.prepareCall(SQL);
            callableStatement.setString(1, dbName);
            System.out.println("Executing procedure...");
            callableStatement.execute();
            nameDataBase = dbName;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void deleteDb(String dbName) throws SQLException {
        Connection connection = null;
        CallableStatement callableStatement = null;
        connection = getDBConnection(FIRST_DB_CONNECTION);
        try {
            String SQL = "{call delete_db (?)}";
            callableStatement = connection.prepareCall(SQL);
            callableStatement.setString(1, dbName);
            System.out.println("Executing procedure...");
            callableStatement.execute();
            System.out.println("Database " + dbName + " dropped");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    //Методы для вывода содержимого каждой таблицы
    public String selectFromTable1(String dbName, String tableName) throws SQLException {

        String query = "{call select_table1 ()}";
        String forResult = "Table 1 listeners: ";
        CallableStatement statement = null;
        Connection connection = null;
        connection = getDBConnection(dbName);

        try {

            statement = connection.prepareCall(query);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                forResult = forResult + "\n" + resultSet.getInt(1) + "    " + resultSet.getString(2) + "    " +
                        resultSet.getString(3) + "    " + resultSet.getString(4) + "    " +
                        resultSet.getInt(5) + "    " + resultSet.getInt(6);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return forResult;
    }

    public String selectFromTable2(String dbName, String tableName) throws SQLException {

        String query = "{call select_table2 ()}";
        String forResult = "Table 2 lections: ";
        CallableStatement statement = null;
        Connection connection = null;
        connection = getDBConnection(dbName);

        try {
            statement = connection.prepareCall(query);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                forResult = forResult + "\n" + resultSet.getInt(1) + "    " + resultSet.getString(2) + "    " +
                        resultSet.getDate(3) + "    " + resultSet.getInt(4);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return forResult;
    }

    public String selectFromTable3(String dbName, String tableName) throws SQLException {

        String query = "{call select_table3 ()}";
        String forResult = "Table 3 lecturer: ";
        CallableStatement statement = null;
        Connection connection = null;
        connection = getDBConnection(dbName);

        try {
            statement = connection.prepareCall(query);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                forResult = forResult + "\n" + resultSet.getInt(1) + "    " + resultSet.getString(2) + "    " +
                        resultSet.getString(3) + "    " + resultSet.getString(4) + "    " + resultSet.getInt(5);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return forResult;
    }

    public String selectFromTable4(String dbName, String tableName) throws SQLException {

        String query = "{call select_table4 ()}";
        String forResult = "Table 4 locations: ";
        CallableStatement statement = null;
        Connection connection = null;
        connection = getDBConnection(dbName);

        try {
            statement = connection.prepareCall(query);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                forResult = forResult + "\n" + resultSet.getInt(1) + "    " + resultSet.getInt(2);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return forResult;
    }

    //Методы для вставки значений в каждую таблицу
    public void insertTable1(String tableName, String dbName, int listener_id, String phone, String firstname, String email, int age, int lection_id) throws SQLException {
        Connection connection = null;
        CallableStatement callableStatement = null;

        connection = getDBConnection(dbName);

        try {
            String SQL = "{call insert_table1 (?,?,?,?,?,?,?)}";
            callableStatement = connection.prepareCall(SQL);
            callableStatement.setString(1, tableName);
            callableStatement.setInt(2, listener_id);
            callableStatement.setString(3, phone);
            callableStatement.setString(4, firstname);
            callableStatement.setString(5, email);
            callableStatement.setInt(6, age);
            callableStatement.setInt(7, lection_id);

            //callableStatement.registerOutParameter(1,Types.VARCHAR);
            System.out.println("Executing procedure...");
            callableStatement.execute();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void insertTable2(String tableName, String dbName, int lection_id, String lname, String ldate, int lec_id) throws SQLException {
        Connection connection = null;
        CallableStatement callableStatement = null;

        connection = getDBConnection(dbName);

        try {
            String SQL = "{call insert_table2 (?,?,?,?,?)}";
            callableStatement = connection.prepareCall(SQL);
            callableStatement.setString(1, tableName);
            callableStatement.setInt(2, lection_id);
            callableStatement.setString(3, lname);
            callableStatement.setDate(4, java.sql.Date.valueOf(ldate));
            callableStatement.setInt(5, lec_id);


            //callableStatement.registerOutParameter(1,Types.VARCHAR);
            System.out.println("Executing procedure...");
            callableStatement.execute();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void insertTable3(String tableName, String dbName, int lect_id, String firstname, String surname, String specialization, int loc_id) throws SQLException {
        Connection connection = null;
        CallableStatement callableStatement = null;

        connection = getDBConnection(dbName);

        try {
            String SQL = "{call insert_table3 (?,?,?,?,?,?)}";
            callableStatement = connection.prepareCall(SQL);
            callableStatement.setString(1, tableName);
            callableStatement.setInt(2, lect_id);
            callableStatement.setString(3, firstname);
            callableStatement.setString(4, surname);
            callableStatement.setString(5, specialization);
            callableStatement.setInt(6, loc_id);


            //callableStatement.registerOutParameter(1,Types.VARCHAR);
            System.out.println("Executing procedure...");
            callableStatement.execute();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void insertTable4(String tableName, String dbName, int loc_id, int capacity) throws SQLException {
        Connection connection = null;
        CallableStatement callableStatement = null;

        connection = getDBConnection(dbName);

        try {
            String SQL = "{call insert_table4 (?,?,?)}";
            callableStatement = connection.prepareCall(SQL);
            callableStatement.setString(1, tableName);
            callableStatement.setInt(2, loc_id);
            callableStatement.setInt(3, capacity);


            //callableStatement.registerOutParameter(1,Types.VARCHAR);
            System.out.println("Executing procedure...");
            callableStatement.execute();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    //Методы для поиска в таблице
    public String searchTable1(String dbName, String firstname) throws SQLException {
        Connection connection = null;
        CallableStatement callableStatement = null;

        connection = getDBConnection(dbName);
        String result = "Результат поиска по имени: " + firstname + "\n";

        try {
            String SQL = "{call search_table1 (?,?,?,?,?,?,?)}";
            callableStatement = connection.prepareCall(SQL);

            callableStatement.setString(1, firstname);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.registerOutParameter(5, Types.VARCHAR);
            callableStatement.registerOutParameter(6, Types.INTEGER);
            callableStatement.registerOutParameter(7, Types.INTEGER);
            System.out.println("Executing procedure...");

            boolean findSomething = callableStatement.execute();

            System.out.println(findSomething);

            result = result + callableStatement.getInt(2) + " " +
                    callableStatement.getString(3) + " " + callableStatement.getString(4) + " " +
                    callableStatement.getString(5) + " " + callableStatement.getInt(6) + " " + callableStatement.getInt(7);

            System.out.println("Поиск по 1 таблице завершен!" + "\n" + callableStatement.getInt(2) + " " +
                    callableStatement.getString(3) + " " + callableStatement.getString(4) + " " +
                    callableStatement.getString(5) + " " + callableStatement.getInt(6) + " " + callableStatement.getInt(7));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return result;
    }

    public String searchTable2(String dbName, String firstname) throws SQLException {
        Connection connection = null;
        CallableStatement callableStatement = null;

        connection = getDBConnection(dbName);
        String result = "Результат поиска по слову: " + firstname + "\n";

        try {
            String SQL = "{call search_table2 (?,?,?,?,?)}";
            callableStatement = connection.prepareCall(SQL);

            callableStatement.setString(1, firstname);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.registerOutParameter(4, Types.DATE);
            callableStatement.registerOutParameter(5, Types.INTEGER);
            //ResultSet rs = callableStatement.executeQuery();
            //rs.next();


            //callableStatement.registerOutParameter(1,Types.VARCHAR);
            System.out.println("Executing procedure...");


            callableStatement.execute();

            result = result + callableStatement.getInt(2) + " " +
                    callableStatement.getString(3) + " " + callableStatement.getDate(4) + " " + callableStatement.getInt(5);

            System.out.println("РџРѕРёСЃРє РїРѕ 2 С‚Р°Р±Р»РёС†Рµ Р·Р°РІРµСЂС€РµРЅ!" + "\n" + callableStatement.getInt(2) + " " +
                    callableStatement.getString(3) + " " + callableStatement.getDate(4) + " " + callableStatement.getInt(5));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return result;
    }

    public String searchTable3(String dbName, String firstname) throws SQLException {
        Connection connection = null;
        CallableStatement callableStatement = null;

        connection = getDBConnection(dbName);
        String result = "Результат поиска по слову: " + firstname + "\n";

        try {
            String SQL = "{call search_table3 (?,?,?,?,?,?)}";
            callableStatement = connection.prepareCall(SQL);

            callableStatement.setString(1, firstname);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.registerOutParameter(3, Types.VARCHAR);
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.registerOutParameter(5, Types.VARCHAR);
            callableStatement.registerOutParameter(6, Types.INTEGER);
            //ResultSet rs = callableStatement.executeQuery();
            //rs.next();


            //callableStatement.registerOutParameter(1,Types.VARCHAR);
            System.out.println("Executing procedure...");

            callableStatement.execute();

            result = result + callableStatement.getInt(2) + " " +
                    callableStatement.getString(3) + " " + callableStatement.getString(4) + " " +
                    callableStatement.getString(5) + " " + callableStatement.getInt(6);

            System.out.println("РџРѕРёСЃРє РїРѕ 2 С‚Р°Р±Р»РёС†Рµ Р·Р°РІРµСЂС€РµРЅ!" + "\n" + callableStatement.getInt(2) + " " +
                    callableStatement.getString(3) + " " + callableStatement.getString(4) + " " +
                    callableStatement.getString(5) + " " + callableStatement.getInt(6));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return result;
    }

    public String searchTable4(String dbName, int firstname) throws SQLException {
        Connection connection = null;
        CallableStatement callableStatement = null;

        connection = getDBConnection(dbName);
        String result = "Результат поиска по слову: " + firstname + "\n";

        try {
            String SQL = "{call search_table4 (?,?,?)}";
            callableStatement = connection.prepareCall(SQL);

            callableStatement.setInt(1, firstname);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.registerOutParameter(3, Types.INTEGER);
            //ResultSet rs = callableStatement.executeQuery();
            //rs.next();


            //callableStatement.registerOutParameter(1,Types.VARCHAR);
            System.out.println("Executing procedure...");

            callableStatement.execute();

            result = result + callableStatement.getInt(2) + " " +
                    callableStatement.getInt(3);

            System.out.println("РџРѕРёСЃРє РїРѕ 2 С‚Р°Р±Р»РёС†Рµ Р·Р°РІРµСЂС€РµРЅ!" + "\n" + callableStatement.getInt(2) + " " +
                    callableStatement.getInt(3));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    //Методы для отчистки данных из таблицы
    public void deleteDataFromTable1(String dbName) throws SQLException {
        String query = "{call delete_table1 ()}";
        CallableStatement statement = null;
        Connection connection = null;

        connection = getDBConnection(dbName);
        ArrayList<String> result = new ArrayList<>();

        try {

            statement = connection.prepareCall(query);
            boolean res = statement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void deleteAllElementTable2(String dbName) throws SQLException {
        String query = "{call delete_all_element_table2 ()}";
        CallableStatement statement = null;
        Connection connection = null;

        connection = getDBConnection(dbName);
        ArrayList<String> result = new ArrayList<>();

        try {

            statement = connection.prepareCall(query);
            boolean res = statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String forResult = resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getDate(3) + " " + resultSet.getInt(4);
                result.add(forResult);
                System.out.println(forResult);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void deleteAllElementTable3(String dbName) throws SQLException {
        String query = "{call delete_all_element_table3 ()}";
        CallableStatement statement = null;
        Connection connection = null;

        connection = getDBConnection(dbName);
        ArrayList<String> result = new ArrayList<>();

        try {

            statement = connection.prepareCall(query);
            boolean res = statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String forResult = resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getInt(5);
                result.add(forResult);
                System.out.println(forResult);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void deleteAllElementTable4(String dbName) throws SQLException {
        String query = "{call delete_all_element_table4 ()}";
        CallableStatement statement = null;
        Connection connection = null;

        connection = getDBConnection(dbName);
        ArrayList<String> result = new ArrayList<>();

        try {

            statement = connection.prepareCall(query);
            boolean res = statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String forResult = resultSet.getInt(1) + " " + resultSet.getInt(2);
                result.add(forResult);
                System.out.println(forResult);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    //Методы для удаления поля таблицы
    public void deleteTable1(String dbName, String firstname) throws SQLException {
        String query = "{call delete_select_element_table1(?)}";
        CallableStatement statement = null;
        Connection connection = null;

        connection = getDBConnection(dbName);
        ArrayList<String> result = new ArrayList<>();

        try {

            statement = connection.prepareCall(query);
            statement.setString(1, firstname);
            boolean res = statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String forResult = resultSet.getInt(1) + " " + resultSet.getString(2) + " " +
                        resultSet.getString(3) + " " + resultSet.getString(4) + " " +
                        resultSet.getInt(5) + " " + resultSet.getInt(6);
                result.add(forResult);
                System.out.println(forResult);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void deleteTable2(String dbName, String firstname) throws SQLException {
        String query = "{call delete_select_element_table2(?)}";
        CallableStatement statement = null;
        Connection connection = null;

        connection = getDBConnection(dbName);
        ArrayList<String> result = new ArrayList<>();

        try {

            statement = connection.prepareCall(query);
            statement.setString(1, firstname);
            boolean res = statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String forResult = resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getDate(3) + " " + resultSet.getInt(4);
                result.add(forResult);
                System.out.println(forResult);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void deleteTable3(String dbName, String firstname) throws SQLException {
        String query = "{call delete_select_element_table3(?)}";
        CallableStatement statement = null;
        Connection connection = null;

        connection = getDBConnection(dbName);
        ArrayList<String> result = new ArrayList<>();

        try {

            statement = connection.prepareCall(query);
            statement.setString(1, firstname);
            boolean res = statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String forResult = resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getInt(5);
                result.add(forResult);
                System.out.println(forResult);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void deleteTable4(String dbName, int firstname) throws SQLException {
        String query = "{call delete_select_element_table4(?)}";
        CallableStatement statement = null;
        Connection connection = null;

        connection = getDBConnection(dbName);
        ArrayList<String> result = new ArrayList<>();

        try {

            statement = connection.prepareCall(query);
            statement.setInt(1, firstname);
            boolean res = statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String forResult = resultSet.getInt(1) + " " + resultSet.getInt(2);
                result.add(forResult);
                System.out.println(forResult);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    //Обновление кортежа
    public void updateTable4(String dbName,int tableName)throws SQLException{
        String query = "{call update_table4(?)}";
        CallableStatement statement = null;
        Connection connection = null;

        connection = getDBConnection(dbName);
        ArrayList<String> result=new ArrayList<>();

        try {

            statement = connection.prepareCall(query);
            statement.setInt(1,tableName);


            System.out.println("Executing procedure...");
            System.out.println("РЎС‚РѕР»Р±РµС† loc_id РІ С‚Р°Р±Р»РёС†Рµ locations РёР·РјРµРЅРµРЅ!");
            statement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void updateTable3(String dbName,int tableName)throws SQLException{
        String query = "{call update_table3(?)}";
        CallableStatement statement = null;
        Connection connection = null;

        connection = getDBConnection(dbName);
        ArrayList<String> result=new ArrayList<>();

        try {

            statement = connection.prepareCall(query);
            statement.setInt(1,tableName);


            System.out.println("Executing procedure...");
            System.out.println("РЎС‚РѕР»Р±РµС† lect_id РІ С‚Р°Р±Р»РёС†Рµ lecturer РёР·РјРµРЅРµРЅ!");
            statement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void updateTable2(String dbName,int tableName)throws SQLException{
        String query = "{call update_table2(?)}";
        CallableStatement statement = null;
        Connection connection = null;

        connection = getDBConnection(dbName);
        ArrayList<String> result=new ArrayList<>();

        try {

            statement = connection.prepareCall(query);
            statement.setInt(1,tableName);


            System.out.println("Executing procedure...");
            System.out.println("РЎС‚РѕР»Р±РµС† lection_id РІ С‚Р°Р±Р»РёС†Рµ lections РёР·РјРµРЅРµРЅ!");
            statement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void updateTable1(String dbName,int tableName)throws SQLException{
        String query = "{call update_table1(?)}";
        CallableStatement statement = null;
        Connection connection = null;

        connection = getDBConnection(dbName);
        ArrayList<String> result=new ArrayList<>();

        try {

            statement = connection.prepareCall(query);
            statement.setInt(1,tableName);


            System.out.println("Executing procedure...");
            System.out.println("РЎС‚РѕР»Р±РµС† listener_id РІ С‚Р°Р±Р»РёС†Рµ listeners РёР·РјРµРЅРµРЅ!");
            statement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


    //В задании нет удаления таблицы, но вдруг пригодиться
    public void dropTable1(String dbName, String tableName) throws SQLException {
        String query = "{call drop_table1(?)}";
        CallableStatement statement = null;
        Connection connection = null;

        connection = getDBConnection(dbName);
        ArrayList<String> result = new ArrayList<>();

        try {

            statement = connection.prepareCall(query);
            statement.setString(1, tableName);


            System.out.println("Executing procedure...");
            System.out.println("Таблица listeners удалена!");
            statement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    //Начальные данные для таблиц
    public void fillUpData() throws SQLException {
        String query = "INSERT INTO locations VALUES (1,\t100);\n" +
                "INSERT INTO locations VALUES (2,\t200);\n" +
                "INSERT INTO lecturer VALUES (101,\t'Frederic',\t'Bobo',\t'Math',\t1);\n" +
                "INSERT INTO lecturer VALUES (202,\t'Putrocho',\t'Glantiano',\t'Biology',\t1);\n" +
                "INSERT INTO lecturer VALUES (303,\t'Guanxi',\t'Outer',\t'Mystic',\t2);\n" +
                "INSERT INTO lecturer VALUES (404,\t'Pedro',\t'Redstone',\t'Explorations',\t2);\n" +
                "INSERT INTO lecturer VALUES (505,\t'Michael',\t'Depp',\t'Technics',\t1);\n" +
                "INSERT INTO lections VALUES (1,'Mystic Stories', '2017-02-17', 303);\n" +
                "INSERT INTO lections VALUES (2, 'Around the world', '2019-12-20', 404);\n" +
                "INSERT INTO lections VALUES (3, 'Deep learning', '2018-01-30',\t101);\n" +
                "INSERT INTO listeners VALUES (1, '89123241545','Serhito', 'ser99@gmail.com', 20,1 );\n" +
                "INSERT INTO listeners VALUES (2, '89533001589','Petr', 'nagib14@yandex.ru', 19,1 );\n" +
                "INSERT INTO listeners VALUES (3,'89127278578','Vanya','XXXgotIT@mail.ru', 22,1);\n" +
                "INSERT INTO listeners VALUES (4, '89123212121',\t'Lena',\t'Lena@mail.ru',\t23,\t2);\n" +
                "INSERT INTO listeners VALUES (5, '89123006583',\t'Kate',\t'kateBIG@gmail.com', 40, 3);";

        Connection connection = null;
        PreparedStatement callableStatement = null;

        connection = getDBConnection(nameDataBase);

        try {
            callableStatement = connection.prepareStatement(query);
            //callableStatement.registerOutParameter(1,Types.VARCHAR);
            System.out.println("Executing procedure...");
            callableStatement.execute();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    //Занесение нужных процедур в БД
    public void fillUpFunctions() throws SQLException {
        String query = "\n" +
                "CREATE OR REPLACE FUNCTION create_table4(t_name varchar(30))\n" +
                "  RETURNS void \n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "EXECUTE format('    \n" +
                "   CREATE TABLE IF NOT EXISTS %I (\n" +
                "    loc_id INT PRIMARY KEY,\n" +
                "    capacity INT)', t_name);\n" +
                "\n" +
                "END\n" +
                "$$ LANGUAGE plpgsql;\n" +
                "\n" +
                "\n" +
                "CREATE OR REPLACE FUNCTION create_table3(t_name varchar(30))\n" +
                "  RETURNS void \n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "EXECUTE format('    \n" +
                "   CREATE TABLE IF NOT EXISTS %I (\n" +
                "    lect_id INT PRIMARY KEY,\n" +
                "    firstname VARCHAR(30),\n" +
                "    surname VARCHAR(30),\n" +
                "    specialization VARCHAR(30),\n" +
                "    loc_id INT REFERENCES locations (loc_id) ON UPDATE CASCADE ON DELETE CASCADE )', t_name);\n" +
                "\n" +
                "END\n" +
                "$$ LANGUAGE plpgsql;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "CREATE OR REPLACE FUNCTION create_table2(t_name varchar(30))\n" +
                "  RETURNS void \n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "EXECUTE format('    \n" +
                "   CREATE TABLE IF NOT EXISTS %I (\n" +
                "    lection_id INT PRIMARY KEY,\n" +
                "    name VARCHAR(30),\n" +
                "    date DATE,\n" +
                "    lect_id INT REFERENCES lecturer (lect_id) ON UPDATE CASCADE ON DELETE CASCADE )', t_name);\n" +
                "\n" +
                "END\n" +
                "$$ LANGUAGE plpgsql;\n" +
                "\n" +
                "\n" +
                "\n" +
                "CREATE OR REPLACE FUNCTION create_table1(t_name varchar(30))\n" +
                "  RETURNS void \n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "EXECUTE format('    \n" +
                "   CREATE TABLE IF NOT EXISTS %I (\n" +
                "    listener_id INT PRIMARY KEY,\n" +
                "    phone VARCHAR,\n" +
                "    firstname VARCHAR(30),\n" +
                "    email VARCHAR(30),\n" +
                "    age INT,\n" +
                "    lection_id INT REFERENCES lections (lection_id) ON UPDATE CASCADE ON DELETE CASCADE)', t_name);\n" +
                "\n" +
                "END\n" +
                "$$ LANGUAGE plpgsql;\n" +
                "\n" +
                "CREATE OR REPLACE FUNCTION insert_table1(t_name VARCHAR(30),listener_id INTEGER,phone VARCHAR(80), \n" +
                "  firstname VARCHAR(80), email VARCHAR(80), age INTEGER,lection_id INTEGER) \n" +
                "  RETURNS void \n" +
                "  AS \n" +
                "  $$ \n" +
                "  BEGIN \n" +
                "\n" +
                "  EXECUTE format(' \n" +
                "  INSERT INTO %I( VALUES ($1,$2, $3, $4, $5, $6) )',t_name) \n" +
                "  using listener_id,phone, \n" +
                "  firstname, email, age,lection_id; \n" +
                "\n" +
                "\n" +
                "  END \n" +
                "  $$ LANGUAGE plpgsql VOLATILE \n" +
                "  COST 100;\n" +
                "\n" +
                "\n" +
                "CREATE or REPLACE FUNCTION select_table1() \n" +
                "  RETURNS TABLE(listener_id1 INTEGER, phone1 varchar(80), \n" +
                "  firstname1 VARCHAR(80), email1 VARCHAR(80), age1 INTEGER, lection_id1 INTEGER) \n" +
                "  AS $$ \n" +
                "  BEGIN \n" +
                "  RETURN QUERY \n" +
                "\n" +
                "  SELECT * FROM listeners; \n" +
                "\n" +
                "\n" +
                "  END \n" +
                "  $$ LANGUAGE plpgsql VOLATILE \n" +
                "  COST 100;" +
                "CREATE or REPLACE FUNCTION delete_table1()\n" +
                "    RETURNS void\n" +
                "\n" +
                "    AS $$\n" +
                "    BEGIN\n" +
                "\n" +
                "    DELETE\n" +
                "    FROM listeners;\n" +
                "\n" +
                "    END\n" +
                "    $$ LANGUAGE plpgsql VOLATILE\n" +
                "    COST 100;" +
                "CREATE or REPLACE FUNCTION search_table1(IN firstname2 VARCHAR(80))\n" +
                "RETURNS TABLE(listener_id1 INTEGER, phone1 varchar(80),\n" +
                "firstname1 VARCHAR(80), email1 VARCHAR(80), age1 INTEGER, lection_id1 INTEGER)\n" +
                "\n" +
                "AS $$\n" +
                "BEGIN\n" +
                "RETURN QUERY\n" +
                "\n" +
                "SELECT listeners.listener_id,listeners.phone,\n" +
                "listeners.firstname, listeners.email, listeners.age,\n" +
                "listeners.lection_id\n" +
                "FROM listeners WHERE listeners.firstname=firstname2;\n" +
                "\n" +
                "\n" +
                "END\n" +
                "$$ LANGUAGE plpgsql VOLATILE\n" +
                "COST 100;" +
                "CREATE or REPLACE FUNCTION delete_select_element_table1(IN firstname2 VARCHAR(80))\n" +
                "RETURNS void\n" +
                "\n" +
                "AS $$\n" +
                "BEGIN\n" +
                "\n" +
                "DELETE\n" +
                "FROM listeners WHERE listeners.firstname=firstname2;\n" +
                "\n" +
                "END\n" +
                "$$ LANGUAGE plpgsql VOLATILE\n" +
                "COST 100;" +
                "CREATE OR REPLACE FUNCTION drop_table1(t_name VARCHAR(30))\n" +
                "RETURNS void\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "EXECUTE format('\n" +
                "DROP TABLE %I',t_name);\n" +
                "\n" +
                "\n" +
                "\n" +
                "END\n" +
                "$$ LANGUAGE plpgsql VOLATILE\n" +
                "COST 100;" +
                "CREATE or REPLACE FUNCTION select_table2() \n" +
                "  RETURNS TABLE(lection_id1 INTEGER, name1 varchar(80), \n" +
                "  date1 DATE, lect_id1 INTEGER) \n" +
                "  AS $$ \n" +
                "  BEGIN \n" +
                "  RETURN QUERY \n" +
                "\n" +
                "  SELECT * FROM lections; \n" +
                "\n" +
                "\n" +
                "  END \n" +
                "  $$ LANGUAGE plpgsql VOLATILE \n" +
                "  COST 100;" +
                "CREATE or REPLACE FUNCTION select_table3() \n" +
                "  RETURNS TABLE(lect_id1 INTEGER, firstname1 varchar(80), surname1 varchar(80), specialization1 varchar(80),\n" +
                "  loc_id1 INTEGER) \n" +
                "  AS $$ \n" +
                "  BEGIN \n" +
                "  RETURN QUERY \n" +
                "\n" +
                "  SELECT * FROM lecturer; \n" +
                "\n" +
                "\n" +
                "  END \n" +
                "  $$ LANGUAGE plpgsql VOLATILE \n" +
                "  COST 100;" +
                "CREATE or REPLACE FUNCTION select_table4() \n" +
                "    RETURNS TABLE(loc_id1 INTEGER, capacity1 INTEGER) \n" +
                "    AS $$ \n" +
                "    BEGIN \n" +
                "    RETURN QUERY \n" +
                "\n" +
                "    SELECT * FROM locations; \n" +
                "\n" +
                "\n" +
                "    END \n" +
                "    $$ LANGUAGE plpgsql VOLATILE \n" +
                "    COST 100;" +
                "CREATE OR REPLACE FUNCTION insert_table2(t_name VARCHAR(30),lection_id INTEGER,lname VARCHAR(200),\n" +
                "ldate DATE,lec_id INTEGER)\n" +
                "RETURNS void\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "EXECUTE format('\n" +
                "INSERT INTO %I( VALUES ($1,$2, $3, $4) )',t_name)\n" +
                "using lection_id,lname,ldate,lec_id;\n" +
                "\n" +
                "\n" +
                "END\n" +
                "$$ LANGUAGE plpgsql VOLATILE\n" +
                "COST 100;" +
                "CREATE OR REPLACE FUNCTION insert_table3(t_name VARCHAR(30),lec_id INTEGER,firstname VARCHAR(100),surname VARCHAR(150),\n" +
                "specialization VARCHAR(100),loc_id INTEGER)\n" +
                "RETURNS void\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "EXECUTE format('\n" +
                "INSERT INTO %I( VALUES ($1,$2, $3, $4, $5) )',t_name)\n" +
                "using lec_id,firstname,surname,specialization,loc_id;\n" +
                "\n" +
                "\n" +
                "END\n" +
                "$$ LANGUAGE plpgsql VOLATILE\n" +
                "COST 100;" +
                "CREATE OR REPLACE FUNCTION insert_table4(t_name VARCHAR(30),loc_id INTEGER,capacity INTEGER)\n" +
                "RETURNS void\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "EXECUTE format('\n" +
                "INSERT INTO %I( VALUES ($1,$2) )',t_name)\n" +
                "using loc_id,capacity;\n" +
                "\n" +
                "\n" +
                "END\n" +
                "$$ LANGUAGE plpgsql VOLATILE\n" +
                "COST 100;" +
                "CREATE or REPLACE FUNCTION search_table2(IN firstname2 VARCHAR(80))\n" +
                "    RETURNS TABLE(lection_id1 INTEGER,lname1 VARCHAR(200),\n" +
                "    ldate1 DATE,lect_id1 INTEGER)\n" +
                "\n" +
                "    AS $$\n" +
                "    BEGIN\n" +
                "    RETURN QUERY\n" +
                "\n" +
                "    SELECT lections.lection_id, lections.name,\n" +
                "    lections.date, lections.lect_id\n" +
                "    FROM lections WHERE lections.name=firstname2;\n" +
                "\n" +
                "\n" +
                "    END\n" +
                "    $$ LANGUAGE plpgsql VOLATILE\n" +
                "    COST 100;\n" +
                "\n" +
                "CREATE or REPLACE FUNCTION search_table3(IN firstname2 VARCHAR(80))\n" +
                "    RETURNS TABLE(lect_id1 INTEGER,firstname1 VARCHAR(200),\n" +
                "    surname1 VARCHAR(100), specialization1 VARCHAR(100),loc_id INTEGER)\n" +
                "\n" +
                "    AS $$\n" +
                "    BEGIN\n" +
                "    RETURN QUERY\n" +
                "\n" +
                "    SELECT lecturer.lect_id, lecturer.firstname,\n" +
                "    lecturer.surname, lecturer.specialization, lecturer.loc_id\n" +
                "    FROM lecturer WHERE lecturer.firstname=firstname2;\n" +
                "\n" +
                "\n" +
                "    END\n" +
                "    $$ LANGUAGE plpgsql VOLATILE\n" +
                "    COST 100;\n" +
                "\n" +
                "\n" +
                "CREATE or REPLACE FUNCTION search_table4(IN firstname2 INTEGER)\n" +
                "    RETURNS TABLE(loc_id INTEGER,capacity INTEGER)\n" +
                "\n" +
                "    AS $$\n" +
                "    BEGIN\n" +
                "    RETURN QUERY\n" +
                "\n" +
                "    SELECT locations.loc_id, locations.capacity\n" +
                "\n" +
                "    FROM locations WHERE locations.capacity=firstname2;\n" +
                "\n" +
                "\n" +
                "    END\n" +
                "    $$ LANGUAGE plpgsql VOLATILE\n" +
                "    COST 100;" +
                "CREATE or REPLACE FUNCTION delete_all_element_table2()\n" +
                "RETURNS void\n" +
                "\n" +
                "AS $$ \n" +
                "BEGIN \n" +
                "\n" +
                "DELETE\n" +
                " FROM lections;\n" +
                "\n" +
                "END\n" +
                "$$ LANGUAGE plpgsql VOLATILE\n" +
                "  COST 100;\n" +
                "\n" +
                "\n" +
                "CREATE or REPLACE FUNCTION delete_all_element_table3()\n" +
                "RETURNS void\n" +
                "\n" +
                "AS $$ \n" +
                "BEGIN \n" +
                "\n" +
                "DELETE\n" +
                " FROM lecturer;\n" +
                "\n" +
                "END\n" +
                "$$ LANGUAGE plpgsql VOLATILE\n" +
                "  COST 100;\n" +
                "\n" +
                "\n" +
                "CREATE or REPLACE FUNCTION delete_all_element_table4()\n" +
                "RETURNS void\n" +
                "\n" +
                "AS $$ \n" +
                "BEGIN \n" +
                "\n" +
                "DELETE\n" +
                " FROM locations;\n" +
                "\n" +
                "END\n" +
                "$$ LANGUAGE plpgsql VOLATILE\n" +
                "  COST 100;" +
                "CREATE or REPLACE FUNCTION delete_select_element_table2(IN firstname2 VARCHAR(80))\n" +
                "RETURNS void\n" +
                "\n" +
                "AS $$ \n" +
                "BEGIN \n" +
                "\n" +
                "DELETE\n" +
                " FROM lections WHERE lections.name=firstname2;\n" +
                "\n" +
                "END\n" +
                "$$ LANGUAGE plpgsql VOLATILE\n" +
                " COST 100;\n" +
                "\n" +
                "\n" +
                "\n" +
                "CREATE or REPLACE FUNCTION delete_select_element_table3(IN firstname2 VARCHAR(80))\n" +
                "RETURNS void\n" +
                "\n" +
                "AS $$ \n" +
                "BEGIN \n" +
                "\n" +
                "DELETE\n" +
                " FROM lecturer WHERE lecturer.firstname=firstname2;\n" +
                "\n" +
                "END\n" +
                "$$ LANGUAGE plpgsql VOLATILE\n" +
                " COST 100;\n" +
                "\n" +
                "\n" +
                "\n" +
                "CREATE or REPLACE FUNCTION delete_select_element_table4(IN firstname2 INTEGER)\n" +
                "RETURNS void\n" +
                "\n" +
                "AS $$ \n" +
                "BEGIN \n" +
                "\n" +
                "DELETE\n" +
                " FROM locations WHERE locations.capacity=firstname2;\n" +
                "\n" +
                "END\n" +
                "$$ LANGUAGE plpgsql VOLATILE\n" +
                " COST 100;" +
                "CREATE OR REPLACE FUNCTION update_table4(t_name INTEGER)\n" +
                "  RETURNS void \n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "\n" +
                "   UPDATE locations SET loc_id=t_name WHERE loc_id=2;\n" +
                "  \n" +
                "\n" +
                "\n" +
                "END\n" +
                "$$ LANGUAGE plpgsql VOLATILE\n" +
                "  COST 100;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "CREATE OR REPLACE FUNCTION update_table3(t_name INTEGER)\n" +
                "  RETURNS void \n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "\n" +
                "   UPDATE lecturer SET lect_id=t_name WHERE lect_id=101;\n" +
                "  \n" +
                "\n" +
                "\n" +
                "END\n" +
                "$$ LANGUAGE plpgsql VOLATILE\n" +
                "  COST 100;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "CREATE OR REPLACE FUNCTION update_table2(t_name INTEGER)\n" +
                "  RETURNS void \n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "\n" +
                "   UPDATE lections SET lection_id=t_name WHERE lection_id=3;\n" +
                "  \n" +
                "\n" +
                "\n" +
                "END\n" +
                "$$ LANGUAGE plpgsql VOLATILE\n" +
                "  COST 100;\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "CREATE OR REPLACE FUNCTION update_table1(t_name INTEGER)\n" +
                "  RETURNS void \n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "\n" +
                "   UPDATE listeners SET listener_id=t_name WHERE listener_id=4;\n" +
                "  \n" +
                "\n" +
                "\n" +
                "END\n" +
                "$$ LANGUAGE plpgsql VOLATILE\n" +
                "  COST 100;";

        Connection connection = null;
        PreparedStatement callableStatement = null;

        connection = getDBConnection(nameDataBase);

        try {
            callableStatement = connection.prepareStatement(query);
            //callableStatement.registerOutParameter(1,Types.VARCHAR);
            System.out.println("Executing procedure...");
            callableStatement.execute();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
