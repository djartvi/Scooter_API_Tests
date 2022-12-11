package Courier;

import Client.Extract;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CourierCreateTest {

    private int responseCode;

    private final Courier courier = Courier.randomCourier();
    private final CourierClient courierClient = new CourierClient();
    private final Extract extract = new Extract();

    @Test
    @DisplayName("Check possibility of creating a courier")
    @Description("Checking with random alphabetic parameters for JSON body")
    public void checkCourierCreating() {

        ValidatableResponse response = courierClient.create(courier);

        responseCode = extract.responseCode(response);

        assertEquals(201, responseCode);
        assertTrue(extract.responseValueIsTrue(response));
    }

    @Test
    @DisplayName("Check possibility of creating an equal courier")
    @Description("Checking with random alphabetic parameters for JSON body")
    public void checkExistingCourierCreating() {

        courierClient.create(courier);

        ValidatableResponse createSecondCourier = courierClient.create(courier);

        String actual = extract.responseMessage(createSecondCourier);
        responseCode = extract.responseCode(createSecondCourier);

        assertEquals(409, responseCode);
        assertEquals("Этот логин уже используется", actual);
    }

    @After
    public void deleteCourier() {
        ValidatableResponse login = courierClient.login(courier.getLogin(), courier.getPassword());
        courierClient.deleteCourier(extract.getIntValue(login, "id"));
    }

}
