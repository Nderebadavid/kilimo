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
public class StoresListedRule implements ServiceRule {

    @Override
    public Boolean matches(String menucode)
    {
        return menucode.equalsIgnoreCase(new RuleTagsModel().getServiceRule(StoresListedRule.class));
    }



    @Override
    public Object apply(RequestModel request)
    {
        return processRequest11(request);
    }



    private Object processRequest11(RequestModel request)
    {
        var userSelection = request.userResponse().trim();
        var cacheKeyTwo = request.getCacheKeys().get(1);
        var cacheKeyThree = request.getCacheKeys().get(2);

        var index = Integer.parseInt(userSelection);
        var object = new RedisDao().getSet(cacheKeyTwo);
        var products = object.getJSONArray("products");
        var stores = object.getJSONArray("stores");
        var productOwner = object.getString("productOwner");

        try
        {

            if (products != null && index > 0 && index <= products.length())
            {
                var selectedProduct = products.getJSONObject(index - 1);

                var productName = selectedProduct.getString("productName");
                var pricePerUnit = selectedProduct.getDouble("pricePerUnit");
                var totalQuantityTarget = selectedProduct.getInt("totalQuantityTarget");

                var selectedProductDetails = new JSONObject()
                        .put("productName", productName)
                        .put("pricePerUnit", pricePerUnit)
                        .put("totalQuantityTarget", totalQuantityTarget)
                        .put("productOwner", productOwner);

                if (!new RedisDao().cacheOperation(cacheKeyThree, selectedProductDetails))
                {
                    return "Failed. Kindly try again later";
                }

                var menuBuilder = new StringBuilder("The Stores Open\n");
                for (int i = 0; i < stores.length(); i++)
                {
                    var store = stores.getString(i);
                    menuBuilder.append(i + 1).append(". ").append(store).append("\n");
                }

                String menu = menuBuilder.toString();
                return new MenusMananger(request).deliverProductMenu(menu);
            }
            else
            {
                return new MenusMananger(request).endSessionMenu();
            }
        }
        catch (NumberFormatException e)
        {
            return "Invalid input. Please enter a valid index.";
        }
    }

}
