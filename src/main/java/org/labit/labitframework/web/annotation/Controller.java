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
 * 어노테이션 생존 유지기간 -> 런타임 까지 즉 JVM이 class 파일을 구성하고 종료하기 전
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Controller {
}
