package daos;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface Dao<T> {
    public User findById(int id) throws SQLException;
    public Set<User> findAll() throws SQLException;
    public T update(User user) throws SQLException;
    public T create(User user ) throws SQLException;
    public void delete(int id) throws SQLException;
}
