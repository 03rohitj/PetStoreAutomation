package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndpoints2 {
    //To Illustrate how data can be read through properties file

    //Method to fetch URLs from routes.properties file
    static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routes");
        return routes;
    }

    public static Response createUser(User payload){

        String createUserUrl = getURL().getString("createUser_url");
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(createUserUrl);
        return response;
    }

    public static Response readUser(String username){
        String getUser_url = getURL().getString("getUser_url");
        Response response = given()
                .pathParam("username",username)
                .when()
                .get(getUser_url);

        return response;
    }

    public static Response updateUser(String username, User payload){
        String updateUser_url = getURL().getString("updateUser_url");
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",username)
                .body(payload)
                .when()
                .put(updateUser_url);
        return response;
    }

    public static Response deleteUser(String username) {
        String deleteUser_url = getURL().getString("deleteUser_url");
        Response response = given()
                .pathParam("username", username)
                .accept(ContentType.JSON)
                .when()
                .delete(deleteUser_url);

        return response;
    }
}
