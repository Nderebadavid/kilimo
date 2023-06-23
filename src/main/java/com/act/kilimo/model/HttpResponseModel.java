/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.act.kilimo.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author coder
 */
public record HttpResponseModel(int statusCode, String meassge) {

    private static final Logger logger = LogManager.getLogger(HttpResponseModel.class);



    public JSONObject toJson()
    {

        try
        {
            return new JSONObject(meassge);
        }
        catch (JSONException exception)
        {
            logger.error("error " + exception.getClass().getSimpleName() + " => " + exception.getMessage());
            return new JSONObject();
        }

    }

}
