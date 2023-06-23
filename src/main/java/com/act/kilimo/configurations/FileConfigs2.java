/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.act.kilimo.configurations;

import com.act.kilimo.model.Settings;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author coder
 */
public class FileConfigs2 {
    
    private static final Logger LOGGER = LogManager.getLogger(FileConfigs2.class);



    public FileConfigs2()
    {

    }
    
    public JSONObject getConfigs(){
       
        return getSettingsFromFileResource();
               
    }



    private JSONObject getSettingsFromFileResource()
    {

        var classaloader = getClass().getClassLoader();
        try ( var inputStreamreader = new InputStreamReader(classaloader.getResourceAsStream("view.json"), StandardCharsets.UTF_8);  var reader = new BufferedReader(inputStreamreader))
        {
            var builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null)
            {
                builder.append(line);
            }

            return new JSONObject(builder.toString());

        }
        catch (Exception exception)
        {
            LOGGER.error("Error: " + exception.getClass().getSimpleName() + "<<<<<< | message | >>>>>>>" + exception.getMessage());
            return new JSONObject();
        }

    }

    
}
