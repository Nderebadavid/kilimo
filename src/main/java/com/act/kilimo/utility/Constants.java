/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.act.kilimo.utility;

/**
 *
 * @author coder
 */
public class Constants {

    public Constants()
    {
    }
    
    //util constants
    public static final String CONTENT_TYPE_KEY = "Content-Type";
    public static final String USER_AGENT_KEY = "User-Agent";
    public static final String AUTHORIZATION_KEY = "Authorization";
    public static final String USER_AGENT_VALUE = "HttpClient";
    public static final String CONTENT_TYPE_VALUE = "application/json";

    //urls
    public static final String FETCHFILESETTINGS_URL_STRING = "http://0.0.0.0:8080/api/loadFilesFour";
    public static final String ADD_USER_URL = "http://localhost:8080/api/users/addUser";
    public static final String GET_ALL_LOCATIONS_URL = "http://0.0.0.0:8080/api/getAllLocations";
    public static final String LOCATION_ID_URL = "http://0.0.0.0:8080/api/locations/getLocationId";
    public static final String FETCH_DATA1_URL = "http://0.0.0.0:8080/api/fetchData";
    
    
    public static enum  menus{
        
        homeMenu,  summaryMenu,productAndStoreListingMenu,productDeliveryMenu,firstnameMenu,lastname,locationMenu,idNumberMenu,
        endSessionMenu, productsMenu, productsListedMenu,storesMenu,productOwnersViewMenu,productDetailsConfirmationMenu, endOperationMenu,
        ProductsdeliveredMenu, FinancialOperationsMenu, deliverProductMenu, confirmDeliveryMenu, invalidResponseMenu
        
    }
    
}
