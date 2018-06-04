package repository;

import entity.User;

import java.sql.ResultSet;

public class UserRepository extends AbstractRepository<User> {

    @Override
    public User getById(int id) throws Exception {
        ResultSet rs = getConnection().createStatement().executeQuery(
                "select * from " + this.getTableName() + " where id = '" + id + "'"
        );

        while (rs.next()) {
            return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password")
            );
        }

        throw new Exception("Not found user: " + id);
    }

    public User getByUsername(String username) throws Exception {
        ResultSet rs = getConnection().createStatement().executeQuery(
                "select * from " + this.getTableName() + " where username = '" + username + "'"
        );

        while (rs.next()) {
            return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password")
            );
        }

        throw new Exception("Not found user: " + username);
    }

    public void store(User entity) throws Exception {
        String stmt = "INSERT INTO " + this.getTableName() + "(username, password) VALUES ('" + entity.getUsername() + "', '" +
                entity.getPassword() + "')";

        getConnection().createStatement().executeUpdate(stmt);
    }

    @Override
    public void update(User entity) throws Exception {
        String stmt = "UPDATE " + this.getTableName() + "SET name = '" + entity.getUsername() + "', password = '" +
                entity.getPassword() + "' WHERE id = " + entity.getId();
    }

    @Override
    protected String getTableName() {
        return "users";
    }
}
