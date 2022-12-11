package Order;

import Client.Extract;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderCreateTest {

    private final Order order;
    private final OrderClient orderClient = new OrderClient();
    private final Extract extract = new Extract();

    public OrderCreateTest(List<String> color) {
        order = Order.randomOrder(color);
    }

    @Parameterized.Parameters(name = "color: {0}")
    public static Object[] JSONBodyParameters() {
        return new Object[] {
                List.of("BLACK"),
                List.of("GREY"),
                List.of("BLACK", "GREY"),
                List.of(""),
        };
    }

    @Test
    @DisplayName("Check possibility of creating a courier without required json values")
    @Description("Checking with random alphabetic parameters")
    public void createCourierWithoutRequiredJSONBody() {

        ValidatableResponse response = orderClient.create(order);

        int responseCode = extract.responseCode(response);
        int orderTrack = extract.getIntValue(response, "track");

        assertEquals(201, responseCode);
        assertTrue(orderTrack > 0);
    }
}
