package com.jf.anotations;

import com.jf.base.EmptyProvider;
import com.jf.interfaces.IProvider;
import com.jf.interfaces.ISkillHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Class: Action
 * @Description:
 * @author: github.com/jackyflame
 * @Date: 2021/7/6
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Action {

    Class<? extends ISkillHandler> skill();

    String[] actions();

    Class<? extends IProvider> provider() default EmptyProvider.class;

}
