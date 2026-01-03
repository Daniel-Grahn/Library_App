package se.yrgo.integrations.utility;

import java.net.*;
import java.net.http.*;

public class Utils {

    public static void getUsersWithBorrowedBooks() throws Exception{
        // we need a http client that can handle cookies since our
        // login information is handled using cookies
        CookieManager cookieHandler = new CookieManager();
        HttpClient client = HttpClient.newBuilder().cookieHandler(cookieHandler).build();
        // login data as a json string
        String loginData = "{\"username\":\"test2\",\"password\":\"yrgoP4ssword\"}";

        HttpRequest loginReq = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8888/api/login"))
                .header("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(loginData)).build();
        HttpResponse<String> loginResp = client.send(loginReq,
                HttpResponse.BodyHandlers.ofString());
        if (loginResp.statusCode() != 200) {
            throw new IllegalStateException("Could not log in");
        }
        HttpRequest lendReq = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8888/api/user/loans"))
                .build();
        HttpResponse<String> bookResp = client.send(lendReq,
                HttpResponse.BodyHandlers.ofString());
        System.out.println(bookResp.body());
        // here bookResp.body() is a JSON string of all the books the user has on loan
    }
}
