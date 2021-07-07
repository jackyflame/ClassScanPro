package com.jf.testlib.adapter.phone.actions;

import com.jf.anotations.Action;
import com.jf.interfaces.IActionHandler;
import com.jf.testlib.adapter.phone.PhoneSkillHandler;

/**
 * @Class: CallInAction
 * @Description:
 * @author: github.com/jackyflame
 * @Date: 2021/7/6
 */
@Action(skill = PhoneSkillHandler.class, actions = {"ACCEPT", "DENI"})
public class CallInAction implements IActionHandler {
}
