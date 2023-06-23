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
public record Redis(
        
    String host,
    String password,
    String username,
    int timeout,
    int port,
    int maxConnections
        
    ) {

    public Redis(JSONObject redis){
        
        this(redis.getString("host"), 
                redis.getString("password"),
                redis.getString("username"), 
                redis.getInt("timeout"), 
                redis.getInt("port"), 
                redis.getInt("maxConnections")
        );
        
    }
}