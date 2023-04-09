package org.labit.labitframework.web.context;/*
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
import java.lang.reflect.Method;
import java.util.HashMap;
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
public class ServiceContextLoader extends ContextLoader {

    private Map<String, Method> methodMapper = new HashMap<>();
    @Override
    public boolean addRequestAttribute(String url, String methodName) {
        boolean result = false;
        if(!methodMapper.containsKey(url)){
            Method method = getMethod(methodName);
            if(method != null){
                methodMapper.put(url, method);
                result = true;
                System.out.println("[INFO] REQUEST MAPPING URL");
            }else{
                methodMapper.replace(url, method);
                System.out.println("[ERROR] REQUEST MAPPING OVERLAP !!");
            }
        }
        return result;
    }

    /**
     * 호출할 클래스의 메도스 value ( url )을 찾아 invoke 함.
     * @param request 객체
     * @param response 객체
     * @return
     */
    @Override
    public RequestDispatcher goService(HttpServletRequest request, HttpServletResponse response) {
        String contextPath = request.getContextPath();
        String requestURI = request.getRequestURI();

        if(contextPath != null ){
            requestURI = requestURI.replaceFirst(contextPath, "");
        }
        Method method = methodMapper.get(requestURI);

        return callMethod(method, request, response);
    }
}


