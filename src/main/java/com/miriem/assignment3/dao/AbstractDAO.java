package com.miriem.assignment3.dao;

import com.miriem.assignment3.connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides basic CRUD frnctionality for a given table
 * @param <T>  reference to a table in a database
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private Class<?> type;

    /**
     * Retrieves the class type for the generic argument
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * Builds the query for selecting elements with a where clause
     * @param field  Field for the WHERE clause
     * @return A string containing the query
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("sql." + type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Build a query for selecting all the rows from a table
     * @return a string containing the query
     */
    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append("sql." + type.getSimpleName());
        return sb.toString();
    }


    /**
     * Builds a query for inserting new rows
     * @return A string containing the query
     */
    private String createInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT ");
        sb.append(" INTO ");
        sb.append("sql." + type.getSimpleName());
        sb.append(" VALUES( ");
        for (int i = 0; i < type.getDeclaredFields().length; i++) {
            sb.append("?,");
        }
        sb.setLength(sb.length() - 1);
        sb.append(")");
        return sb.toString();
    }

    /**
     * Builds a query for updating rows
     * @param field Field for the WHERE clause
     * @return A string containing the query
     */
    private String createUpdateQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        for (Field f : type.getDeclaredFields()) {
            sb.append(f.getName() + "=");
            sb.append("?,");
        }
        sb.setLength(sb.length() - 1);
        sb.append(" WHERE " + field);
        sb.append("=?");
        return sb.toString();
    }

    /**
     * Builds a query for deleting a row
     * @param field  Field for the WHERE clause
     * @return String containing the query
     */
    private String createDeleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append("sql." + type.getSimpleName());
        sb.append(" WHERE " + field);
        sb.append("=?");
        return sb.toString();
    }


    /**
     * Selects the row with the searched id
     * @param id Id to find
     * @return The object with the corresponding. If not found, null.
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            rs = statement.executeQuery();

            return createObjects(rs).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


    /**
     * Finds the object containing the searched name, uses LIKE clause
     * @param name  Any part of a name to be searched
     * @return All the rows containing that name.
     */
    public T findbyName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = createSelectQuery("name");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            rs = statement.executeQuery();
            List<T> objects = createObjects(rs);
            if (objects.size() > 0)
                return objects.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Retrieves all records from the table
     *
     * @return list of records of type T
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


    /**
     * Deletes a row
     * @param id  Id of the object to be deleted
     */
    public void deleteById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createDeleteQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:deleteById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Inserts a new row
     * @param t  Object to be inserted
     * @return Object inserted
     */
    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery();
        try {
            int index = 1;
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            for (Field f : type.getDeclaredFields()) {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(f.getName(), type);
                Method method = propertyDescriptor.getReadMethod();
                Object s = method.invoke(t);
                statement.setObject(index, s);
                index++;
            }
            statement.executeUpdate();
            return t;
        } catch (SQLException | IntrospectionException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            LOGGER.log(Level.WARNING, "DAO:insert " + e.getClass() + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Updates an object
     * @param t Object to be updated
     * @return Object updated
     */
    public T update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery("id");
        try {
            int index = 1;
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            for (Field f : type.getDeclaredFields()) {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(f.getName(), type);
                Method method = propertyDescriptor.getReadMethod();
                Object s = method.invoke(t);
                statement.setObject(index, s);
                index++;
            }
            Object object = new PropertyDescriptor(type.getDeclaredFields()[0].getName(), type).getReadMethod().invoke(t);
            statement.setObject(index, object);
            statement.executeUpdate();
            return t;
        } catch (SQLException | IntrospectionException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            LOGGER.log(Level.WARNING, "DAO:update " + e.getClass() + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Given a resultSet, creates a list of records
     *
     * @param resultSet result set from database
     * @return list of records from the result set
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                T instance = (T) type.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }
}