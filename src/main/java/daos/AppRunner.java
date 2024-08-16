package daos;

import java.sql.SQLException;
import java.util.Set;

public class AppRunner {

    public static void main(String[] args) throws SQLException {
        Dao_Concrete dao = new Dao_Concrete();
        User user = new User(34, "testing name from app", "passowrd123", 66);
        dao.create(user);
        dao.update(new User(34, "updated name","updated password", 44));
        dao.delete(34);
        Set<User> users = dao.findAll();
        for(User u : users){
            System.out.println(u.getName());
        }
    }
}
