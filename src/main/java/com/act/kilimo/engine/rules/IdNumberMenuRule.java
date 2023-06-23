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

/**
 *
 * @author coder
 */
public class IdNumberMenuRule implements  ServiceRule{
    
      @Override
    public Boolean matches(String menucode)
    {
        return  menucode.equalsIgnoreCase(new RuleTagsModel().getServiceRule(IdNumberMenuRule.class));
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
                var msg = "Invalid Response. ID Number cannot be empty, kindly try again.";
                response = new MenusMananger(request).invalidResponseMenu(msg);
            }
            case "00" -> new MenusMananger(request).endSessionMenu();
            default -> {
                var cacheObject = new RedisDao().getSet(cacheKeyTwo);
                if (!new RedisDao().cacheOperation(cacheKeyTwo,cacheObject.put("idNumber", userResponse)))
                {
                    
                    response =  "Failed. Please try again later";
                    
                }
                else{
                    response =  new MenusMananger(request).locationMenu();
                }
            }
        }
        
        return  response;
    }
    
    
}
