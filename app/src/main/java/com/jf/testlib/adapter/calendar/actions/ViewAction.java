package com.jf.testlib.adapter.calendar.actions;

import com.jf.anotations.Action;
import com.jf.interfaces.IActionHandler;
import com.jf.testlib.adapter.calendar.CalendarSkillHandler;

/**
 * @Class: ViewAction
 * @Description:
 * @author: github.com/jackyflame
 * @Date: 2021/7/6
 */
@Action(skill = CalendarSkillHandler.class, actions = {"VIEW_DATE", "VIEW_TIME"})
public class ViewAction implements IActionHandler {
}
