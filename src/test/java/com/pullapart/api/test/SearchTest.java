package com.pullapart.api.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pullapart.common.TestBase;

import com.pullapart.endpoint.SEARCH;
import com.pullapart.payload.SearchPayload;
import com.pullapart.properties.AppConstants;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SearchTest extends TestBase {

    @Test(groups = {"search"}, description = "test coverage for /Vehicle/Search end point")
    public void searchVehicle() throws JsonProcessingException {

        String searchedMake = "INFINITI";
        String searchedModel = "Q45";
        List<Integer> locations = Arrays.asList(4);
        int MakeID = 29;
        List<Integer> Models = Arrays.asList(720);
        List<Integer> Years = Arrays.asList();
        //Create the search request payload with the above test data
        String payload = SearchPayload.searchPayload(locations, MakeID, Models, Years);
        //make the POST search call

        Response searchObj =
        given()
                .header(AppConstants.ACCEPT, AppConstants.APPLICATION_JSON_TEXT_JS_Q_01)
                .header(AppConstants.CONTENT_TYPE, AppConstants.APPLICATION_JSON)
                .body(payload)
                .when()
                .post(inventoryBaseURL.concat(SEARCH.VEHICLE.path).concat(SEARCH.SEARCH.path))
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        //If search results are returned, assert that the initially searched make and model are returned
        int totalObj = searchObj.path("$.size()");
        if (totalObj > 0) {
            int totalExact = searchObj.path("[0].exact.size()");
            for (int i = 0; i < totalExact; ++i) {
                Map<String, Object> tempData = searchObj.path("[0].exact[" + i + "]");
                Assert.assertEquals(tempData.get("makeName").toString(), searchedMake, "the initially searched make was not returned");
                Assert.assertEquals(tempData.get("modelName").toString(), searchedModel, "the searched model was not returned");
            }
        }
    }
}
