package org.builtinquality.pos;

import io.restassured.RestAssured;
import org.builtinquality.pos.entity.ItemEntity;
import org.builtinquality.pos.repository.ItemsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemsE2ERestTest {

    @Autowired
    ItemsRepository itemsRepository;

    @LocalServerPort
    private int port;

    @Test
    public void should_get_all_items() throws Exception {
        itemsRepository.save(new ItemEntity("ITEM001", "可口可乐", "听", 2d));
        RestAssured.given()
                .contentType(JSON)
                .body("{\"code\": \"ITEM002\", \"name\": \"苹果\", \"unit\": \"斤\", \"price\": 3.5}")
                .when()
                .post(String.format("http://localhost:%s/items", port))
                .then()
                .statusCode(is(201))
                .body("code", is("ITEM002"))
                .body("name", is("苹果"))
                .body("unit", is("斤"))
                .body("price", is(3.5f));

        RestAssured.given()
                .when()
                .get(String.format("http://localhost:%s/items", port))
                .then()
                .statusCode(is(200))
                .body("$", hasSize(2))
                .body("code", hasItems("ITEM001", "ITEM002"));
    }

    @AfterEach
    public void tearDown() {
        itemsRepository.deleteAll();
    }
}
