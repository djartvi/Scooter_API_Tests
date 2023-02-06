package courier;

import client.Extract;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CourierCreateBodyTest {

    private final String json;
    private int responseCode;

    private static final Courier courier = Courier.randomCourier();
    private final CourierClient courierClient = new CourierClient();
    private final Extract extract = new Extract();

    public CourierCreateBodyTest(String json) {
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
    @Description("Checking with random alphabetic parameters for JSON body")
    public void createCourierWithoutRequiredJSONBody() {

        ValidatableResponse response = courierClient.createCourierWithCustomJSONBody(json);

        responseCode = extract.responseCode(response);
        String actual = extract.responseMessage(response);

        assertEquals(400, responseCode);
        assertEquals("Недостаточно данных для создания учетной записи", actual);
    }

    @After
    public void deleteCourier() {
        if (responseCode == 201) {
            ValidatableResponse login = courierClient.login(json);
            courierClient.deleteCourier(extract.id(login));
        }
    }
}
