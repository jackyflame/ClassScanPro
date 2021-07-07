package com.jf.interfaces;

/**
 * @Class: IProviderManager
 * @Description:
 * @author: github.com/jackyflame
 * @Date: 2021/7/7
 */
public interface IProviderManager {

    void addProvider(IProvider provider, Class<? extends IActionHandler> target);

    IProvider getProvider(Class<? extends IActionHandler> target);

}
