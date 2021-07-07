package com.jf.base;

import com.jf.interfaces.IActionHandler;
import com.jf.interfaces.ISkillHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @Class: AbsSkillHandler
 * @Description:
 * @author: github.com/jackyflame
 * @Date: 2021/7/6
 */
public class AbsSkillHandler implements ISkillHandler {

    Map<String, IActionHandler> handlerMap = new HashMap<>();

    @Override
    public void handle(String message) {

    }

    public void addActionHandler(String action, IActionHandler handler) {
        handlerMap.put(action,handler);
    }

    @Override
    public void addActionHandler(Map<String, IActionHandler> cacheMap) {
        handlerMap.putAll(cacheMap);
    }

}
