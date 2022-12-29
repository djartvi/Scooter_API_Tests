package courier;

import client.RestClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class CourierClient extends RestClient {

    private final String PREFIX = "api/v1/courier";

    @Step("Create courier")
    public ValidatableResponse create(Courier courier) {
        return spec()
                .body(courier)
                .post(PREFIX)
                .then();
    }

    @Step("Create courier with custom JSON parameters")
    public ValidatableResponse createCourierWithCustomJSONBody(String json) {
        return spec()
                .body(json)
                .post(PREFIX)
                .then();
    }

    @Step("Login of courier")
    public ValidatableResponse login(String login, String password) {
        String json = String.format("{\"login\": \"%s\", \"password\": \"%s\"}", login, password);
        return spec()
                .body(json)
                .post(PREFIX + "/login")
                .then();
    }

    @Step("Login of courier with custom JSON parameters")
    public ValidatableResponse login(String json) {
        return spec()
                .body(json)
                .post(PREFIX + "/login")
                .then();
    }

    @Step("Delete courier")
    public ValidatableResponse deleteCourier(int courierId) {
        String json = String.format("{\"id\": \"%d\"}", courierId);
        return spec()
                .body(json)
                .delete(PREFIX + "/" + courierId)
                .then();
    }

    @Step("Delete courier without id value in JSON")
    public ValidatableResponse deleteCourierWithoutId(int courierId) {
        return spec()
                .body("{\"id\": \"\"}")
                .delete(PREFIX + "/" + courierId)
                .then();
    }
}
