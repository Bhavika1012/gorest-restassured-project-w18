package com.gorest.goreststeps;

import com.gorest.constants.EndPoints;
import com.gorest.model.UsersPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class UserSteps {
    static String bearerToken = "Bearer 5f8f6573a47c8960d0a91e044e48aeed74916d3e1b6a5a5a8cd77338db388b67";

    @Step("Create the user with name : {0}, email : {1}, gender : {2}, status : {3}")
    public ValidatableResponse createUser(String name, String email, String gender, String status) {

        UsersPojo usersPojo = UsersPojo.getBooking(name, email, gender, status);


        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", bearerToken)
                .contentType(ContentType.JSON)
                .body(usersPojo)
                .when()
                .post()
                .then();
    }

    @Step("Getting the users details with usersId : {0}")
    public ValidatableResponse getTheUsersDetails(int usersId) {
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Access", "application/json")
                .header("Authorization", bearerToken)
                .pathParam("usersID", usersId)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then();

    }

    @Step("Updating the users details with usersId : {0},name : {1}, email : {2}, gender : {3}, status : {4} ")
    public ValidatableResponse updateTheUsersDetails(int usersId, String name, String email, String gender, String status) {

        UsersPojo usersPojo = UsersPojo.getBooking(name, email, gender, status);

        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", bearerToken)
                .contentType(ContentType.JSON)
                .pathParam("usersID", usersId)
                .body(usersPojo)
                .when()
                .put(EndPoints.UPDATE_USER_BY_ID)
                .then();

    }

    @Step("Deleting the users with usersId : {0}")
    public ValidatableResponse deleteTheUser(int usersId) {
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Access", "application/json")
                .header("Authorization", bearerToken)
                .pathParam("usersID", usersId)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();

    }

}
