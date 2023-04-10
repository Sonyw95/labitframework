package org.labit.labitframework.web.servlet;/*
 *  @author LABIT
 *
 *  Copyright LABIT , Software License, Version 1.0
 *
 *  http://labit.pe.kr
 *
 */

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
public class ModelView {

    private String VIEW_NAME;
    private Map<String, Object> MODEL = new HashMap<>();

    public ModelView(String view_name){
        this.VIEW_NAME = view_name;
    }
    public String getVIEW_NAME(){
        return VIEW_NAME;
    }
    public void setVIEW_NAME(String view_name){this.VIEW_NAME = view_name;}

    public Map<String, Object> getMODEL(){
        return MODEL;
    }
    public void setMODEL(Map<String, Object> md){this.MODEL = md;}

    public boolean insertModel(String name, Object obj){
        boolean result = false;

        if (name == null || "".equals(name) || name.isEmpty()){
            result = false;
        }else{
            MODEL.put(name, obj);
            result = true;
        }

        return result;
    }

    public Object getModelObject(String name){
        if(name == null || "".equals(name) || name.isEmpty()){
            return null;
        }else{
            Object obj = null;
            if(MODEL.containsKey(name)){
                obj = MODEL.get(name);
            }else{
                return null;
            }
            return obj;
        }
    }

}
