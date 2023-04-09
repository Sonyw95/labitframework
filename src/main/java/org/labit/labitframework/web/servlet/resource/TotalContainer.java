package org.labit.labitframework.web.servlet.resource;/*
 *  @author LABIT
 *
 *  Copyright LABIT , Software License, Version 1.0
 *
 *  http://labit.pe.kr
 *
 */

/**
 * Description : 클래스에 대한 설명을 입력해주세요.<br>
 * Date : 2023-04<br>
 * History :<br>
 * - 작성자 : LABIT, 날짜 : 2023-04, 설명 : 최초작성<br>
 *
 * @author LABIT
 * @version 1.0
 */
public class TotalContainer {

    private TotalContainer(){};

    public static TotalContainer getInstance(){
        return ClassInInstance.totalContainer;
    }
    private static class ClassInInstance{
        private static final TotalContainer totalContainer = new TotalContainer();
    }

}
