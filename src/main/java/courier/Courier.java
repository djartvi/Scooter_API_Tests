package courier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;

@AllArgsConstructor
@Getter
public class Courier {

    private final String login;
    private final String password;
    private final String firstName;

    public static Courier randomCourier() {
        String login = RandomStringUtils.randomAlphanumeric(5);
        String password = RandomStringUtils.randomAlphanumeric(5);
        String firstname = RandomStringUtils.randomAlphanumeric(5);

        return new Courier(login, password, firstname);
    }
}
