/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.act.kilimo.model;

import org.json.JSONObject;

/**
 *
 * @author coder
 */
public record Menus(
        String homeMenu,
        String summaryMenu,
        String productAndStoreListingMenu,
        String productDeliveryMenu,
        String firstnameMenu,
        String lastnameMenu,
        String locationMenu,
        String idNumberMenu,
        String endSessionMenu,
        String endOperationMenu,
        String successMenu,
        String productsMenu,
        String productsListedMenu,
        String storesMenu,
        String productOwnersViewMenu,
        String productDetailsConfirmationMenu,
        String ProductsdeliveredMenu,
        String FinancialOperationsMenu,
        String deliverProductMenu,
        String confirmDeliveryMenu,
        String invalidResponseMenu
        ) {

    public Menus(JSONObject menus)
    {

        this(
                menus.getString("homeMenu"),
                menus.getString("summaryMenu"),
                menus.getString("productAndStoreListingMenu"),
                menus.getString("productDeliveryMenu"),
                menus.getString("firstnameMenu"),
                menus.getString("lastnameMenu"),
                menus.getString("locationMenu"),
                menus.getString("idNumberMenu"),
                menus.getString("endSessionMenu"),
                menus.getString("endOperationMenu"),
                menus.getString("successMenu"),
                menus.getString("productsMenu"),
                menus.getString("productsListedMenu"),
                menus.getString("storesMenu"),
                menus.getString("productOwnersViewMenu"),
                menus.getString("productDetailsConfirmationMenu"),
                menus.getString("ProductsdeliveredMenu"),
                menus.getString("FinancialOperationsMenu"),
                menus.getString("deliverProductMenu"),
                menus.getString("confirmDeliveryMenu"),
                menus.getString("invalidResponseMenu")
        );

    }
}
