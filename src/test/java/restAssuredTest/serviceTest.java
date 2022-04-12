package restAssuredTest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.runner.Request;
import org.testng.annotations.Test;
import io.restassured.RestAssured.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.number.OrderingComparison.greaterThan;

import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;



public class serviceTest {
    public static final String address = "http://localhost:3000";
    public static final int STATUS_CODE =0;
    private static final Gson gson = new Gson();

    @Test
    public void  whenGroceryServiceGetRequest_ThenReturnAllGroceryWith200(){
        when().
                get(address+"/data").
        then().
                log().ifStatusCodeIsEqualTo(500);
        when().
                get(address+"/data").
        then().
                log().ifStatusCodeIsEqualTo(400);
        when().
                get(address+"/data").
                then().
                log().ifStatusCodeIsEqualTo(200).
                body("name",hasItems("apple","grapes"));


    }
    @Test
    public void whenGroceryServiceGetRequestWithName_ThenReturnGroceryNameJsonWith200(){
        when().
                get(address+"/data?name=apple").
        then().log().ifStatusCodeIsEqualTo(500).//ıf service get 500 then write log about 500.
                body("name[0]",equalTo("apple")).statusCode(200).
                log().all();


    }
    @Test
    public void whenAddNewJsonObject_thenReturnSuccess() throws JSONException {
        addNewJsonObject(18,"strawx",10,25);
        when().
                get(address+"/data").
        then().
                body("name",hasItems(notNullValue())).log().ifStatusCodeIsEqualTo(201);

    }

    public void addNewJsonObject(int id,String name,int price, int stock) throws JSONException {
        JSONObject requestParam = new JSONObject();
        requestParam.put("id", id);
        requestParam.put("name", name);
        requestParam.put("price", price);
        requestParam.put("stock", stock);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestParam.toString()).
        when().
                post(address+"/data").
        then().
               log().body().assertThat().statusCode(201);
    }
}
