package com.example.RestAssured_TestNG_Demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
 
import org.json.JSONObject;
import org.testng.annotations.Test;
 
import io.restassured.http.ContentType;
 
public class RestAPIDemo  {
 
    @Test(description = "To get the details of employee with id 2", priority = 0)
    public void verifyUser() {
 
        // Given
        given()
                // When
                .when().get("http://dummy.restapiexample.com/api/v1/employee/2")
                // Then
                .then().statusCode(200).statusLine("HTTP/1.1 200 OK")
                // To verify booking id at index 3
                .body("data.employee_name", equalTo("Garrett Winters"))
                .body("message", equalTo("Successfully! Record has been fetched."));
    }
 
    @Test(description = "To create a new employee", priority = 1)
    public void createUser() {
 
        JSONObject data = new JSONObject();
 
        // Map<String, String> map = new HashMap<String, String>();
 
        data.put("employee_name", "APITest");
        data.put("employee_salary", "99999");
        data.put("employee_age", "30");
 
        // GIVEN
        given().baseUri("http://dummy.restapiexample.com/api").contentType(ContentType.JSON).body(data.toString())
 
                // WHEN
                .when().post("/v1/create")
 
                // THEN
                .then().statusCode(200).body("data.employee_name", equalTo("APITest"))
                .body("message", equalTo("Successfully! Record has been added."));
 
    }
}