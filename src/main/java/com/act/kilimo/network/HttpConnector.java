/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.act.kilimo.network;

import com.act.kilimo.model.HttpResponseModel;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author coder
 */
public class HttpConnector {

    private static final Logger LOGGER = LogManager.getLogger(HttpConnector.class);



    public HttpConnector()
    {
    }



    public HttpRequest getRequest(String url, String[] headers)
    {

        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .headers(headers)
                .build();

    }



    public HttpRequest postRequest(String url, String payload, String[] headers)
    {

        return HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .uri(URI.create(url))
                .headers(headers)
                .build();

    }



    public HttpRequest putRequest(String url, String payload, String[] headers)
    {

        return HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofString(payload))
                .uri(URI.create(url))
                .headers(headers)
                .build();

    }



    public HttpResponseModel sendRequest(HttpRequest httpRequest)
    {

        var client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(60))
                .build();

        try
        {
            HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            var statusCode = httpResponse.statusCode();
            var responseMessage = Optional.of(httpResponse.body()).orElse("Response Not Found");
            return new HttpResponseModel(statusCode, responseMessage);
        }
        catch (IOException | InterruptedException exception)
        {
            LOGGER.error("Error: " + exception.getClass().getSimpleName() + "<<<<<< | message | >>>>>>>" + exception.getMessage());
            return  new HttpResponseModel(0, new JSONObject().toString());
        }

    }

}
