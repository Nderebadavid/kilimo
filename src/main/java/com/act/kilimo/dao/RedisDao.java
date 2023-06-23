/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.act.kilimo.dao;

import com.act.kilimo.configurations.RedisConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author coder
 */
public class RedisDao {

    
    RedisConnection connector;
    private static final Logger LOGGER = LogManager.getLogger(RedisDao.class);



    
    public RedisDao()
    {
        this.connector = new RedisConnection();
    }
    
    public boolean saveSession(String msisdn, String menucode, String description){
        
        var object = new JSONObject()
                .put("msisdn", msisdn)
                .put("menucode", menucode)
                .put("description", description);
        
        var member = object.toString();
        try(var jedis = connector.getRedisConnection())
        {
            deleteSet(msisdn);
            return  jedis.setex(msisdn, 360, member).equalsIgnoreCase("ok");
        }
        catch (Exception exception)
        {
            LOGGER.error("ERROR: " + exception.getClass().getSimpleName() + "<<<<<<<<<<| Messsage |>>>>>>>>>" + exception.getMessage());
            return false;
        }
        
    }
    
    public boolean cacheOperation(String key, JSONObject payload){
        
        String member = payload.toString();
        try(var jedis =connector.getRedisConnection())
        {
            deleteSet(key);
            return  jedis.setex(key, 360, member).equalsIgnoreCase("ok");
        }
        catch (Exception exception)
        {
            LOGGER.error("ERROR: " + exception.getClass().getSimpleName() + "<<<<<<<<<<| Messsage |>>>>>>>>>" + exception.getMessage());
            return false;
        }
        
    }
    
    public JSONObject getSet(String key){
        
        try(var jedis = connector.getRedisConnection())
        {
            return  (jedis.get(key) != null) ? new JSONObject(jedis.get(key)) : new JSONObject();
        }
        catch (Exception exception)
        {
            LOGGER.error("ERROR: " + exception.getClass().getSimpleName() + "<<<<<<<<<<| Messsage |>>>>>>>>>" + exception.getMessage());
            return new JSONObject();
        }
        
    }
    
    
    public boolean deleteSet(String key){
        
        var deleted = false;
        try(var jedis = connector.getRedisConnection())
        {
            deleted = (jedis.del(key) > 0);
        }
        catch (Exception exception)
        {
            LOGGER.error("ERROmsiR: " + exception.getClass().getSimpleName() + "<<<<<<<<<<| Messsage |>>>>>>>>>" + exception.getMessage());
            deleted = false;
        }
        
        return  deleted;
        
    }
    
    
}
