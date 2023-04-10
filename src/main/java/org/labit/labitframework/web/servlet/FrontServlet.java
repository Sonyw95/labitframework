package org.labit.labitframework.web.servlet;/*
 *  @author LABIT
 *
 *  Copyright LABIT , Software License, Version 1.0
 *
 *  http://labit.pe.kr
 *
 */

import org.labit.labitframework.web.context.ApplicationContext;
import org.labit.labitframework.web.servlet.resource.TotalContainer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Description : 클래스에 대한 설명을 입력해주세요.<br>
 * Date : 2023-04-08<br>
 * History :<br>
 * - 작성자 : LABIT, 날짜 : 2023-04-08, 설명 : 최초작성<br>
 *
 * @author LABIT
 * @version 1.0
 */
public class FrontServlet extends FrameWorkServlet   {

    private static List<Class<?>> handler ;

    @Override
    protected void onLoad(ServletContext sc) {
        initHandler(sc);
        initMappingHandler();
    }
    private void doService(HttpServletRequest request, HttpServletResponse response){
        ServiceMappingClassLoader mapHandler = (ServiceMappingClassLoader) getHandler(request);
        if(mapHandler != null){
            System.out.println("[INFO] MapHandler : " + mapHandler);
        }
    }

    @Override
    protected void doAction(HttpServletRequest request, HttpServletResponse response) {
        TotalContainer totalContainer = TotalContainer.getInstance();
        System.out.println(totalContainer.toString());
        if(this.handler == null){
            System.out.println("[INFO] INIT FRONT SERVLET");
            onLoad(request.getServletContext());
        }
        doService(request, response);
    }
    protected static void initHandler(ServletContext servletContext){
        new HandlerFinder(servletContext);
        handler = HandlerFinder.HandlerFinder(servletContext);
    }
    protected static void initMappingHandler(){
        HandlerMapping.initMappingHandle(handler);
    }
    private Object getHandler(HttpServletRequest request){
        Object mapHandler = HandlerMapping.getHandler(request);
        if(mapHandler == null) {
            return  null;
        }
        return mapHandler;
    }


}
