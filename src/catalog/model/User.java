package catalog.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class User extends Entity {
    private String role;
    private String username;
    private String passHash;
    private Calendar lastActivity;
    private double dayTraffic;
    private double allTraffic;

    public User(
            int id,
            String role,
            String username,
            String passHash,
            GregorianCalendar lastActivity,
            double dayTraffic,
            double allTraffic
    ) {
        super(id);
        this.role = role;
        this.username = username;
        this.passHash = passHash;
        this.lastActivity = lastActivity;
        this.dayTraffic = dayTraffic;
        this.allTraffic = allTraffic;
    }

    public User(
            String role,
            String username,
            String passHash
    ) {
        this(0, role, username, passHash, new GregorianCalendar(), 0, 0);
    }

    public User(int id) {
        super(id);
    }

    public User() {
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public Calendar getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Timestamp lastActivity) {
        this.lastActivity = new GregorianCalendar();
        this.lastActivity.setTimeInMillis(lastActivity.getTime());
    }

    public void setDate(Calendar date) {
        this.lastActivity = date;
    }

    public double getDayTraffic() {
        return dayTraffic;
    }

    public void setDayTraffic(double dayTraffic) {
        this.dayTraffic = dayTraffic;
    }

    public double getAllTraffic() {
        return allTraffic;
    }

    public void setAllTraffic(double allTraffic) {
        this.allTraffic = allTraffic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Double.compare(user.dayTraffic, dayTraffic) == 0 &&
                Double.compare(user.allTraffic, allTraffic) == 0 &&
                Objects.equals(role, user.role) &&
                Objects.equals(username, user.username) &&
                Objects.equals(passHash, user.passHash) &&
                Objects.equals(lastActivity, user.lastActivity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), role, username, passHash, lastActivity, dayTraffic, allTraffic);
    }
}
