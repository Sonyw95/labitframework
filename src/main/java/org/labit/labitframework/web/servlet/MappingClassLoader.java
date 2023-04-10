package org.labit.labitframework.web.servlet;/*
 *  @author LABIT
 *
 *  Copyright LABIT , Software License, Version 1.0
 *
 *  http://labit.pe.kr
 *
 */

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Description : 클래스에 대한 설명을 입력해주세요.<br>
 * Date : 2023-04<br>
 * History :<br>
 * - 작성자 : LABIT, 날짜 : 2023-04, 설명 : 최초작성<br>
 *
 * @author LABIT
 * @version 1.0
 */
public abstract class MappingClassLoader {

    /**
     *
     * @param url
     * @param methodName
     * @return ServiceMap
     */
    public abstract boolean addRequestAttribute(String url, String methodName);

    /**
     *
     * @param request 객체
     * @param response 객체
     * @return RequestDispatcher
     */
    public abstract ModelView goService(HttpServletRequest request, HttpServletResponse response);

    /**
     * 컨트롤러 클래스 안에 있는 메소드를 찾음.
     * @param methodName
     * @return Method 객체
     * 
     */
    public Method getMethod(String methodName){
        Method method = null;
        Class<?> getClass = this.getClass();

        Class<?>[] parameters = new Class[2];
        parameters[0] = HttpServletRequest.class;
        parameters[1] = HttpServletResponse.class;

        try {
            method = getClass.getMethod(methodName, parameters);
            if(method != null){
                System.out.println("[INFO] SUCCESS FIND METHOD forNAME : " + method.getName()  );
            }else{
                System.out.println("[ERROR] FAIL FIND METHOD !!");
            }
        } catch (NoSuchMethodException e) {
            System.out.println("[ERROR] FAIL FIND METHOD CATCH!!");
        }finally {
            return method;
        }
    }

    /**
     * 컨트롤 클래스 내부에 있는 메소드를 호출(사용)함
     * @param method
     * @param request
     * @param response
     * @return
     */

    public ModelView callMethod(Method method, HttpServletRequest request, HttpServletResponse response){
        ModelView modelView = null;
        Object parameters[] = new Object[2];
        parameters[0] = request;
        parameters[1] = response;

        try {
            modelView = (ModelView) method.invoke(this, parameters);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }finally {
            return modelView;
        }
    }


    /*

    default Class<?> loadContextClass(ServletContext servletContext){
        String contextClassName = servletContext.getInitParameter(BASE_CONTEXT_PARAM_NAME);
        if(contextClassName != null){
            return forName(contextClassName, getContextClassLoader() );
        }else{
            return forName(contextClassName, RootContextLoader.class.getClassLoader());
        }
    }


     Class<?> forName(String name, ClassLoader classLoader){
        try {
            return Class.forName(name, false, getContextClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    ClassLoader getContextClassLoader(){
        ClassLoader classLoader = null;
        classLoader = Thread.currentThread().getContextClassLoader();

        if(classLoader == null){
            classLoader = RootContextLoader.class.getClassLoader();
            if(classLoader == null){
                classLoader = ClassLoader.getSystemClassLoader();
            }
        }
        return classLoader;
    }
     */

}
