import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class JavaTemplate {
    public static void main(String[] args) {

        String jarName = "";
        String className = "";
        String methodName = "";
        try{
            jarName = args[0];
            className = args[1];
            methodName = args[2];
        }catch (Exception e) {
            System.out.println("参数有误，请检查");
        }
        List<String> nextArgs = new ArrayList<String>();
        for (int i = 3;i< args.length-3;i++){
            nextArgs.add(args[i]);
        }
        String path = "file:" + jarName;
        URLClassLoader urlClassLoader = null;
        Class<?> MyTest = null;
        try {
            urlClassLoader = new URLClassLoader(new URL[]{new URL(path)});
            MyTest = urlClassLoader.loadClass(className);
            Object instance = MyTest.newInstance();
            Method method = MyTest.getMethod(methodName);
            if(!nextArgs.isEmpty()){
                method.invoke(instance,nextArgs);
            }
            method.invoke(instance);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                urlClassLoader.close();
            }catch (IOException e) {
            }
        }
    }
}

