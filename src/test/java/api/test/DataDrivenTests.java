package api.test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class DataDrivenTests {

    //String userID, String userName, String fName, String lName, String userEmail, String password, String phone
    @Test(priority = 1, dataProvider = "UserData", dataProviderClass = DataProviders.class)
    public void testPostUser(Map<String,String> rowData){

        User userPayload = new User();
        userPayload.setId(Integer.parseInt(rowData.get("UserID")));
        userPayload.setUsername(rowData.get("UserName"));
        userPayload.setFirstname(rowData.get("FirstName"));
        userPayload.setLastname(rowData.get("LastName"));
        userPayload.setEmail(rowData.get("Email"));
        userPayload.setPassword(rowData.get("Password"));
        userPayload.setPhone(rowData.get("Phone"));

        Response response = UserEndpoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String username){

        Response response = UserEndpoints.deleteUser(username);
        Assert.assertEquals(response.statusCode(),200);
    }
}
