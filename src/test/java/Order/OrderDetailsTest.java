package Order;

import Client.Extract;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDetailsTest {

    private int responseCode;

    private final OrderClient orderClient = new OrderClient();
    private final Extract extract = new Extract();
    private final Order order = Order.randomOrder(List.of(""));

    @Test
    @DisplayName("Check possibility of creating a courier")
    @Description("Checking with random alphabetic parameters for JSON body")
    public void checkGettingOrderDetails() {

        ValidatableResponse createOrder = orderClient.create(order);

        int orderTrack = extract.getIntValue(createOrder, "track");

        ValidatableResponse ordersDetails = orderClient.getOrderDetails(orderTrack);

        responseCode = extract.responseCode(ordersDetails);
        OrderDetailsResponse orderDetailsResponse = extract.getOrderDetailsBody(ordersDetails);

        assertEquals(200, responseCode);
        assertEquals(orderTrack, orderDetailsResponse.getOrders().getTrack());
    }

    @Test
    @DisplayName("Check possibility of creating a courier")
    @Description("Checking with random alphabetic parameters for JSON body")
    public void checkGettingNonexistentOrder() {

        orderClient.create(order);
        ValidatableResponse ordersDetails = orderClient.getOrderDetails(0);

        responseCode = extract.responseCode(ordersDetails);
        String message = extract.responseMessage(ordersDetails);

        assertEquals(404, responseCode);
        assertEquals("Заказ не найден", message);
    }

    @Test
    @DisplayName("Check possibility of creating a courier")
    @Description("Checking with random alphabetic parameters for JSON body")
    public void checkGettingOrderWithoutTrack() {

        orderClient.create(order);
        ValidatableResponse ordersDetails = orderClient.getOrderDetailsWithoutTrack("");

        responseCode = extract.responseCode(ordersDetails);
        String message = extract.responseMessage(ordersDetails);

        assertEquals(400, responseCode);
        assertEquals("Недостаточно данных для поиска", message);
    }
}
