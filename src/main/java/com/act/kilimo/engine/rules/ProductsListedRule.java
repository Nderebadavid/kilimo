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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author coder
 */
public class ProductsListedRule implements ServiceRule {

    @Override
    public Boolean matches(String menucode)
    {
        return menucode.equalsIgnoreCase(new RuleTagsModel().getServiceRule(ProductsListedRule.class));
    }



    @Override
    public Object apply(RequestModel request)
    {
        return processRequest11(request);
    }



    private Object processRequest(RequestModel request)
    {
        String userSelection = request.userResponse().trim();
        String object = request.getCacheKeys().get(6);
        String cacheKeyTwo = request.getCacheKeys().get(1);

        JSONArray stores = new JSONArray();
        JSONObject details = new JSONObject();
        JSONArray products = new JSONArray();

        if (userSelection.matches("[1-10]"))
        {
            int index = Integer.parseInt(userSelection);
            var objects = new RedisDao().getSet(object).getJSONArray("objects");
            System.out.println("========>" + objects);

            if (index > 0 && index <= objects.length())
            {
                var selectedObject = objects.getJSONObject(index - 1);
                System.out.println("========>" + selectedObject);

                var storesArray = selectedObject.getJSONArray("stores");
                var productOwner = selectedObject.getString("productOwner");

                for (int i = 0; i < storesArray.length(); i++)
                {

                    var store = storesArray.getJSONObject(i);
                    var storeName = store.getString("name");
                    var storeProducts = store.getJSONArray("products");

                    stores.put(storeName);

                    for (int j = 0; j < storeProducts.length(); j++)
                    {

                        var product = storeProducts.getJSONObject(j);
                        var productName = product.getString("productName");
                        var pricePerUnitDouble = product.getDouble("pricePerUnit");
                        var pricePerUnit = String.valueOf(pricePerUnitDouble);
                        var totalQuantityTarget = product.getString("totalQuantityTarget");

                        var productDetails = new JSONObject();
                        productDetails
                                .put("productName", productName)
                                .put("pricePerUnit", pricePerUnit)
                                .put("totalQuantityTarget", totalQuantityTarget);

                        products.put(productDetails);

                    }

                }

                details
                        .put("productOwner", productOwner)
                        .put("stores", stores)
                        .put("products", products);

            }

            if (!new RedisDao().cacheOperation(cacheKeyTwo, details))
            {
                return "Failed, Kindly try again later";
            }

            var menuBuilder = new StringBuilder("The Products Listed\n");
            var productsArray = details.getJSONArray("products");
            for (int i = 0; i < productsArray.length(); i++)
            {
                var product = productsArray.getJSONObject(i);
                var productName = product.getString("productName");
                var pricePerUnit = product.getString("pricePerUnit");
                var totalQuantityTarget = product.getString("totalQuantityTarget");

                String line = "Product Name: " + productName
                        + ", Price per Unit: " + pricePerUnit
                        + ", Total Quantity Target: " + totalQuantityTarget;

                menuBuilder.append(i + 1).append(". ").append(line).append("\n");
            }

            String menu = menuBuilder.toString();
            return new MenusMananger(request).storesMenu(menu);
        }
        else
        {
            return new MenusMananger(request).homeMenu();
        }
    }



    private Object processRequest11(RequestModel request)
    {
        String userSelection = request.userResponse().trim();
        String cacheKeyTwo = request.getCacheKeys().get(1);
        String object = request.getCacheKeys().get(6);

        JSONArray stores = new JSONArray();
        JSONObject details = new JSONObject();
        JSONArray products = new JSONArray();

        try
        {
            int index = Integer.parseInt(userSelection);

            var objects = new RedisDao().getSet(object).getJSONArray("objects");
            if (objects != null && index > 0 && index <= objects.length())
            {
                var selectedObject = objects.getJSONObject(index - 1);
                var storesArray = selectedObject.getJSONArray("stores");
                var productOwner = selectedObject.getString("productOwner");

                for (int i = 0; i < storesArray.length(); i++)
                {
                    var store = storesArray.getJSONObject(i);
                    var storeName = store.getString("name");
                    var storeProducts = store.getJSONArray("products");

                    stores.put(storeName);

                    for (int j = 0; j < storeProducts.length(); j++)
                    {
                        var product = storeProducts.getJSONObject(j);
                        var productName = product.getString("productName");
                        var pricePerUnitDouble = product.getDouble("pricePerUnit");
                        var pricePerUnit = String.valueOf(pricePerUnitDouble);
                        var totalQuantityTarget = product.getString("totalQuantityTarget");

                        var productDetails = new JSONObject();
                        productDetails
                                .put("productName", productName)
                                .put("pricePerUnit", pricePerUnit)
                                .put("totalQuantityTarget", totalQuantityTarget);

                        products.put(productDetails);
                    }
                }

                details
                        .put("productOwner", productOwner)
                        .put("stores", stores)
                        .put("products", products);

                if (!new RedisDao().cacheOperation(cacheKeyTwo, details))
                {
                    return "Failed, Kindly try again later";
                }

                var menuBuilder = new StringBuilder("The Products Listed\n");
                var productsArray = details.getJSONArray("products");
                for (int i = 0; i < productsArray.length(); i++)
                {
                    var product = productsArray.getJSONObject(i);
                    var productName = product.getString("productName");
                    var pricePerUnit = product.getString("pricePerUnit");
                    var totalQuantityTarget = product.getString("totalQuantityTarget");

                    String line = "Product Name: " + productName
                            + ", Price per Unit: " + pricePerUnit
                            + ", Total Quantity Target: " + totalQuantityTarget;

                    menuBuilder.append(i + 1).append(". ").append(line).append("\n");
                }

                String menu = menuBuilder.toString();
                return new MenusMananger(request).storesMenu(menu);
            }
            else
            {
                return new MenusMananger(request).homeMenu();
            }
        }
        catch (NumberFormatException e)
        {
            return "Invalid input. Please enter a valid index.";
        }
    }
}
