package homework;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by yanchengzhe
 * Date 2020/10/18 23:20
 * Description
 *
 * @author yanchengzhe
 */
public class ClassLoadTest extends ClassLoader {

    public static void main(String[] args)  {
        System.out.println("test start");
        try {
            //加载类
            Class hello = new ClassLoadTest().loadClass("Hello");
            //获取方法
            Method o = hello.getDeclaredMethod("hello",null);
            //调用
            o.invoke(hello.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("test end");
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        //读取字节码
        byte[] resultByte = new byte[0];
        try {
            resultByte = Files.readAllBytes(Paths.get("D:\\IdeaProjects\\aJiKeStudy\\study\\JAVA-000\\Week_01\\课件\\Hello.xlass"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < resultByte.length; i++) {
            resultByte[i] = (byte) (255 - resultByte[i]);
        }
        return defineClass(className,resultByte,0,resultByte.length);
    }
}
