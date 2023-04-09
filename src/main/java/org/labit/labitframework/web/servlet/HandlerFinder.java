package org.labit.labitframework.web.servlet;/*
 *  @author LABIT
 *
 *  Copyright LABIT , Software License, Version 1.0
 *
 *  http://labit.pe.kr
 *
 */

import org.labit.labitframework.web.servlet.resource.FinderUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Description : 클래스에 대한 설명을 입력해주세요.<br>
 * Date : 2023-04-08<br>
 * History :<br>
 * - 작성자 : LABIT, 날짜 : 2023-04-08, 설명 : 최초작성<br>
 *
 * @author LABIT
 * @version 1.0
 */
public class HandlerFinder extends FinderUtil {

    public HandlerFinder(ServletContext sc){
        BASE_PACKAGE_NAME = sc.getInitParameter(DEFAULT_BASE_PACKAGE_WHICH);
    }

    public static List<Class<?>> HandlerFinder(ServletContext sc){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        getNameReplace();
        System.out.println(BASE_PACKAGE_NAME);
        System.out.println(BASE_PACKAGE_PATH);

        List<Class<?>> allClass = new ArrayList<>();

        List<File> files = new ArrayList<>();
        try {
            Enumeration<URL> enumeration = classLoader.getResources(BASE_PACKAGE_PATH);
            while (enumeration.hasMoreElements()){
                URL resources = enumeration.nextElement();
                files.add(new File(resources.getFile()));
            }
            for(File file : files){
                if(file.isDirectory()) allClass.addAll(findAllHandler(file, BASE_PACKAGE_NAME));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allClass;
    }

    private static List<Class<?>> findAllHandler(File dir, String basePackage){
        List<Class<?>> handlerList = new ArrayList<>();
        if(!dir.exists()){
            return handlerList;
        }
        File[] files = dir.listFiles();
        for(File file: files){
            if(file.isDirectory()){
                System.out.println("[INFO] FIND CLASS PATH: " + file.getName());
                handlerList.addAll(findAllHandler(file, basePackage + NAME_DOT + file.getName()));
            }else if(file.getName().endsWith(".class")){
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                try {
                    System.out.println("[INFO] FIND CLASS PATH: " + file.getName());
                    handlerList.add(Class.forName(basePackage + NAME_DOT + file.getName().substring(0, file.getName().length()-6),false, classLoader));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return handlerList;
    }

}
