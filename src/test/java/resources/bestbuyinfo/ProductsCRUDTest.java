package resources.bestbuyinfo;

import com.bestbuy.Serenity.model.ProductPojo;
import com.bestbuy.Serenity.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;

@RunWith(SerenityRunner.class)
public class ProductsCRUDTest {
    static ValidatableResponse response;
    static String name = "New Product" + TestUtils.getRandomValue();
    static String UpdatedName = "UpdatedName" + TestUtils.getRandomValue();
    static String type = "Hard Good" + TestUtils.getRandomValue();
    static String upc = "12" + TestUtils.getRandomValue();
    static double price = 99.99;
    static String description = "This is a placeholder request for creating a new product.";
    static String model = "NP" + TestUtils.getRandomValue();
    static int productId;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
    }

    @Test
    public void test001() {

        ProductPojo projectPojo = new ProductPojo();
        projectPojo.setName(name);
        projectPojo.setType(type);
        projectPojo.setUpc(upc);
        projectPojo.setPrice(price);
        projectPojo.setDescription(description);
        projectPojo.setModel(model);

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .body(projectPojo)
                .post("/products");
        response.then().log().all().statusCode(201);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        productId = jsonPath.getInt("id");
    }

    @Test
    public void test002() {
        System.out.println("=============" + productId);
        response = given()
                .when()
                .get("/products/" + productId)
                .then().statusCode(200);
    }

    @Test
    public void test003() {

        ProductPojo projectPojo = new ProductPojo();
        projectPojo.setName(UpdatedName);
        projectPojo.setType(type);
        projectPojo.setUpc(upc);
        projectPojo.setPrice(price);
        projectPojo.setDescription(description);
        projectPojo.setModel(model);

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .body(projectPojo)
                .patch("/products/" + productId);
        response.then().log().all().statusCode(200);

    }

    @Test
    public void test004() {

        given()
                .pathParam("id", productId)
                .when()
                .delete("/products/{id}")
                .then()
                .statusCode(200);
    }
}
