package com.smart.framework.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @project Writing Java web frame from zero
 * @package com.smart.framework.utils
 * @file ClassUtils.java
 * @time 2015-12-17下午05:07:54
 * @description 
 * @author   nilu
 * @version 1.0
 *
 */
public class ClassUtils {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtils.class);
    
    /**
     * @description load all files in the package
     * @time 2015-12-21下午08:58:26
     * @param packageName
     * @return
     * @author   nilu
     */
    public static Set<Class<?>> getClassSet(String packageName) {
        final Set<Class<?>> classSet = new HashSet<Class<?>>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            URL url = null;
            String protocol = null;
            while(urls.hasMoreElements()) {
                url = urls.nextElement();
                if(!GyUtils.isNull(url)) {
                    protocol = url.getProtocol();
                    //文件或者jar包
                    if("file".equals(protocol)) {
                        String packagePath = url.getPath().replaceAll("%20", "");
                        addClass(classSet, packagePath, packageName);
                    } else if("jar".equals(protocol)) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if(!GyUtils.isNull(jarURLConnection)) {
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if(!GyUtils.isNull(jarFile)) {
                                Enumeration<JarEntry> jarEnties = jarFile.entries();
                                while(jarEnties.hasMoreElements()) {
                                    JarEntry jarEntry = jarEnties.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if(jarEntryName.endsWith(".class")) {
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                        doAddClass(classSet, className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("get class set failure!", e);
            e.printStackTrace();
        }
        
        return classSet;
    }
    
    /**
     * @description 
     * @time 2015-12-21下午09:41:40
     * @param classSet
     * @param className
     * @author   nilu
     */
    private static void doAddClass(Set<Class<?>> classSet, String className) {
        try {
            Class<?> clazz = Class.forName(className, false, getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("add class failure!",e);
            e.printStackTrace();
        }
    }

    /**
     * @description 
     * @time 2015-12-21下午09:36:09
     * @param classSet
     * @param packagePath
     * @param packageName
     * @author   nilu
     */
    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
           
        });
        
        for(File file : files) {
            String fileName = file.getName();
            if(file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if(!GyUtils.isNull(packageName)) {
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            } else {
                String subPackagePath = fileName;
                if(!GyUtils.isNull(fileName)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if(!GyUtils.isNull(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }

    public static ClassLoader getClassLoader() {
        return ClassUtils.class.getClassLoader();
    }
}
