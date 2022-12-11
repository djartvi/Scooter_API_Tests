package Courier;

import Client.Extract;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CourierLoginBodyTest {

    private final String json;
    private static ValidatableResponse login;

    private final static Courier courier = Courier.randomCourier();
    private final static CourierClient courierClient = new CourierClient();
    private final static Extract extract = new Extract();

    public CourierLoginBodyTest(String json) {
        this.json = json;
    }

    @Parameterized.Parameters(name = "JSON: {0}")
    public static Object[] JSONBodyParameters() {
        return new Object[] {
                "{\"login\": \"\", \"password\": \"\"}",
                String.format("{\"login\": \"\", \"password\": \"%s\"}", courier.getPassword()),
                String.format("{\"login\": \"%s\", \"password\": \"\"}", courier.getLogin()),
                String.format("{\"login\": \"%s\"}", courier.getLogin()),
                String.format("{\"password\": \"%s\"}", courier.getPassword()),
                String.format("{\"firstname\": \"%s\"}", courier.getFirstName()),
                String.format("{\"login\": \"%s\", \"firstname\": \"%s\"}", courier.getLogin(), courier.getFirstName()),
                String.format("{\"password\": \"%s\", \"firstname\": \"%s\"}", courier.getPassword(), courier.getFirstName()),
        };
    }

    @Test
    @DisplayName("Check possibility of creating a courier without required json values")
    @Description("Checking with random alphabetic parameters")
    public void loginOfCourierWithoutRequiredJSONBody() {

        courierClient.create(courier);

        login = courierClient.login(json);

        int responseCode = extract.responseCode(login);
        String actual = extract.responseMessage(login);

        assertEquals(400, responseCode);
        assertEquals("Недостаточно данных для входа", actual);
    }

    @AfterClass
    public static void deleteCourier() {
        login = courierClient.login(courier.getLogin(), courier.getPassword());
        courierClient.deleteCourier(extract.getIntValue(login, "id"));
    }
}
