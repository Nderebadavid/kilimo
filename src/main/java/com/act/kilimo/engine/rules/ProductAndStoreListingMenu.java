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
import com.act.kilimo.network.RequestHandler;

/**
 *
 * @author coder
 */
public class ProductAndStoreListingMenu implements  ServiceRule{

    @Override
    public Boolean matches(String menucode)
    {
        return menucode.equalsIgnoreCase(new RuleTagsModel().getServiceRule(ProductAndStoreListingMenu.class));
    }



    @Override
    public Object apply(RequestModel request)
    {
        return processRequest(request);
    }
    
    private Object processRequest(RequestModel request){
        
        var userResponse = request.userResponse().trim();
        var object = request.getCacheKeys().get(6);
        Object response;
        
        switch (userResponse)
        {
            case "1" -> {
                var fileObject =RequestHandler.fetchdata1();
                
                if (!new RedisDao().cacheOperation(object, fileObject))
                {
                    return "Failed to fetch products and product owners, please try again";
                }

                var jsonArray = fileObject.getJSONArray("objects");

                //access product owners
                var menuBuilder = new StringBuilder();
                menuBuilder.append("Choose Your Product Owner\nProduct Owners\n");
                for (int i = 0; i < jsonArray.length(); i++)
                {

                    var results = jsonArray.getJSONObject(i);
                    var productOwner = results.getString("productOwner");

                    var menu = new StringBuilder().append((i + 1)).append(". ").append(productOwner).toString();

                    menuBuilder.append(menu).append("\n");
                }

                 var menu = menuBuilder.toString();
                 return new MenusMananger(request).productsMenu(menu);
            }
            
            case "2" ->response =new MenusMananger(request).homeMenu();
            
            default -> response ="Failed, try again later";
        }
        
        return response;
        
    }
    
}
