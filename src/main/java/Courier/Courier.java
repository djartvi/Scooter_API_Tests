package Courier;

import org.apache.commons.lang3.RandomStringUtils;

public class Courier {

    private final String login;
    private final String password;
    private final String firstName;

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public static Courier randomCourier() {
        String login = RandomStringUtils.randomAlphanumeric(5);
        String password = RandomStringUtils.randomAlphanumeric(5);
        String firstname = RandomStringUtils.randomAlphanumeric(5);

        return new Courier(login, password, firstname);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

}
