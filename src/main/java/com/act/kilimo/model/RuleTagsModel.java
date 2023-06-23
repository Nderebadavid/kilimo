/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.act.kilimo.model;

import com.act.kilimo.engine.interfaces.ServiceRule;
import com.act.kilimo.engine.rules.ConfirmDeliveryRule;
import com.act.kilimo.engine.rules.DeliverProductRule;
import com.act.kilimo.engine.rules.FirstnameMenuRule;
import com.act.kilimo.engine.rules.HomeMenuRule;
import com.act.kilimo.engine.rules.IdNumberMenuRule;
import com.act.kilimo.engine.rules.InvalidResponseRule;
import com.act.kilimo.engine.rules.LastnameMenuRule;
import com.act.kilimo.engine.rules.LocationMenuRule;
import com.act.kilimo.engine.rules.ProductAndStoreListingMenu;
import com.act.kilimo.engine.rules.ProductDetailsConfirmationRule;
import com.act.kilimo.engine.rules.ProductsListedRule;
import com.act.kilimo.engine.rules.StoresListedRule;
import com.act.kilimo.engine.rules.SummaryMenuRule;
import com.act.kilimo.utility.Constants;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author coder
 */
public class RuleTagsModel {
    
        private static final Logger LOGGER = LogManager.getLogger(RuleTagsModel.class);
        
       private final Map<Class<?extends ServiceRule>, String> ruleTagMap  = new HashMap<>();
       private final List<ServiceRule> rules =new ArrayList<>();



    public RuleTagsModel()
    {
        initializeRuleTagMap();
        initializeRules();
    }
       
       
       
       
       private void initializeRuleTagMap(){
           ruleTagMap.put(HomeMenuRule.class, Constants.menus.homeMenu.name());
           ruleTagMap.put(FirstnameMenuRule.class, Constants.menus.firstnameMenu.name());
           ruleTagMap.put(LastnameMenuRule.class, Constants.menus.lastname.name());
           ruleTagMap.put(IdNumberMenuRule.class, Constants.menus.idNumberMenu.name());
           ruleTagMap.put(LocationMenuRule.class, Constants.menus.locationMenu.name());
           ruleTagMap.put(ProductAndStoreListingMenu.class, Constants.menus.productAndStoreListingMenu.name());
           ruleTagMap.put(ProductsListedRule.class, Constants.menus.productsMenu.name());
           ruleTagMap.put(StoresListedRule.class, Constants.menus.storesMenu.name());
           ruleTagMap.put(ProductDetailsConfirmationRule.class, Constants.menus.productDetailsConfirmationMenu.name());
           ruleTagMap.put(DeliverProductRule.class, Constants.menus.deliverProductMenu.name());
           ruleTagMap.put(ConfirmDeliveryRule.class, Constants.menus.confirmDeliveryMenu.name());
           ruleTagMap.put(SummaryMenuRule.class, Constants.menus.summaryMenu.name());
           ruleTagMap.put(InvalidResponseRule.class, Constants.menus.invalidResponseMenu.name());
       }
       
       
       private void initializeRules(){
           for (Class<? extends ServiceRule> ruleClass :  ruleTagMap.keySet())
           {
               try
               {
                   ServiceRule rule = ruleClass.getDeclaredConstructor().newInstance();
                   rules.add(rule);
               }
               catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException exception)
               {
                   LOGGER.error("failed to initialize rule" +ruleClass.getSimpleName(), exception);
               }
           }
       }
       
       public String getServiceRule(Class<? extends ServiceRule> ruleClass){
           
           return ruleTagMap.get(ruleClass);
           
       }
       
       public List<ServiceRule> getRules(){
           
           return  rules;
           
       }
       
}
