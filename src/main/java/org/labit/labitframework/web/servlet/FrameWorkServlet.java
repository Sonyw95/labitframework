package org.labit.labitframework.web.servlet;/*
 *  @author LABIT
 *
 *  Copyright LABIT , Software License, Version 1.0
 *
 *  http://labit.pe.kr
 *
 */

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description : 클래스에 대한 설명을 입력해주세요.<br>
 * Date : 2023-04-08<br>
 * History :<br>
 * - 작성자 : LABIT, 날짜 : 2023-04-08, 설명 : 최초작성<br>
 *
 * @author LABIT
 * @version 1.0
 */
public abstract class FrameWorkServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        onLoad(config.getServletContext());
    }

    protected abstract void onLoad(ServletContext sc);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[SERVLET SERVICE START] FrameWorkServlet LINE 29");

        /* Servlet 요청 확인 -> 요청이 HTTP 요청이 아니면 Throw Exception Run */
        if( !(req instanceof  HttpServletRequest && resp instanceof HttpServletResponse) ){
            throw  new ServletException("non request response");
        }


        String method = req.getMethod();
        System.out.println("[SERVLET SERVICE MIDDLE] FrameWorkServlet LINE 38 " + method);
        if(method.equals("GET")){
            long ifModified = getLastModified(req);
            System.out.println("[SERVLET SERVICE MIDDLE] FrameWorkServlet LINE 41 " + ifModified);
            if(ifModified == -1){
                doGet(req, resp);
            }else{
                long ifModifiedSince = req.getDateHeader("If-Modified-Since");
                System.out.println("[SERVLET SERVICE MIDDLE] FrameWorkServlet LINE 46 " + ifModifiedSince);
                if(ifModifiedSince < ifModified) {
                    if(!resp.containsHeader("Last-Modified")){resp.setDateHeader("Last-Modified",ifModifiedSince );}
                    doGet(req,resp);
                }
            }
        }else if(method.equals("POST")){
            doPost(req,resp);
        }else{
            resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, "METHOD_NOT_IMPLEMENTED");
        }

    }
    protected abstract void doAction(HttpServletRequest request, HttpServletResponse response);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcessRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcessRequest(req,resp);
    }

    protected final void doProcessRequest(HttpServletRequest request, HttpServletResponse response){
        doAction(request, response);
    }
}
