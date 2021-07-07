package com.jf.testlib.adapter.music.actions;

import com.jf.anotations.Action;
import com.jf.interfaces.IActionHandler;
import com.jf.testlib.adapter.music.IListenSkillHandler;

/**
 * @Class: NewsAction
 * @Description:
 * @author: github.com/jackyflame
 * @Date: 2021/7/6
 */
@Action(skill = IListenSkillHandler.class, actions = {"RECOMMEND", "SEARCH"})
public class NewsAction implements IActionHandler {
}
