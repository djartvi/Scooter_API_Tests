package order;

import client.Extract;
import courier.*;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderAcceptTest {

    private final String orderId;
    private final String courierId;
    private final int expectedCode;

    private static final Courier courier = Courier.randomCourier();
    private static final Extract extract = new Extract();
    private static final OrderClient orderClient = new OrderClient();
    private static final Order order = Order.randomOrder(List.of(""));

    private static final CourierClient courierClient = new CourierClient();
    private static final ValidatableResponse create = courierClient.create(courier);
    private static final ValidatableResponse login = courierClient.login(courier.getLogin(), courier.getPassword());
    private static final int defaultCourierId = extract.getIntValue(login, "id");

    private static final ValidatableResponse createOrder = orderClient.create(order);
    private static final int orderTrack = extract.getIntValue(createOrder, "track");
    private static final ValidatableResponse orderDetails = orderClient.getOrderDetails(orderTrack);
    private static final int defaultOrderId = extract.getIntValue(orderDetails, "order.id");

    public OrderAcceptTest(String orderId, String courierId, int expectedCode) {
        this.orderId = orderId;
        this.courierId = courierId;
        this.expectedCode = expectedCode;
    }

    @Parameterized.Parameters(name = "order {0}, courier {1}, code {2}")
    public static Object[][] endpointParameters() {
        return new Object[][] {
                {"" + defaultOrderId, "" + defaultCourierId, 200},
                {"", "" + defaultCourierId, 400},
                {"" + defaultOrderId, "", 400},
                {"0", "" + defaultCourierId, 404},
                {"" + defaultOrderId, "0", 404},
        };
    }

    @Test
    @DisplayName("Check possibility of creating a courier without required json values")
    @Description("Checking with random alphabetic parameters")
    public void acceptOrderWithRequiredEndpointParameters() {

        ValidatableResponse acceptOrder = orderClient.acceptOrder(orderId, courierId);

        int responseCode = extract.responseCode(acceptOrder);

        assertEquals(expectedCode, responseCode);
    }

    @AfterClass
    public static void deleteCourier() {
        courierClient.deleteCourier(defaultCourierId);
    }
}
