package tron.deployment.pojo;

import com.sun.javafx.image.impl.ByteIndexed;
import lombok.Getter;
import org.apache.tomcat.Jar;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarScanner {
    public static void main(String[] args){
        JarScanner jarScanner = new JarScanner();
        List<Class<?>> classes = jarScanner.getClasses("FullNode.jar");

    }

    List<Class<?>> getClasses(String jarPath){
        int num = 0;
        int total = 0;
        List<Class<?>> classes = new ArrayList<>();
        try{
            JarFile jarFile = new JarFile(jarPath);
            Enumeration jarEnumeration = jarFile.entries();
            URL[] urls = { new URL("jar:file:" + jarPath +"!/") };
            URLClassLoader cl = URLClassLoader.newInstance(urls);
            while(jarEnumeration.hasMoreElements()){
                total++;
                JarEntry entry = (JarEntry) jarEnumeration.nextElement();
                // filter classes not needed, hardcode org/tron, might use param later
                if(entry.isDirectory() || !entry.getName().endsWith(".class") || !entry.getName().startsWith("org/tron")){
                    continue;
                }
                String className = entry.getName().substring(0,entry.getName().length()-6);
                className = className.replace('/', '.');
                try{
                    Class c = cl.loadClass(className);
                    classes.add(c);
                }
                catch (Exception e1){
                    e1.printStackTrace();
                    continue;
                }
                num++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(num);
        System.out.println(total);
        return classes;
    }

    List<String> getFields(List<Class<?>> classes){
        for(Class<?> c : classes){

        }
        return null;
    }

}
