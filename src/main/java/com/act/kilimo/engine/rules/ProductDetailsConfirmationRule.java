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
public class ProductDetailsConfirmationRule implements ServiceRule {

    @Override
    public Boolean matches(String menucode)
    {
        return (menucode.equalsIgnoreCase(new RuleTagsModel().getServiceRule(ProductDetailsConfirmationRule.class)));
    }



    @Override
    public Object apply(RequestModel request)
    {
        return processRequest(request);
    }



    private Object processRequest(RequestModel request)
    {
        Object response;
        var userResponse = request.userResponse().trim();
        var cacheKeyThree = request.getCacheKeys().get(2);

        int quantity = Integer.parseInt(userResponse);

        JSONObject cacheObject = new RedisDao().getSet(cacheKeyThree);
        var totalQuantityTarget = cacheObject.getInt("totalQuantityTarget");

        if (quantity > 0 && quantity <= totalQuantityTarget)
        {
            cacheObject.put("quantityDelivered", quantity);

            if (!new RedisDao().cacheOperation(cacheKeyThree, cacheObject))
            {
                return "Failed to process. Kindly try again later.";
            }
            
            var store = cacheObject.getString("store");
            var productOwner = cacheObject.getString("productOwner");
            var productName = cacheObject.getString("productName");
            var pricePerUnit = cacheObject.getDouble("pricePerUnit");

            //String menu = "Kindly confirm your details to deliver products:\n" + cacheObject.toString();
            var msg = new StringBuilder().append("Kindly confirm your details to deliver products:\n\n").append("\t").append("Store: ").append(store)
                    .append("\n\tProduct Owner: ").append(productOwner).append("\n\tTotal Quantity Delivered : ").append(quantity).append("\n\tProductName : ").append(productName)
                    .append("\n\tPricePerUnit : ").append(pricePerUnit).toString();
            response =  new MenusMananger(request).confirmDeliveryMenu(msg);
        }
        else{
            response =new MenusMananger(request).homeMenu();
        }
        
        return  response;

    }

}
