package org.labit.labitframework.web.servlet;/*
 *  @author LABIT
 *
 *  Copyright LABIT , Software License, Version 1.0
 *
 *  http://labit.pe.kr
 *
 */

import org.labit.labitframework.web.annotation.Controller;
import org.labit.labitframework.web.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description : 클래스에 대한 설명을 입력해주세요.<br>
 * Date : 2023-04<br>
 * History :<br>
 * - 작성자 : LABIT, 날짜 : 2023-04, 설명 : 최초작성<br>
 *
 * @author LABIT
 * @version 1.0
 */
public class HandlerMapping  {

    private static Map<String, Map> handler = new HashMap<>() ;

    public static void initMappingHandle(List<Class<?>> contextAll){

        System.out.println("[WRAN] START INIT MAPPING HANDLER !!");
        Map<Method, Object> controllerMapper = new HashMap<>();
        Map<String, Method> requestMapper = new HashMap<>();

        for(Class<?> allContext : contextAll){
            Controller annotationController = allContext.getAnnotation(Controller.class);
            if(annotationController != null){
                Object controllerNewInstance = null;
                try {
                    System.out.println("[INFO] CREATE CONTROLLER INSTANCE");
                    controllerNewInstance = allContext.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                    System.out.println("[FAIL] DONT CREATE CONTROLLER INSTANCE");
                }
                if(controllerNewInstance != null){
                    Method[] methods = allContext.getDeclaredMethods();
                    for(Method method: methods){
                        RequestMapping annotationRequestMapping = method.getAnnotation(RequestMapping.class);
                        if(annotationRequestMapping != null){
                            controllerMapper.put(method, controllerNewInstance);
                            requestMapper.put(annotationRequestMapping.value(), method);
                            System.out.println("[INFO] SUCCESS MAPPING ");
                        }
                    }
                }
            }
        }
        System.out.println("[INFO] SUCCESS REQUEST MAPPING END !!");
        handler.put("controllerMapping", controllerMapper);
        handler.put("requestMapping", requestMapper);
    }

    public static Object getHandler(HttpServletRequest request){
        System.out.println( handler.get("requestMapping").get(request.getRequestURI()) );
        return handler.get("controllerMapping").get(handler.get("requestMapping").get(request.getRequestURI()));
    }



}
