package study;


import java.util.Base64;

/**
 * Created by yanchengzhe
 * Date 2020/10/8 22:06
 * Description
 *
 * @author yanchengzhe
 */
public class HelloWorldClassLoader extends  ClassLoader{

    public static void main(String[] args) {
        try {
            new HelloWorldClassLoader().loadClass("study.HelloWorld").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        String base64 = "需要的类字节码";
        byte[] bytes = decode(base64);
        return defineClass(name,bytes,0,bytes.length);
    }

    private byte[] decode(String base64){
        return Base64.getDecoder().decode(base64);
    }
}
