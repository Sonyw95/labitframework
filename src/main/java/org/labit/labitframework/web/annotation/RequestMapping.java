package org.labit.labitframework.web.annotation;/*
 *  @author LABIT
 *
 *  Copyright LABIT , Software License, Version 1.0
 *
 *  http://labit.pe.kr
 *
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Controller 와 동일.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface RequestMapping {
    public String value() default "";
    public enum RequestMethod {GET, POST};
    public RequestMethod method() default RequestMethod.GET;
}
