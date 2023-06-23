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
public class HomeMenuRule implements ServiceRule {

    @Override
    public Boolean matches(String menucode)
    {
        return menucode.equalsIgnoreCase(new RuleTagsModel().getServiceRule(HomeMenuRule.class));
    }



    @Override
    public Object apply(RequestModel request)
    {
        return processResponse(request);
    }



    private Object processResponse(RequestModel request)
    {

        var useResponse = request.userResponse();
        Object response;

        if (useResponse == null)
        {
            System.out.println("The response is null");
            throw new AssertionError("you did not select anything, kindly try again");
        }
        else
        {
            switch (useResponse)
            {
                case "00" ->
                    response = new MenusMananger(request).endSessionMenu();
                case "1" ->
                    response = new MenusMananger(request).firstname();
                case "2" ->
                    response = new MenusMananger(request).productAndStoreListingMenu();
               
                case "3" ->
                    response = new MenusMananger(request).summaryMenu();

                default ->
                    throw new AssertionError("Invalid user response");
            }
        }
        return response;

    }

}
