package com.project.controller;

import org.labit.labitframework.web.annotation.Controller;
import org.labit.labitframework.web.annotation.RequestMapping;

/**
 * Description : 클래스에 대한 설명을 입력해주세요.<br>
 * Date : 2023-04-07<br>
 * History :<br>
 * - 작성자 : LABIT, 날짜 : 2023-04-07, 설명 : 최초작성<br>
 *
 * @author LABIT
 * @version 1.0
 */
@Controller
public class MainController  {
    @RequestMapping(value = "/a.do")
    public void test(){
        System.out.println("TEST");
    }

}
