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
public class SummaryMenuRule implements ServiceRule {

    @Override
    public Boolean matches(String menucode)
    {
        return menucode.equalsIgnoreCase(new RuleTagsModel().getServiceRule(SummaryMenuRule.class));
    }



    @Override
    public Object apply(RequestModel request)
    {
        return processRequest(request);
    }



    private Object processRequest(RequestModel request)
    {

        var userResponse = request.userResponse();
        var productOwner1 = "productOwner1";
        var productOwner2 = "productOwner2";

        var prod1 = "Product A, TotalQuantityDelivered = 1000";
        var prod2 = "Product B, TotalQuantityDelivered = 1200";
        var prod3 = "Product c, TotalQuantityDelivered = 700";
        
         var prod4 = "Product A, TotalQuantityDelivered = 1000";
        var prod5 = "Product B, TotalQuantityDelivered = 1200";
        var prod6 = "Product c, TotalQuantityDelivered = 700";
        
        
        var clearedAmmount = 100_000;
        var pendingAmmount = 75_000;
        var accountName = "user1";

        switch (userResponse)
        {
            case "1" ->
            {

                var msg = new StringBuilder().append("The products Delivered so far:\n\nProduct Owner: ").append(productOwner1)
                        .append("\nProducts Delivered: \n").append(prod1).append("\n").append(prod2).append("\n").append(prod3).append("\n\n")
                        .append("Product Owner: ").append(productOwner2)
                        .append("\nProducts Delivered: \n").append(prod4).append("\n").append(prod5).append("\n").append(prod6).append("\n\n").toString();
                
                return new MenusMananger(request).ProductsdeliveredMenu(msg);
                
            }
            
            case "2" ->{
                
                var msg = new StringBuilder().append("My Account Transactions:\n\nAccount Name:" ).append(accountName).append("\n")
                        .append("\t").append("The Total Ammount Cleared: ").append(clearedAmmount).append("\n").append("\t")
                        .append("Pending Payment: ").append(pendingAmmount).toString();
                
                return new MenusMananger(request).FinancialOperationsMenu(msg);
                
            }

            default ->
                throw new AssertionError();
        }

    }

}
