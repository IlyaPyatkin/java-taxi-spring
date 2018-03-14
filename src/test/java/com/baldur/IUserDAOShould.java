import model.dao.impl.UserDAO;
import model.dao.interfaces.IUserDAO;
import model.pojo.User;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class IUserDAOShould {
    private static IUserDAO IUserDAO;

    @BeforeClass
    public static void setUpFixture() {
        IUserDAO = new UserDAO();
    }

    @Test
    public void fetchUserFromDB() {
        String username = "test";
        String password = "password";

        IUserDAO.save(new User(username, password));
        User user = IUserDAO.findUser(username, password);

        assertThat(user, notNullValue());
        assertThat(username, equalTo(user.getUsername()));
        assertThat(password, equalTo(user.getPassword()));
    }

    @Test
    public void userDoesNotExist() {
        String username = "nosuchuser";

        assertFalse(IUserDAO.userExists(username));
    }
}
