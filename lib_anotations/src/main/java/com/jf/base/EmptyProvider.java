package com.jf.base;

import com.jf.interfaces.IActionHandler;
import com.jf.interfaces.IProvider;

/**
 * @Class: DefaultProVider
 * @Description:
 * @author: github.com/jackyflame
 * @Date: 2021/7/7
 */
public class EmptyProvider implements IProvider {

    @Override
    public IActionHandler create() {
        return null;
    }

}
