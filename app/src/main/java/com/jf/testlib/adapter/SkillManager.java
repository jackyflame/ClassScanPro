package com.jf.testlib.adapter;

import android.content.Context;

import com.jf.interfaces.IActionHandler;
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
    private final Map<Class<? extends ISkillHandler>, Map<String, IActionHandler>> skillActionCacheMap = new HashMap<>();

    private static class SingletonHolder {
        static final SkillManager INSTANCE = new SkillManager();
    }

    public static SkillManager getInstance() {
        return SkillManager.SingletonHolder.INSTANCE;
    }

    public void init(Context context){
        try {
            ClassLoaderUtil.getInstance().scanSkillToPool(context,"com.jf.testlib.adapter",this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("SkillManager init success!!");
    }

    public void addSkillHandler(String skill, ISkillHandler skillHandler){
        Map<String, IActionHandler> cacheMap = skillActionCacheMap.get(skillHandler.getClass());
        if(cacheMap != null && cacheMap.size() > 0){
            skillHandler.addActionHandler(cacheMap);
            skillActionCacheMap.remove(skillHandler.getClass());
        }
        skillHandlerMap.put(skill,skillHandler);
    }

    public void attachActionToSkill(Class<? extends ISkillHandler> skill, String action, IActionHandler newAH){
        ISkillHandler skillHandler = getSkillHandlerByType(skill);
        if(skillHandler != null){
            skillHandler.addActionHandler(action,newAH);
        }else{
            Map<String, IActionHandler> cacheMap = skillActionCacheMap.get(skill);
            if(cacheMap == null){
                cacheMap = new HashMap<>();
            }
            cacheMap.put(action,newAH);
            skillActionCacheMap.put(skill,cacheMap);
        }
    }

    private ISkillHandler getSkillHandlerByType(Class<? extends ISkillHandler> clz){
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
