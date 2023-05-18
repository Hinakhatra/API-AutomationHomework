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

public class WeatherApi {
    @BeforeClass
        public void setUp(){

    }

    @Test
    public void alternate_way_to_get(){
        RequestSpecification requestSpecification =given();
        requestSpecification.baseUri("https://api.openweathermap.org/data/2.5");
        requestSpecification.basePath("weather");
        requestSpecification.queryParam("lat", "44.34");
        requestSpecification.queryParam("lon", "10.99");
        requestSpecification.queryParam("appid", "b2d763b80bec0db73b651a7c10580d7d");

        ValidatableResponse vr = requestSpecification.when()
                .get()
                .then();
        //response.prettyPrint();
        vr.assertThat().statusCode(200);
        vr.assertThat().body("main", hasKey("feels_like"));
        vr.assertThat().body("name", Is.is("Zocca"));

    }

    @Test
    public void it_Gets_weather(){
        ValidatableResponse validatableresponse = given()
                .baseUri("https://api.openweathermap.org/data/2.5")
                .basePath("weather")
                .queryParam("lat","44.34")
                .queryParam("lon","10.99")
                .queryParam("appid","b2d763b80bec0db73b651a7c10580d7d")
                .when()
                .get()
                .then();
        Response response = validatableresponse
                .extract()
                .response();
        Assert.assertEquals(response.getStatusCode(), 200);
        response.prettyPrint();
        validatableresponse.assertThat().body("main", hasKey("feels_like"));


    }
}

