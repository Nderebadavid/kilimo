/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.act.kilimo.engine.rules;

import com.act.kilimo.engine.implementation.MenusMananger;
import com.act.kilimo.engine.interfaces.ServiceRule;
import com.act.kilimo.model.RequestModel;
import com.act.kilimo.model.RuleTagsModel;

/**
 *
 * @author coder
 */
public class ConfirmDeliveryRule implements  ServiceRule{

    @Override
    public Boolean matches(String menucode)
    {
        return  menucode.equalsIgnoreCase(new RuleTagsModel().getServiceRule(ConfirmDeliveryRule.class));
    }



    @Override
    public Object apply(RequestModel request)
    {
        return processRequest(request);
    }
    
    private Object processRequest(RequestModel request){
        
        var userResponse = request.userResponse().trim();
        
        switch (userResponse)
        {
            case "00" ->
            {
                  return new MenusMananger(request).endOperationMenu("Thank you for Choosing Kilimosoko");

            }
            case "1" ->
            {
                var message = "Your Delivery has been posted successfully Now Awaiting Aproval from the Product Owner. Thank you for Choosing Kilimosoko";
                return new MenusMananger(request).endOperationMenu(message);
            }
            default -> throw new AssertionError();
        }
        
    }
    
}
