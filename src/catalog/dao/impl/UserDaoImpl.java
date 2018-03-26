package catalog.dao.impl;

import catalog.dao.UserDao;
import catalog.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static catalog.dao.impl.SQLQueryPool.*;

public class UserDaoImpl extends AbstractDaoImpl<User, Integer> implements UserDao {
    @Override
    public List<User> getAll() {
        List<User> usersList = new ArrayList<>();
        setPreparedStatement(SQL_SELECT_USER_ALL);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                usersList.add(extractUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void create(User user) {
        setPreparedStatement(SQL_INSERT_USER);
        try {
            preparedStatement.setString(1, user.getRole());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassHash());
            preparedStatement.setTimestamp(4, new Timestamp(user.getLastActivity().getTimeInMillis()));
            preparedStatement.setDouble(5, user.getDayTraffic());
            preparedStatement.setDouble(6, user.getAllTraffic());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public User extractUser(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(resultSet.getInt(SQL_USER_ID));
            user.setRole(resultSet.getString(SQL_USER_ROLE));
            user.setUsername(resultSet.getString(SQL_USER_USERNAME));
            user.setPassHash(resultSet.getString(SQL_USER_PASSHASH));
            user.setLastActivity(resultSet.getTimestamp(SQL_USER_LAST_ACTIVITY));
            user.setDayTraffic(resultSet.getDouble(SQL_USER_DAY_TRAFFIC));
            user.setAllTraffic(resultSet.getDouble(SQL_USER_ALL_TRAFFIC));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public String getPassHash(String username) {
        String passHash = null;
        setPreparedStatement(SQL_SELECT_PASSHASH_BY_USERNAME);
        try {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                passHash = resultSet.getString(SQL_USER_PASSHASH);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passHash;
    }

    public User getByUsername(String username) {
        User user = null;
        setPreparedStatement(SQL_SELECT_USER_BY_USERNAME);
        try {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = extractUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean findUsername(String username) {
        setPreparedStatement(SQL_SELECT_USERNAME_BY_USERNAME);
        try {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next())
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
