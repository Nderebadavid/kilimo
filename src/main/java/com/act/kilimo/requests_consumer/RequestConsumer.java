/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.act.kilimo.requests_consumer;

import com.act.kilimo.dao.RedisDao;
import com.act.kilimo.engine.engine.Engine;
import com.act.kilimo.engine.implementation.MenusMananger;
import com.act.kilimo.model.RequestModel;

/**
 *
 * @author coder
 */
public class RequestConsumer {

    public static String initService(RequestModel request)
    {

        return new MenusMananger(request).homeMenu().toString();

    }



    public static String completeService(RequestModel request)
    {

        var msisdn = request.mobileNumber();
        var menucode = new RedisDao().getSet(msisdn).get("menucode").toString();

        return new Engine(request, menucode).processRequest();

    }

}
