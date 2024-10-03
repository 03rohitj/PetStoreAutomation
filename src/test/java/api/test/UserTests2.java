package api.test;

import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpoints2;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests2 {
    Faker faker;
    User userPayload;
    Logger logger;

    @BeforeClass
    public void setup(){
        faker = new Faker();
        userPayload = new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password(true));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPostUser(){
        logger.info("***********************Creating User Info***********************");
        Response response = UserEndpoints2.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("****************************************************************");
    }

    @Test(priority = 2)
    public void testGetUserByName(){
        logger.info("***********************Reading User Info***********************");
        Response response = UserEndpoints2.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.statusCode(),200);
        logger.info("****************************************************************");
    }

    @Test(priority = 3)
    public void testUpdateUserByName(){
        logger.info("***********************Updating User Info***********************");
        //Update fname, lname and email
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setLastname(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        Response response = UserEndpoints2.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(),200);

        //Verify after performing update
        Response responseAfterUpdate = UserEndpoints2.readUser(this.userPayload.getUsername());
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
        logger.info("****************************************************************");
    }

    @Test(priority = 4)
    public void testDeleteUserByName(){
        logger.info("***********************Deleting User Info***********************");
        Response response = UserEndpoints2.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.statusCode(),200);
        logger.info("****************************************************************");
    }
}
