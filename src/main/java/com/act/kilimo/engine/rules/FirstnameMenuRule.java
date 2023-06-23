/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.act.kilimo.engine.rules;


import com.act.kilimo.dao.RedisDao;
import com.act.kilimo.engine.implementation.MenusMananger;
import com.act.kilimo.engine.interfaces.ServiceRule;
import com.act.kilimo.model.RequestModel;
import com.act.kilimo.model.RuleTagsModel;
import org.json.JSONObject;

/**
 *
 * @author coder
 */
public class FirstnameMenuRule implements  ServiceRule{

    @Override
    public Boolean matches(String menucode)
    {
        return  menucode.equalsIgnoreCase(new RuleTagsModel().getServiceRule(FirstnameMenuRule.class));
    }



    @Override
    public Object apply(RequestModel request)
    {
       return processRequest(request);
    }
    
    
    private Object processRequest(RequestModel request){
        
        var userResponse = request.userResponse().trim();
        var cacheKeyTwo = request.getCacheKeys().get(1);
        Object response = null;
        
        
        switch (userResponse)
        {
            case "" -> {
                var msg = "Invalid Response. Firstname cannot be null";
                response = new MenusMananger(request).invalidResponseMenu(msg);
            }
            case "0" -> response = new MenusMananger(request).homeMenu();
            case "00" ->response = new MenusMananger(request).endSessionMenu();
            default -> {
                if (!new RedisDao().cacheOperation(cacheKeyTwo, new JSONObject().put("firstname", userResponse)))
                {
                    
                    response =  "Failed. Please try again later";
                    
                }
                else{
                    response =  new MenusMananger(request).lastname();
                }
            }
        }
        
        return  response;
    }
    
    
}
