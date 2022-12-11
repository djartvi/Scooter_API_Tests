package Order;

import Client.RestClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class OrderClient extends RestClient {

    private final String PREFIX = "/api/v1/orders";

    @Step("Creating order")
    public ValidatableResponse create(Order order) {
        return spec()
                .body(order)
                .post(PREFIX)
                .then();
    }

    @Step("Get list of orders")
    public ValidatableResponse getOrdersList() {
        return spec()
                .get(PREFIX)
                .then();
    }

    @Step("Get details of order")
    public ValidatableResponse getOrderDetails(int orderTrack) {
        return spec()
                .queryParam("t", orderTrack)
                .get(PREFIX + "/track")
                .then();
    }

    @Step("Get details of order without sending track")
    public ValidatableResponse getOrderDetailsWithoutTrack(String orderTrack) {
        return spec()
                .queryParam("t", orderTrack)
                .get(PREFIX + "/track")
                .then();
    }

    @Step("Accept order by courier")
    public ValidatableResponse acceptOrder(String orderId, String courierId) {
        return spec()
                .queryParam("courierId", courierId)
                .put(PREFIX + "/accept/" + orderId)
                .then();
    }
}
