package ru.yandex.praktikum.configuration;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static ru.yandex.praktikum.configuration.Config.getBaseUri;

public class Rest {
    public RequestSpecification getBaseRequestSpec() {
        return new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri(getBaseUri()).build();
    }
}
