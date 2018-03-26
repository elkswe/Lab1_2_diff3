package catalog.dao.impl;

import catalog.dao.util.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDaoImpl<E, K> {

    PreparedStatement preparedStatement;
    private SQLConnection SQLConnection;
    private Connection connection;

    AbstractDaoImpl() {
        SQLConnection = new SQLConnection();
        preparedStatement = null;
        connection = null;
    }

    public final void createConnection() {
        if (connection == null) {
            connection = SQLConnection.getConnection();
        }
    }

    public final void closeConnection() {
        if (connection != null) {
            SQLConnection.close();
        }
    }

    public abstract List<E> getAll();

    public abstract E update(E entity);

    public abstract E getById(K id);

    public abstract void delete(K id);

    public abstract void create(E entity);

    void setPreparedStatement(String sql) {
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closePreparedStatement() {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
