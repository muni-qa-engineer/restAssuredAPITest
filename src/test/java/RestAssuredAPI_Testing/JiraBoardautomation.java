package RestAssuredAPI_Testing; 

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.io.File;

import files.payLoad;

import static io.restassured.RestAssured.*;

public class JiraBoardautomation {

    public static void main(String[] args) {

        // Base URI
//        RestAssured.baseURI = "https://rahulshettyacademy-team.atlassian.net/";
        RestAssured.baseURI = "https://muniqaengineer.atlassian.net//";

        // ======================
        // 1. Create Bug Issue
        // ======================
        String createIssueResponse =
                given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Basic bXVuaWNoZW0wOUBnbWFpbC5jb206QVRBVFQzeEZmR0YwLUItS05WcU92UFVjM29yUG9DOUY3NDdWLTR3RzV3TDE1WGNzd3dhUDU5SmdFZVlQMXNMWFRHRFJram9RQjhzdFcyYzMySkY1ZTVfamxkTTNEeE9heHllZURsSDNVenpROEhLQUlReVFvS0k3NzBrNHNBMEFWc2ZlM3l4N1FxRFczOFJ1RnlBYlNOaGJ4TlZEME9SZ2QwYjJQUV9VNFAtUFBld3U3Z1RLVlFNPThBMjgxMUEw")
                    .body(payLoad.createBugPayload())
                    .log().all()
                .when()
                    .post("rest/api/3/issue")
                .then()
                    .log().all()
                    .assertThat()
                    .statusCode(201)
                    .contentType("application/json")
                    .extract()
                    .response()
                    .asString();

        // Extract issue id
        JsonPath js = new JsonPath(createIssueResponse);
        String issueId = js.getString("id");
        System.out.println("Created Issue ID: " + issueId);

        // ======================
        // 2. Add Attachment
        // ======================
        given()
            .pathParam("key", issueId)
            .header("X-Atlassian-Token", "no-check")
            .header("Authorization", "Basic bXVuaWNoZW0wOUBnbWFpbC5jb206QVRBVFQzeEZmR0YwLUItS05WcU92UFVjM29yUG9DOUY3NDdWLTR3RzV3TDE1WGNzd3dhUDU5SmdFZVlQMXNMWFRHRFJram9RQjhzdFcyYzMySkY1ZTVfamxkTTNEeE9heHllZURsSDNVenpROEhLQUlReVFvS0k3NzBrNHNBMEFWc2ZlM3l4N1FxRFczOFJ1RnlBYlNOaGJ4TlZEME9SZ2QwYjJQUV9VNFAtUFBld3U3Z1RLVlFNPThBMjgxMUEw")
            .multiPart("file", new File("/Users/munisekhar/Desktop/image.png"))
            .log().all()
        .when()
            .post("rest/api/3/issue/{key}/attachments")
        .then()
            .log().all()
            .assertThat()
            .statusCode(200);
    }

    
}