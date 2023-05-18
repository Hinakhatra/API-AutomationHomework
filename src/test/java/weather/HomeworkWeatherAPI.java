package weather;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.core.Is;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class HomeworkWeatherAPI {
    @BeforeClass
    public void setUp(){

    }
    @Test
    public void it_Gets_Weather(){
        ValidatableResponse validatableresponse = given()
                .baseUri("https://jsonplaceholder.typicode.com/")
                .basePath("users")
                .when()
                .get("2")    //This method will change like post,put
                .then();
        Response response = validatableresponse
                .extract()
                .response();
        Assert.assertEquals(response.getStatusCode(), 200);
        response.prettyPrint();
        //To verify latitude is as expected
        validatableresponse.assertThat().body("address.geo.lat", Is.is("-43.9509"));
        //To verify longitude is as expected
        validatableresponse.assertThat().body("address.geo.lng", Is.is("-34.4618"));
    }
    @Test
    public void it_Gets_weather_2(){
        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com/")
                .basePath("users")
                .when()
                .get("2")              //This method will change like post,put
                .then()
                .extract().response();
        Assert.assertEquals(response.getStatusCode(),200);
        response.prettyPrint();
        //to verify latitude is as expected
        Assert.assertEquals(response.jsonPath().getString("address.geo.lat"),"-43.9509");

        //to verify longitude is as expected
        Assert.assertEquals(response.jsonPath().getString("address.geo.lng"),"-34.4618");

    }

}
