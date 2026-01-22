package com.aston.postman;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PostmanEchoTests {

    @Test
    public void testGetRequest() {
        System.out.println("1. Тестируем GET запрос...");
        given()
                .param("foo1", "bar1")
                .param("foo2", "bar2")
                .when()
                .get("https://postman-echo.com/get")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"));
        System.out.println(" Успешно");
    }

    @Test
    public void testPostJson() {
        System.out.println("2. Тестируем POST с JSON...");
        given()
                .body("{\"test\": \"value\"}")
                .header("Content-Type", "application/json")
                .when()
                .post("https://postman-echo.com/post")
                .then()
                .statusCode(200)
                .body("json.test", equalTo("value"));
        System.out.println(" Успешно");
    }

    @Test
    public void testPostFormData() {
        System.out.println("3. Тестируем POST с form-data...");
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .when()
                .post("https://postman-echo.com/post")
                .then()
                .statusCode(200);
        System.out.println(" Успешно");
    }

    @Test
    public void testPutRequest() {
        System.out.println("4. Тестируем PUT...");
        String putBody = "This is expected to be sent back as part of response body.";
        given()
                .body(putBody)
                .when()
                .put("https://postman-echo.com/put")
                .then()
                .statusCode(200)
                .body("data", equalTo(putBody));
        System.out.println(" Успешно");
    }

    @Test
    public void testPatchRequest() {
        System.out.println("5. Тестируем PATCH...");
        String patchBody = "This is expected to be sent back as part of response body.";
        given()
                .body(patchBody)
                .when()
                .patch("https://postman-echo.com/patch")
                .then()
                .statusCode(200)
                .body("data", equalTo(patchBody));
        System.out.println(" Успешно");
    }

    @Test
    public void testDeleteRequest() {
        System.out.println("6. Тестируем DELETE...");
        String deleteBody = "This is expected to be sent back as part of response body.";
        given()
                .body(deleteBody)
                .when()
                .delete("https://postman-echo.com/delete")
                .then()
                .statusCode(200)
                .body("data", equalTo(deleteBody));
        System.out.println(" Успешно");
    }
}
