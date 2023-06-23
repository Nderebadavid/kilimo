/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.act.kilimo.network;

import com.act.kilimo.dao.RedisDao;
import com.act.kilimo.model.RequestModel;
import org.json.JSONObject;

/**
 *
 * @author coder
 */
public class PayloadGenerator {

    public static JSONObject addUserPayload(RequestModel request)
    {

        var cacheKeyTwo = request.getCacheKeys().get(1);
        var cacheOne =request.mobileNumber();
        
        
        var cacheObject = new RedisDao().getSet(cacheKeyTwo);
        var msisdn = new RedisDao().getSet(cacheOne).getString("msisdn");
        
        var location = cacheObject.optString("location", "");
        var payload = new JSONObject().put("location", location);
        
        var locationId = RequestHandler.getLocationId(payload.toString()) >= 1 ? RequestHandler.getLocationId(payload.toString()) : null;
        
        var firstname = cacheObject.optString("firstname","");
        var lastname = cacheObject.optString("lastname", "");
        var idNumber =cacheObject.optString("idNumber", "");
        
        
       
        return new JSONObject()
                .put("firstName", firstname)
                .put("lastName", lastname)
                .put("idNumber", idNumber)
                .put("msisdn", msisdn)
                .put("locationId", locationId);

    }

}
/*
 var store = cacheObject.getString("store");
        var productOwner = cacheObject.getString("productOwner");
        var productName = cacheObject.getString("productName");
        var pricePerUnit = cacheObject.getDouble("pricePerUnit");
        var quantityDelivered = cacheObject.getInt("quantityDelivered");


//  response = new StringBuilder().append("Failed to register the user.  A ").append(dataObj).append(". Please try again Later").toString();


*/
