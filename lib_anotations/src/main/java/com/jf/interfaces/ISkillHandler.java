package com.jf.interfaces;

import java.util.Map;

/**
 * @Class: ISkillHandler
 * @Description:
 * @author: github.com/jackyflame
 * @Date: 2021/7/7
 */
public interface ISkillHandler {

    void handle(String message);

    void addActionHandler(String action,IActionHandler handler);

    void addActionHandler(Map<String, IActionHandler> cacheMap);

}
