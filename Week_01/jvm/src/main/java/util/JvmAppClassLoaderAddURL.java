package util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by yanchengzhe
 * Date 2020/10/8 22:51
 * Description
 *
 * @author yanchengzhe
 */
public class JvmAppClassLoaderAddURL {
    public static void main(String[] args) {
        //需要额外导入的路径
        String filePath = "file:/d:/path/";
        URLClassLoader classLoader = (URLClassLoader) JvmAppClassLoaderAddURL.class.getClassLoader();
        try {
            Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            method.setAccessible(true);
            URL url = new URL(filePath);
            method.invoke(classLoader,url);
            //路径有点问题?没加载上
            Class.forName("study.HelloWorld");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
