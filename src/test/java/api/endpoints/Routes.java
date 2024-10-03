package api.endpoints;


/*
To maintain URLs
Base URL : https://petstore.swagger.io/v2
Create User (post) : https://petstore.swagger.io/v2/user
Get User(get) : https://petstore.swagger.io/v2/user/{username}
Update User (put) : https://petstore.swagger.io/v2/user/{username}
Delete User (delete) : https://petstore.swagger.io/v2/user/{username}
 */
public class Routes {
    public static String base_url = "https://petstore.swagger.io/v2";

    //User Module
    public static String getUser_url = base_url+"/user/{username}";
    public static String createUser_url = base_url+"/user";
    public static String updateUser_url = base_url+"/user/{username}";
    public static String deleteUser_url = base_url+"/user/{username}";


    //Store Module


    //Pet module


}
