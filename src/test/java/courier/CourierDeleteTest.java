package courier;

import client.Extract;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CourierDeleteTest {

    private int courierId;
    private int responseCode;

    private final Courier courier = Courier.randomCourier();
    private final CourierClient courierClient = new CourierClient();
    private final Extract extract = new Extract();

    @Test
    @DisplayName("Possibility of deleting a courier")
    @Description("Checking response code and json value")
    public void checkCourierDeleting() {

        courierClient.create(courier);
        ValidatableResponse login = courierClient.login(courier.getLogin(), courier.getPassword());

        courierId = extract.id(login);

        ValidatableResponse deleteCourier = courierClient.deleteCourier(courierId);

        responseCode = extract.responseCode(deleteCourier);

        assertEquals(200, responseCode);
        assertTrue(extract.responseValueIsTrue(deleteCourier));
    }

    @Test
    @DisplayName("Deleting a courier without id")
    @Description("Check possibility of deleting a courier without id in request")
    public void checkCourierDeletingWithoutId() {

        courierClient.create(courier);
        ValidatableResponse login = courierClient.login(courier.getLogin(), courier.getPassword());

        courierId = extract.id(login);

        ValidatableResponse deleteCourier = courierClient.deleteCourierWithoutId(courierId);

        responseCode = extract.responseCode(deleteCourier);

        courierClient.deleteCourier(courierId);

        assertEquals(400, responseCode);
        assertEquals("Недостаточно данных для удаления курьера", extract.responseMessage(deleteCourier));
    }

    @Test
    @DisplayName("Possibility of deleting nonexistent courier")
    @Description("Checking response code and json value")
    public void checkNonexistentCourierDeleting() {

        courierClient.create(courier);
        ValidatableResponse login = courierClient.login(courier.getLogin(), courier.getPassword());

        courierId = extract.id(login);

        courierClient.deleteCourier(courierId);

        ValidatableResponse deleteNonexistentCourier = courierClient.deleteCourier(courierId);

        responseCode = extract.responseCode(deleteNonexistentCourier);

        assertEquals(404, responseCode);
        assertEquals("Курьера с таким id нет", extract.responseMessage(deleteNonexistentCourier));
    }
}
