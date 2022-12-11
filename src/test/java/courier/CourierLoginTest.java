package courier;

import client.Extract;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CourierLoginTest {

    private int responseCode;
    private int courierId;

    private final Courier courier = Courier.randomCourier();
    private final CourierClient courierClient = new CourierClient();
    private final Extract extract = new Extract();

    @Test
    @DisplayName("Check possibility of creating a courier")
    @Description("Checking with random alphabetic parameters for JSON body")
    public void checkCourierLogin() {

        courierClient.create(courier);

        ValidatableResponse login = courierClient.login(courier.getLogin(), courier.getPassword());

        responseCode = extract.responseCode(login);
        courierId = extract.getIntValue(login, "id");

        assertEquals(200, responseCode);
        assertTrue(courierId > 0);
    }

    @Test
    @DisplayName("Check possibility of creating a courier")
    @Description("Checking with random alphabetic parameters for JSON body")
    public void checkNonexistentCourierLogin() {

        ValidatableResponse login = courierClient.login(courier.getLogin(), courier.getPassword());

        responseCode = extract.responseCode(login);
        String responseMessage = extract.responseMessage(login);

        assertEquals(404, responseCode);
        assertEquals("Учетная запись не найдена", responseMessage);
    }

    @After
    public void deleteCourier() {

        if (courierId > 0) {
            courierClient.deleteCourier(courierId);
        }
    }
}
