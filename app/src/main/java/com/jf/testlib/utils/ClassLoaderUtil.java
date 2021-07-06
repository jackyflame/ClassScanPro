package com.jf.testlib.utils;

import android.content.Context;
import android.os.Build;

import com.jf.anotations.Action;
import com.jf.anotations.Skill;
import com.jf.base.IActionHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.DexFile;

/**
 * @Class: ClassLoaderUtil
 * @Description:
 * @author: github.com/jackyflame
 * @Date: 2021/6/24
 */
public class ClassLoaderUtil {

    private static class SingletonHolder {
        static final ClassLoaderUtil INSTANCE = new ClassLoaderUtil();
    }

    public static ClassLoaderUtil getInstance() {
        return ClassLoaderUtil.SingletonHolder.INSTANCE;
    }

    public void testSrcPath() throws IOException {
        //包名
        String basePack = "com.fawvw.vehicle.diy.launcher.widget.voice.common";
        //先把包名转换为路径,首先得到项目的classpath
        String classpath = System.getProperty("user.dir");
        System.out.println(">>> classpath:"+classpath);
        //String classpath = getClass().getPackage().getName().replace(".", "\\");
        //String classpath = "";
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        System.out.println(">>> courseFile:" + courseFile);
        //// 第一种：获取类加载的根路径   D:\git\daotie\daotie\target\classes
        //File f = new File(this.getClass().getResource("/").getPath());
        //System.out.println(f);
        // 第四种： D:\git\daotie\daotie
        System.out.println(">>> user.dir:" + System.getProperty("user.dir"));

        //然后把我们的包名basPach转换为路径名
        basePack = basePack.replace(".", File.separator);
        System.out.println(">>> basePack new:"+basePack);
        //然后把classpath和basePack合并
        String searchPath = classpath +"src/main/java/"+ basePack;
        System.out.println(">>> searchPath :"+searchPath);
    }

    public void searchCLass(Context context) throws Exception {
        //包名
        String targetPackName = "com.jf.testlib.adapter";
        List<String> classNameList = getClassName(context,targetPackName);
        //这个时候我们已经得到了指定包下所有的类的绝对路径了。我们现在利用这些绝对路径和java的反射机制得到他们的类对象
        int i = 0;
        for (String s : classNameList) {
            Class<?> cls = Class.forName(s);
            if(cls.isAnnotationPresent(Skill.class)){
                Skill skillAno = cls.getAnnotation(Skill.class);
                System.out.println(">>> Skill-Name:" + skillAno.skillName() + " from Class:" + cls);
            }
            if(cls.isAnnotationPresent(Action.class)){
                Action actionAno = cls.getAnnotation(Action.class);
                System.out.println(">>> Action-array from Class:" + cls);
                if(actionAno.actions() != null && actionAno.actions().length > 0){
                    for (String action:actionAno.actions()) {
                        System.out.println(action);
                    }
                }else{
                    System.out.println("empty-actions");
                }
            }
            //if(hasImplementsInterface(cls,IHandler.class)){
            if(IActionHandler.class.isAssignableFrom(cls)){
                System.out.println(">>> index:"+(i++) + ">>> Class:"+cls);
            }
        }
    }

    private boolean hasImplementsInterface(Class<?> target,Class<?> itf) {
        Class<?>[] interfacesArray = target.getInterfaces();//获取这个类的所以接口类数组
        for (Class<?> item : interfacesArray) {
            if (item == itf) { //判断是否有继承的接口
                return true;
            }
        }
        Class<?>  superClz = target.getSuperclass();
        if(superClz != null && superClz != Object.class){
            return hasImplementsInterface(superClz,itf);
        }else{
            return false;
        }
        //return itf.isAssignableFrom(target);
    }

    /**
     * 该方法会得到所有的类，将类的绝对路径写入到classPaths中
     * @param file
     */
    private void doPath(List<String> classPaths , File file) {
        if (file.isDirectory()) {//文件夹
            //文件夹我们就递归
            File[] files = file.listFiles();
            for (File f1 : files) {
                doPath(classPaths, f1);
            }
        } else {//标准文件
            //标准文件我们就判断是否是class文件
            if (file.getName().endsWith(".class") || file.getName().endsWith(".java")) {
                //如果是class文件我们就放入我们的集合中。
                classPaths.add(file.getPath());
            }
        }
    }

    public List<String> getClassName(Context context, String packageName){
        List<String>classNameList = new ArrayList<String>();
        try {
            String packagePath = context.getPackageCodePath();
            DexFile df = new DexFile(packagePath);//通过DexFile查找当前的APK中可执行文件
            Enumeration<String> enumeration = df.entries();//获取df中的元素  这里包含了所有可执行的类名 该类名包含了包名+类名的方式
            while (enumeration.hasMoreElements()) {//遍历
                String className = (String) enumeration.nextElement();
                if (className.startsWith(packageName)) {//在当前所有可执行的类里面查找包含有该包名的所有类
                    classNameList.add(className);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  classNameList;
    }

}
