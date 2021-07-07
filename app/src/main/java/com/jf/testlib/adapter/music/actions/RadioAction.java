package com.jf.testlib.adapter.music.actions;

import com.jf.anotations.Action;
import com.jf.interfaces.IActionHandler;
import com.jf.testlib.adapter.music.IListenSkillHandler;

/**
 * @Class: RadioAction
 * @Description:
 * @author: github.com/jackyflame
 * @Date: 2021/7/6
 */
@Action(skill = IListenSkillHandler.class, actions = {"LOCAL", "ONLINE"})
public class RadioAction implements IActionHandler {
}
