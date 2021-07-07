package com.jf.testlib.adapter.music.actions;

import com.jf.anotations.Action;
import com.jf.interfaces.IActionHandler;
import com.jf.testlib.adapter.music.IListenSkillHandler;

/**
 * @Class: MusicAction
 * @Description:
 * @author: github.com/jackyflame
 * @Date: 2021/7/6
 */
@Action(skill = IListenSkillHandler.class, actions = {"OPEN", "CLOSE", "LIST"})
public class MusicAction implements IActionHandler {
}
