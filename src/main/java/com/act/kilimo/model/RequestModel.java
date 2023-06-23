package com.act.kilimo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record RequestModel(String mobileNumber, String sessionid, String serviceCode, String userResponse, String start, String end, ArrayList<String> cacheKeys, Map<String, String> payload) {

    public RequestModel(Map<String, String> payload)
    {
        this(
                payload.getOrDefault("mobileNumber", ""),
                payload.getOrDefault("sessionId", ""),
                payload.getOrDefault("serviceCode", ""),
                payload.getOrDefault("userResponse", ""),
                payload.getOrDefault("start", "CON "),
                payload.getOrDefault("end", "END "),
                generateCacheKeys(payload.get("mobileNumber")),
                payload
        );
    }

    public static ArrayList<String> generateCacheKeys(String mobileNumber)
    {
        if (mobileNumber != null)
        {
            var cacheKeyOne = mobileNumber.substring(1);
            var cacheKeyTwo = mobileNumber.substring(2);
            var cacheKeyThree = mobileNumber.substring(3);
            var cacheKeyFour = new StringBuilder(cacheKeyOne).reverse().toString();
            var cacheKeyFive = new StringBuilder(cacheKeyTwo).reverse().toString();
            var cacheKeys = new ArrayList<String>();
            cacheKeys.add(cacheKeyOne);
            cacheKeys.add(cacheKeyTwo);
            cacheKeys.add(cacheKeyThree);
            cacheKeys.add(cacheKeyFour);
            cacheKeys.add(cacheKeyFive);
            cacheKeys.add("location");
            cacheKeys.add("object");
            
            return cacheKeys;
        } else {
            return new ArrayList<>();
        }
    }

    public List<String> getCacheKeys() {
        return cacheKeys;
    }
}
