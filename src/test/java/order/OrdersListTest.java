package order;

import client.Extract;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrdersListTest {

    private final OrderClient orderClient = new OrderClient();
    private final Extract extract = new Extract();

    @Test
    @DisplayName("Check possibility of creating a courier")
    @Description("Checking with random alphabetic parameters for JSON body")
    public void checkGettingOrders() {

        ValidatableResponse ordersList = orderClient.getOrdersList();

        int responseCode = extract.responseCode(ordersList);
        OrdersResponse orderResponse = extract.ordersResponseBody(ordersList);

        assertEquals(200, responseCode);
        assertTrue(orderResponse.getOrders().size() > 0);
    }
}
