package resources.bestbuyinfo;

import com.bestbuy.Serenity.bestbuyinfo.StoresSteps;
import com.bestbuy.Serenity.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(SerenityRunner.class)

public class StoresCURDTest {
    static Response response;
    static int storeid;
    static String name = "Prime" + TestUtils.getRandomValue();
    static String updatedName = "UpdatedName" + TestUtils.getRandomValue();
    static String type = "Testing";
    static String address = "Xyz";

    static String address2 = "Abc";
    static String city = "Patan";

    static String state = "Gujarat";
    static String zip = "385241";

    static List<String> services;
    @Steps
    StoresSteps storesSteps;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
    }
    @Title("This will create a new store ")
    @Test
    public void test001() {
        response = storesSteps.createStore(name, type, address, address2, city, state, zip);
        response.then().log().all().statusCode(201);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        storeid = jsonPath.getInt("id");
        Assert.assertEquals(name, jsonPath.getString("name"));
        Assert.assertEquals(type, jsonPath.getString("type"));
        Assert.assertEquals(address, jsonPath.getString("address"));
        Assert.assertEquals(address2, jsonPath.getString("address2"));
        Assert.assertEquals(city, jsonPath.getString("city"));
        Assert.assertEquals(state, jsonPath.getString("state"));
        Assert.assertEquals(zip, jsonPath.getString("zip"));


    }

    @Title("Get Store by Store Id ")
    @Test
    public void test002() {
        response = storesSteps.getStoreById(storeid);
        response.then().log().all().statusCode(200);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        Assert.assertEquals(name, jsonPath.getString("name"));
        Assert.assertEquals(type, jsonPath.getString("type"));
        Assert.assertEquals(address, jsonPath.getString("address"));
        Assert.assertEquals(address2, jsonPath.getString("address2"));
        Assert.assertEquals(city, jsonPath.getString("city"));
        Assert.assertEquals(state, jsonPath.getString("state"));
        Assert.assertEquals(zip, jsonPath.getString("zip"));

    }

    @Title("Update Store by Store Id ")
    @Test
    public void test003() {


        response = storesSteps.updateStoreById(updatedName, type, address, address2, city, state, zip, storeid);

        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        Assert.assertEquals(updatedName, jsonPath.getString("updatedName"));
        response.then().log().all().statusCode(200);

    }

    @Title("Delete Store by Store Id ")
    @Test
    public void test004() {
        response = storesSteps.deleteStoreById(storeid);
        response.then().log().all().statusCode(200);

        response = storesSteps.getStoreById(storeid);
        response.then().log().all().statusCode(404);

    }
}