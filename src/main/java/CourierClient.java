import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierClient {
    public Response addCourier(String login, String password, String firstName) {
        BodyForCourier jsonBody = new BodyForCourier(login, password, firstName);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(jsonBody)
                        .when()
                        .post("/api/v1/courier");
        return response;
    }

    public Response loginCourier(String login, String password) {
        BodyForLoginCourier jsonBody = new BodyForLoginCourier(login, password);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(jsonBody)
                        .when()
                        .post("/api/v1/courier/login");
        return response;
    }

    public void deleteCourier(String login, String password) {
        Response idCourier = loginCourier(login, password);
        Integer id = idCourier.then().extract().body().path("id");
        if (id != null) {
            Response response =
                    given()
                            .delete("/api/v1/courier/" + id);
        }
    }
}
