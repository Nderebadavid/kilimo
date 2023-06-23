/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.act.kilimo.network;

import com.act.kilimo.model.RequestModel;
import com.act.kilimo.utility.Constants;
import org.json.JSONObject;

/**
 *
 * @author coder
 */
public class RequestHandler {

    public static String fetchData()
    {

        var url = Constants.FETCHFILESETTINGS_URL_STRING;
        var headers = new String[]
        {
            Constants.CONTENT_TYPE_KEY, Constants.CONTENT_TYPE_VALUE, Constants.USER_AGENT_KEY, Constants.USER_AGENT_VALUE
        };

        var request = new HttpConnector().getRequest(url, headers);
        var response = new HttpConnector().sendRequest(request);
        System.out.println("http Response=>" + response.toJson());

        return response.meassge();

    }
    
    public static JSONObject fetchdata1(){
        
        var url = Constants.FETCH_DATA1_URL;
        var headers = new String[]
        {
                        Constants.CONTENT_TYPE_KEY, Constants.CONTENT_TYPE_VALUE, Constants.USER_AGENT_KEY, Constants.USER_AGENT_VALUE

        };
        
        var request =new HttpConnector().getRequest(url, headers);
        var response = new HttpConnector().sendRequest(request);
        System.out.println("http response =====>" +response.toJson());
        
        return response.toJson();
        
    }

    
    public static int getLocationId(String payload){
        
       int locationId = 0;
        
        var url = Constants.LOCATION_ID_URL;
        
        var headers = new String[]
        {
            Constants.CONTENT_TYPE_KEY, Constants.CONTENT_TYPE_VALUE, Constants.USER_AGENT_KEY, Constants.USER_AGENT_VALUE
        };

        var request = new HttpConnector().postRequest(url, payload, headers);
        var response = new HttpConnector().sendRequest(request);
        System.out.println("http Response=>" + response.toJson());
        
        if (response.toJson().has("locationId"))
        {
           locationId = response.toJson().getInt("locationId");
        }
          return locationId;
    }


    public static JSONObject registerUser(RequestModel request)
    {

        var url = Constants.ADD_USER_URL;
        var headers = new String[]
        {
            Constants.CONTENT_TYPE_KEY, Constants.CONTENT_TYPE_VALUE, Constants.USER_AGENT_KEY, Constants.USER_AGENT_VALUE

        };
        
        var regPayload = PayloadGenerator.addUserPayload(request);
        var request1 = new HttpConnector().postRequest(url, regPayload.toString(), headers);
        var response1 = new HttpConnector().sendRequest(request1);
        System.out.println("http Response=>" +response1.toJson());
        
        return  response1.toJson();
    }
    
  


}
