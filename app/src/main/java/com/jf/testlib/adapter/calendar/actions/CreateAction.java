package com.jf.testlib.adapter.calendar.actions;

import com.jf.anotations.Action;
import com.jf.interfaces.IActionHandler;
import com.jf.testlib.adapter.calendar.CalendarSkillHandler;

/**
 * @Class: CreateAction
 * @Description:
 * @author: github.com/jackyflame
 * @Date: 2021/7/6
 */
@Action(skill = CalendarSkillHandler.class,actions = {"CREATE_1","CREATE_2"})
public class CreateAction implements IActionHandler {

}
