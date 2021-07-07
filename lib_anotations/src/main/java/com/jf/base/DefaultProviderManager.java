package com.jf.base;

import com.jf.interfaces.IActionHandler;
import com.jf.interfaces.IProvider;
import com.jf.interfaces.IProviderManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @Class: DefaultProviderManager
 * @Description:
 * @author: github.com/jackyflame
 * @Date: 2021/7/7
 */
public class DefaultProviderManager implements IProviderManager {

    private Map<Class<?>,IProvider> providerMap = new HashMap<>();

    private static class SingletonHolder {
        static final DefaultProviderManager INSTANCE = new DefaultProviderManager();
    }

    public static DefaultProviderManager getInstance() {
        return DefaultProviderManager.SingletonHolder.INSTANCE;
    }

    @Override
    public void addProvider(IProvider provider, Class<? extends IActionHandler> target) {
        providerMap.put(target,provider);
    }

    @Override
    public IProvider getProvider(Class<? extends IActionHandler> target) {
        return providerMap.get(target);
    }
}
