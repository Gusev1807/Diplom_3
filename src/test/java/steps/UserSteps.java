package steps;

import io.restassured.response.Response;
import io.qameta.allure.Step;
import pojo.User;

import static io.restassured.RestAssured.given;

public class UserSteps {

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api";

    @Step("Создать пользователя через API")
    public static Response createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post(BASE_URL + "/auth/register");
    }

    @Step("Удалить пользователя через API")
    public static Response deleteUser(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .delete(BASE_URL + "/auth/user");
    }

    @Step("Войти пользователем через API")
    public static Response loginUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post(BASE_URL + "/auth/login");
    }
}

