package com.miracle.userservice.swagger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Swagger 명세에 사용되는 어노테이션. Swagger Docket 중 "/v1/user/{userId}" 경로를 포함하지 않는 모든 요청은 이 어노테이션을 명시해야한다.
 * 이 어노테이션으로 Swagger는 해당 API가 'default-path' 그룹에 속하는 docket인지 판단할 수 있다.
 *
 * <p> 핸들러에 직접 명시해도 되고, Swagger 명세 어노테이션에 달아주는 것도 가능하다.
 * 해당 컨트롤러 내 API가 모두 포함된다면 클래스 레벨에 달아줄 수도 있다.
 * 클래스 레벨보다 메서드 레벨이 우선순위가 더 높다.
 *
 * @author chocola
 * @see UserPathDocket
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultPathDocket {
}
