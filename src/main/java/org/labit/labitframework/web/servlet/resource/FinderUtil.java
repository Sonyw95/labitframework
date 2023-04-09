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
public class FinderUtil {

    public static String BASE_PACKAGE_NAME ;

    public static String DEFAULT_BASE_PACKAGE_WHICH = "basePackage";

    public static String BASE_PACKAGE_PATH;

    public static String NAME_DOT = ".";

    public static String BASE_PACKAGE_NAME_REPLACER = "/";

    public static void getNameReplace(){
        BASE_PACKAGE_PATH = BASE_PACKAGE_NAME.replace(NAME_DOT, BASE_PACKAGE_NAME_REPLACER);
    }

}
