/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.act.kilimo.engine.interfaces;

import com.act.kilimo.model.RequestModel;

/**
 *
 * @author coder
 */
public interface IRule<T> {
    
    public Boolean matches(String menucode);
    
    public Object apply(RequestModel request);
    
}
