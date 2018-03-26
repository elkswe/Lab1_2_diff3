package catalog.dao;

import catalog.model.User;

import java.sql.ResultSet;

public interface UserDao {
    User extractUser(ResultSet resultSet);

}
