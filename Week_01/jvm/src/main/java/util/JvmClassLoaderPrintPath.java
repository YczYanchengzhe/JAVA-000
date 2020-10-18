package util;


import sun.misc.Launcher;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * Created by yanchengzhe
 * Date 2020/10/8 22:16
 * Description 排查找不到Jar包的问题
 *  打印加载的类清单和加载顺序 : 在类的启动命令行参数加上 ‐XX:+TraceClassLoading 或者 ‐verbose 即 可，注意需要加载java命令之后，要执行的类名之前，不然不起作用
 *  指定启动类加载器加载某个jar : -Dsun.boot.class.path="C:\Program Files\Java\jdk1.8.0_144\jre\lib\rt.jar"
 *  指定扩展类加载器加载某个jar : ‐Djava.ext.dirs
 * @author yanchengzhe
 */
public class JvmClassLoaderPrintPath {
    public static void main(String[] args) {
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("====> 加载启动类");
        for (URL url : urls) {
            System.out.println(url.toExternalForm());
        }
        printClassLoader("扩展类加载器",JvmClassLoaderPrintPath.class.getClassLoader().getParent());
        printClassLoader("应用类加载器",JvmClassLoaderPrintPath.class.getClassLoader());
    }

    public static void printClassLoader(String name, ClassLoader CL) {
        if (CL != null) {
            System.out.println(name + " ClassLoader ‐> " + CL.toString());
            printURLForClassLoader(CL);
        } else {
            System.out.println(name + " ClassLoader ‐> null");
        }
    }

    public static void printURLForClassLoader(ClassLoader CL) {
        //TODO ucp 是什么???
        Object ucp = insightField(CL, "ucp");
        Object path = insightField(ucp, "path");
        ArrayList ps = (ArrayList) path;
        for (Object p : ps) {
            System.out.println(" ==> " + p.toString());
        }
    }

    private static Object insightField(Object obj, String fName) {
        try {
            Field f = null;
            if (obj instanceof URLClassLoader) {
                f = URLClassLoader.class.getDeclaredField(fName);
            } else {
                f = obj.getClass().getDeclaredField(fName);
            }
            f.setAccessible(true);
            return f.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
