package com.jf.testlib.adapter;

import android.content.Context;

import com.jf.interfaces.ISkillHandler;
import com.jf.testlib.utils.ClassLoaderUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Class: AdapterManager
 * @Description:
 * @author: github.com/jackyflame
 * @Date: 2021/7/6
 */
public class SkillManager {

    private final Map<String, ISkillHandler> skillHandlerMap = new HashMap<>();

    private static class SingletonHolder {
        static final SkillManager INSTANCE = new SkillManager();
    }

    public static SkillManager getInstance() {
        return SkillManager.SingletonHolder.INSTANCE;
    }

    public void init(Context context){
        try {
            String packName =  "com.jf.testlib.adapter";
            ClassLoaderUtil.getInstance().scanSkillToPool(context,packName,this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addSkillHandler(String skill, ISkillHandler skillHandler){
        skillHandlerMap.put(skill,skillHandler);
    }

    public ISkillHandler getSkillHandlerByType(Class<? extends ISkillHandler> clz){
        Iterator<ISkillHandler> iterator = skillHandlerMap.values().iterator();
        while (iterator.hasNext()){
            ISkillHandler skillHandler = iterator.next();
            if(skillHandler.getClass() == clz){
                return skillHandler;
            }
        }
        return null;
    }
}
