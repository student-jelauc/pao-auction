package repository;

import entity.AbstractEntity;
import java.sql.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public abstract class AbstractRepository<Entity extends AbstractEntity> {
    private static Connection connection;

    public abstract Entity getById(int id) throws Exception;

    public abstract void store(Entity entity) throws Exception;

    public abstract void update(Entity entity) throws Exception;

    protected abstract String getTableName();

    protected static Connection getConnection() throws Exception {
        if (connection == null) {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("root");
            dataSource.setPassword("root");
            dataSource.setServerName("localhost");
            dataSource.setDatabaseName("auction_jsp");

            connection = dataSource.getConnection();
        }

        return connection;
    }

    @Override
    protected void finalize() throws Throwable {
        if (connection != null) {
            connection.close();
            connection = null;
        }

        super.finalize();
    }
}
