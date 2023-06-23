/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.act.kilimo.resources;

import com.act.kilimo.model.RequestModel;
import com.act.kilimo.requests_consumer.RequestConsumer;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.OPTIONS;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author coder
 */
@Path("/")
public class ControllerResource {
    
    @OPTIONS
    @Path("/")
    public Response corsOptions() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "*")
                .header("Access-Control-Allow-Headers", "*")
                .build();
    }
    
    @GET
    @Path("/home")
    public Response home(){
        return  Response
                .ok("you are at home , take a sit! ")
                .build();
    }
    
    @GET
    @Path("/ussd_controller")
    public Response ussdController(@Context UriInfo uriInfo, 
                                                                                    @QueryParam("sessionId") String sessionId,
                                                                                    @QueryParam("serviceCode") String serviceCode,
                                                                                    @QueryParam("text") String userResponse,
                                                                                    @QueryParam("phoneNumber") String mobileNumber,
                                                                                    @QueryParam("networkCode") String network)  {
        
     userResponse = formatUserResponse(userResponse);
        mobileNumber = formatMobileNumber(mobileNumber);

        var payload = createPayload(sessionId, serviceCode, userResponse, mobileNumber, network);

        String response;
         if (userResponse.contains("*"))
        {
            userResponse = userResponse.substring(userResponse.lastIndexOf("*") + 1).trim();
        }
        response = switch (userResponse)
        {
            case "40" -> RequestConsumer.initService(new RequestModel(payload));
            default -> RequestConsumer.completeService(new RequestModel(payload));
        };

        return Response.ok(response)
                .build();
    }
    
        private String formatUserResponse(String userResponse) {
        if (userResponse != null && userResponse.contains("*")) {
            return userResponse.substring(userResponse.lastIndexOf("*") + 1).trim();
        }
        return userResponse;
    }
    
     private Map<String, String> createPayload(String sessionId, String serviceCode, String userResponse,
                                              String mobileNumber, String network) {
        Map<String, String> payload = new HashMap<>();

        Optional.ofNullable(sessionId).ifPresent(value -> payload.put("sessionId", value));
        Optional.ofNullable(serviceCode).ifPresent(value -> payload.put("serviceCode", value));
        Optional.ofNullable(userResponse).ifPresent(value -> payload.put("userResponse", value));
        Optional.ofNullable(mobileNumber).ifPresent(value -> payload.put("mobileNumber", value));
        Optional.ofNullable(network).ifPresent(value -> payload.put("network", value));

        return payload;
    }

    
   private String formatMobileNumber(String number) {
    String formattedNumber = "invalid Number";

    if (number.matches("254\\d{9}")) {
        formattedNumber = number;
    } else if (number.length() == 10) {
        if (number.matches("07\\d{8}") || number.matches("01\\d{8}")) {
            formattedNumber = "254" + number.substring(1);
        }
    }

    return formattedNumber;
}

    
}
