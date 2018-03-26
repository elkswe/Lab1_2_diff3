package catalog.dao.impl;

public final class SQLQueryPool {
    public static final String SQL_SELECT_USER_ALL = "SELECT ID, Role, Username, PassHash, LastActivity, DayTraffic, AllTraffic FROM Users";
    public static final String SQL_SELECT_USER_BY_ID = "SELECT ID, Role, Username, PassHash, LastActivity, DayTraffic, AllTraffic FROM Users WHERE ID = ?";
    public static final String SQL_SELECT_USER_BY_USERNAME = "SELECT ID, Role, Username, PassHash, LastActivity, DayTraffic, AllTraffic FROM Users WHERE Username = ?";
    public static final String SQL_SELECT_PASSHASH_BY_USERNAME = "SELECT PassHash FROM Users WHERE Username = ?";
    public static final String SQL_SELECT_USERNAME_BY_USERNAME = "SELECT Username FROM Users WHERE Username = ?";
    public static final String SQL_INSERT_USER = "INSERT INTO Users (Role, Username, PassHash, LastActivity, DayTraffic, AllTraffic) values (?, ?, ?, ?, ?, ?)";
    public static final String SQL_UPDATE_USER = "";

    public static final String SQL_USER_ID = "ID";
    public static final String SQL_USER_ROLE = "Role";
    public static final String SQL_USER_USERNAME = "Username";
    public static final String SQL_USER_PASSHASH = "PassHash";
    public static final String SQL_USER_LAST_ACTIVITY = "LastActivity";
    public static final String SQL_USER_DAY_TRAFFIC = "DayTraffic";
    public static final String SQL_USER_ALL_TRAFFIC = "AllTraffic";

    SQLQueryPool() {
    }

}
