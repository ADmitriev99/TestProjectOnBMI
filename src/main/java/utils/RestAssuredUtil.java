package utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RestAssuredUtil {

    protected static RequestSpecification request = null;

    protected final static String URL = "http://localhost:8087/services/bodymassindex";

    public static RequestSpecification getInstance() {
        if (request == null) {
            request = RestAssured.given();
            request.header("Content-Type", "application/xml");
        }
        return request;
    }

    public static String getUrl() {
        return URL;
    }
}
