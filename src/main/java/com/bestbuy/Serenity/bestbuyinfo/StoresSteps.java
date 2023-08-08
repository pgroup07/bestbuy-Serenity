package com.bestbuy.Serenity.bestbuyinfo;

import com.bestbuy.Serenity.constants.Path;
import com.bestbuy.Serenity.model.StorePojo;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StoresSteps {
    @Step("Create store")
    public Response createStore(String name, String type, String address, String address2, String city, String state, String zip) {

        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(storePojo)
                .post(Path.STORES);
    }

    @Step("Get store by store id")
    public Response getStoreById(int storeId) {
        return SerenityRest.given().log().all()
                .when()
                .get(Path.STORES + storeId);
    }

    @Step("Update store By store id name")
    public Response updateStoreById(String UpdatedName, String type, String address, String address2, String city, String state, String zip, int storeId) {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(UpdatedName);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .body(storePojo)
                .patch(Path.STORES + storeId);
              //  .then().log().all();
    }

    @Step("Delete store By Store id")
    public Response deleteStoreById(int storeId) {
        return SerenityRest.given().log().all()
                .pathParam("id", storeId)
                .when()
                .delete("/stores/{id}");
    }

}
