/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.act.kilimo.engine.implementation;

import com.act.kilimo.configurations.FileConfigs;
import com.act.kilimo.dao.RedisDao;
import com.act.kilimo.model.Menus;
import com.act.kilimo.model.RequestModel;
import com.act.kilimo.utility.Constants;

/**
 *
 * @author coder
 */
public class MenusMananger {

    private final String msisdn;
    private final String start;
    private final String end;
    private final RequestModel request;



    public MenusMananger(RequestModel request)
    {
        this.request = request;
        this.msisdn = request.mobileNumber();
        this.start = request.start();
        this.end = request.end();
    }

    Menus menus = new Menus(new FileConfigs().getConfigs().menus());



    public Object homeMenu()
    {

        new RedisDao().saveSession(msisdn, Constants.menus.homeMenu.name(), "A menucode for home menu");
        return start + menus.homeMenu();

    }



    public Object firstname()
    {

        new RedisDao().saveSession(msisdn, Constants.menus.firstnameMenu.name(), "A menucode for firstname menu");
        return start + menus.firstnameMenu();

    }



    public Object lastname()
    {

        new RedisDao().saveSession(msisdn, Constants.menus.lastname.name(), "A menucode for lastname menu");
        return start + menus.lastnameMenu();

    }



    public Object idNumberMenu()
    {

        new RedisDao().saveSession(msisdn, Constants.menus.idNumberMenu.name(), "A menucode for idNumberMenu menu");
        return start + menus.idNumberMenu();

    }



    public Object locationMenu()
    {

        new RedisDao().saveSession(msisdn, Constants.menus.locationMenu.name(), "A menucode for locationMenu menu");
        return start + menus.locationMenu();

    }



    public Object productOwnersViewMenu(String menu)
    {

        new RedisDao().saveSession(msisdn, Constants.menus.productOwnersViewMenu.name(), "A menucode for productOwners menu");
        return start + menu.replace("#MESSAGE", menu);

    }



    public Object productDetailsConfirmationMenu(String menu)
    {

        new RedisDao().saveSession(msisdn, Constants.menus.productDetailsConfirmationMenu.name(), "A menucode for productDetailsConfirmationMenu menu");
        return start + menus.productDetailsConfirmationMenu().replace("#MESSAGE", menu);

    }
    
       public Object confirmDeliveryMenu(String menu)
    {

        new RedisDao().saveSession(msisdn, Constants.menus.confirmDeliveryMenu.name(), "A menucode for confirmDeliveryMenu menu");
        return start + menus.confirmDeliveryMenu().replace("#MESSAGE", menu);

    }



    public Object productsListedMenu(String menu)
    {

        new RedisDao().saveSession(msisdn, Constants.menus.productsListedMenu.name(), "A menucode for productsListed menu");
        return start + menu.replace("#MESSAGE", menu);

    }



    public Object productsMenu(String menu)
    {

        new RedisDao().saveSession(msisdn, Constants.menus.productsMenu.name(), "A menucode for productsListed menu");
        return start + menu.replace("#MESSAGE", menu);

    }



    public Object storesMenu(String menu)
    {

        new RedisDao().saveSession(msisdn, Constants.menus.storesMenu.name(), "A menucode for stores menu");
        return start + menu.replace("#MESSAGE", menu);

    }



    public Object productAndStoreListingMenu()
    {

        new RedisDao().saveSession(msisdn, Constants.menus.productAndStoreListingMenu.name(), "A menucode for productAndStoreListingMenu menu");
        return start + menus.productAndStoreListingMenu();

    }



    public Object productDeliveryMenu()
    {

        new RedisDao().saveSession(msisdn, Constants.menus.productDeliveryMenu.name(), "A menucode for productDeliveryMenu menu");
        return start + menus.productDeliveryMenu();

    }



    public Object summaryMenu()
    {

        new RedisDao().saveSession(msisdn, Constants.menus.summaryMenu.name(), "A menucode for summaryMenu menu");
        return start + menus.summaryMenu();

    }



    public Object ProductsdeliveredMenu(String message)
    {

        new RedisDao().saveSession(msisdn, Constants.menus.ProductsdeliveredMenu.name(), "Amenucode for productsDeliveredMenu");
        return start + menus.ProductsdeliveredMenu().replace("#MESSAGE", message);

    }
    
    
    public Object deliverProductMenu(String message)
    {

        new RedisDao().saveSession(msisdn, Constants.menus.deliverProductMenu.name(), "Amenucode for deliverProductMenu");
        return start + menus.deliverProductMenu().replace("#MESSAGE", message);

    }



    public Object FinancialOperationsMenu(String message)
    {

        new RedisDao().saveSession(msisdn, Constants.menus.FinancialOperationsMenu.name(), "Amenucode for FinancialOperationsMenu");
        return start + menus.FinancialOperationsMenu().replace("#MESSAGE", message);

    }



    public Object endOperationMenu(String message)
    {

        new RedisDao().saveSession(msisdn, Constants.menus.endOperationMenu.name(), "A menucode for endOperationMenu menu");
        return start + menus.endOperationMenu().replace("#MESSAGE", message);

    }
    
     
    public Object invalidResponseMenu(String message)
    {

        new RedisDao().saveSession(msisdn, Constants.menus.invalidResponseMenu.name(), "Amenucode for invalidResponseMenu");
        return start + menus.invalidResponseMenu().replace("#MESSAGE", message);

    }



    public Object endSessionMenu()
    {

        new RedisDao().saveSession(msisdn, Constants.menus.endSessionMenu.name(), "A menucode for endSessionMenu menu");
        return start + menus.endSessionMenu();

    }

}
