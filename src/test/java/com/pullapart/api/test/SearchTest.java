package com.pullapart.api.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pullapart.common.TestBase;

import com.pullapart.endpoint.SEARCH;
import com.pullapart.helper.URIFormatter;
import com.pullapart.payload.SearchPayload;
import com.pullapart.properties.AppConstants;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
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

    @Test(groups = {"search"}, dataProvider = "dataProvider", description = "test coverage for /Vehicle/Search advanced search" +
            "using testNG data provider to bring in the test data")
    public void searchVehicleWithDataProvider(String searchedMake, String searchedModel) throws JsonProcessingException {

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

    @Test(groups = {"search"}, description = "test coverage for /Vehicle/Search end point")
    public void advancedVehiclesearch() throws JsonProcessingException {

        String searchedMake = "INFINITI";
        String searchedModel = "Q45";
        int  years = 1994;
        List<Integer> locations = Arrays.asList(21, 4, 3);
        int MakeID = 29;
        List<Integer> Models = Arrays.asList(720);
        List<Integer> Years = Arrays.asList(years);
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
        //If search results are returned, assert that the initially searched make and model are returned, note exact year match may not be available
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

    @Test(groups = {"search"}, description = "test coverage for /Vehicle/Search end point using URI Formatter")
    public void advancedVehiclesearchUsingURIFormatter() throws JsonProcessingException {

        String searchedMake = "INFINITI";
        String searchedModel = "Q45";
        int  years = 1994;
        List<Integer> locations = Arrays.asList(21, 4, 3);
        int MakeID = 29;
        List<Integer> Models = Arrays.asList(720);
        List<Integer> Years = Arrays.asList(years);
        //Create the search request payload with the above test data
        String payload = SearchPayload.searchPayload(locations, MakeID, Models, Years);
        //make the POST search call
        URIFormatter format = new URIFormatter();
        //To see full log
//        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        Response searchObj =
                given()
                        .header(AppConstants.ACCEPT, AppConstants.APPLICATION_JSON_TEXT_JS_Q_01)
                        .header(AppConstants.CONTENT_TYPE, AppConstants.APPLICATION_JSON)
                        .body(payload)
                        .when()
                        .post(format.formatUri(inventoryBaseURL, "Vehicle", "Search"))
                        .then()
                        .log().ifValidationFails()
                        .statusCode(200)
                        .extract().response();
        //If search results are returned, assert that the initially searched make and model are returned, note exact year match may not be available
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
