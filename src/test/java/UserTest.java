import org.dlpk.User;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void test() {
        User user = new User();
        user.setEmail("abc@gmail.com");
        user.setName("Daniel");
    }

    @Test
    public void addressTest() {
        User user = new User();
        user.addAddress( new User.Address("Endereço A") );
        assertEquals( user.getAddresses().size(), 1 );
        user.removeAddress(new User.Address("Endereço A"));
        assertEquals( user.getAddresses().size(), 0 );
    }

}
