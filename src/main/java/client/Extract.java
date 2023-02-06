package client;

import order.OrderDetailsResponse;
import order.OrdersResponse;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class Extract {

    @Step("Extract status code")
    public int responseCode(ValidatableResponse response) {
        return response
                .extract()
                .statusCode();
    }

    @Step("Extract response text")
    public String responseMessage(ValidatableResponse response) {
        return response
                .extract()
                .path("message");
    }

    @Step("Is true in response body")
    public boolean responseValueIsTrue(ValidatableResponse response) {
        return response
                .extract()
                .path("ok");
    }

    @Step("Extract Id")
    public int id(ValidatableResponse response) {
        return response
                .extract()
                .path("id");
    }

    @Step("Extract track")
    public int track(ValidatableResponse response) {
        return response
                .extract()
                .path("track");
    }

    @Step("Extract id of the order")
    public int orderId(ValidatableResponse response) {
        return response
                .extract()
                .path("order.id");
    }

    @Step("Get list of orders")
    public OrdersResponse ordersResponseBody(ValidatableResponse response) {
        return response
                .extract()
                .body().as(OrdersResponse.class);
    }

    @Step("Get details of order")
    public OrderDetailsResponse orderDetailsBody(ValidatableResponse response) {
        return response
                .extract()
                .body().as(OrderDetailsResponse.class);
    }
}
