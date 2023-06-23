/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.act.kilimo.configurations;

import com.act.kilimo.model.Redis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 *
 * @author coder
 */
public class RedisConnection {

    public RedisConnection()
    {
    }
    
   public Jedis getRedisConnection(){
       
       var redisConfigs = new Redis( new FileConfigs().getConfigs().redis());
       
       var host = redisConfigs.host();
       var port = redisConfigs.port();
       var password = redisConfigs.password();
       var timeout = redisConfigs.timeout();
       var maxConnections =redisConfigs.maxConnections();
       
       var poolfConfig = new JedisPoolConfig();
       poolfConfig.setMaxTotal(maxConnections);
       
       return  new JedisPool(poolfConfig, host, port, timeout, password,false).getResource();
       
   }
    
}
