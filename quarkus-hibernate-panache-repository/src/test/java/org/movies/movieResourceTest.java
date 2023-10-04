package org.movies;


import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@Tag("integration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class movieResourceTest {

    @Test
    @Order(1)
    void getall() {
        given()
                .when().get("/movies")
                .then()
                .body("size" , equalTo(2))
                .body("id" , hasItems(1,2))
                .body("title" , hasItems("FirstMovie" , "SecondMovie"))
                .body("description" , hasItems("Firstmovie" , "Secondmovie"))
                .body("director" , hasItem("me"))
                .body("country" , hasItem("planer"))
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    @Order(1)
    void getbyid() {
    }

    @Test
    @Order(1)
    void getbytitle() {
    }

    @Test
    @Order(1)
    void getbycountry() {
    }

    @Test
    @Order(2)
    void createmovie() {
    }

    @Test
    @Order(3)
    void deletebyid() {
    }

    @Test
    @Order(4)
    void updatemovie() {
    }
}