/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.act.kilimo.engine.engine;

import com.act.kilimo.engine.interfaces.ServiceRule;
import com.act.kilimo.model.RequestModel;
import com.act.kilimo.model.RuleTagsModel;

/**
 *
 * @author coder
 */
public class Engine {

    RequestModel request;
    String menucode;



    public Engine(RequestModel request, String menucode)
    {
        this.request = request;
        this.menucode = menucode;
    }



    public String processRequest()
    {

        var response = "No rule found";

        var ruleTags = new RuleTagsModel().getRules();

        for (ServiceRule ruleTag : ruleTags)
        {
            if (ruleTag.matches(menucode))
            {
                response = ruleTag.apply(request).toString();
                break;
            }

        }

        return response;
    }
}
