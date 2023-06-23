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
public class DeliverProductRule implements ServiceRule {

    @Override
    public Boolean matches(String menucode)
    {
        return menucode.equalsIgnoreCase(new RuleTagsModel().getServiceRule(DeliverProductRule.class));
    }



    @Override
    public Object apply(RequestModel request)
    {
        return processRequest(request);
    }



    private Object processRequest(RequestModel request)
    {

        var userSelection = request.userResponse().trim();
        var cacheKeyThree = request.getCacheKeys().get(2);
        var cacheKeyTwo = request.getCacheKeys().get(1);

        int index = Integer.parseInt(userSelection);

        JSONObject object = new RedisDao().getSet(cacheKeyTwo);

        JSONObject cacheObject = new RedisDao().getSet(cacheKeyThree);

        if (index > 0 && index <= object.getJSONArray("stores").length())
        {
            String store = object.getJSONArray("stores").getString(index - 1);
            cacheObject.put("store", store);

            if (!new RedisDao().cacheOperation(cacheKeyThree, cacheObject))
            {
                return "Failed to process. Kindly try again later.";
            }

            var menu = new StringBuilder().append("Enter The Quantity of Your Products").toString();
            return new MenusMananger(request).productDetailsConfirmationMenu(menu);

        }

        if (index == 0)
        {
            return new MenusMananger(request).productAndStoreListingMenu();
        }
        else
        {
            return new MenusMananger(request).homeMenu();

        }

    }

}
