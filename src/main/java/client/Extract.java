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

    @Step("Check response text")
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

    @Step("Get int value by key")
    public int getIntValue(ValidatableResponse response, String key) {
        return response
                .extract()
                .path(key);
    }

    @Step("Get list of orders")
    public OrdersResponse getOrdersResponseBody(ValidatableResponse response) {
        return response
                .extract()
                .body().as(OrdersResponse.class);
    }

    @Step("Get details of order")
    public OrderDetailsResponse getOrderDetailsBody(ValidatableResponse response) {
        return response
                .extract()
                .body().as(OrderDetailsResponse.class);
    }
}
