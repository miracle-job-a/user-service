package com.miracle.userservice.config;

import com.fasterxml.classmate.TypeResolver;
import com.miracle.userservice.controller.response.ErrorApiResponse;
import com.miracle.userservice.controller.response.SuccessApiResponse;
import com.miracle.userservice.swagger.DefaultPathDocket;
import com.miracle.userservice.swagger.UserPathDocket;
import com.miracle.userservice.util.Const;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@EnableOpenApi
@Configuration
public class SwaggerConfig {

    private static final String TITLE = "Miracle User Service API";
    private static final String DESCRIPTION = "유저와 연관된 요청들을 처리하는 API";
    private static final String VERSION = "1.0";
    private static final String BASE_PACKAGE = "com.miracle.userservice.controller";

    @Bean
    public Docket defaultPathdocket(TypeResolver typeResolver) {
        return baseDocket(typeResolver, "default-path")
                .select()
                .apis(withAnnotation(DefaultPathDocket.class))
                .build();
    }

    @Bean
    public Docket userPathDocket(TypeResolver typeResolver) {
        return baseDocket(typeResolver, "user-path")
                .globalRequestParameters(userPathRequestParameterList())
                .select()
                .apis(withAnnotation(UserPathDocket.class))
                .build();
    }

    private Docket baseDocket(TypeResolver typeResolver, String groupName) {
        return new Docket(DocumentationType.OAS_30)
                .globalRequestParameters(globalRequestParameterList())
                .additionalModels(
                        typeResolver.resolve(SuccessApiResponse.class),
                        typeResolver.resolve(ErrorApiResponse.class)
                )
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .version(VERSION)
                .build();
    }

    private List<RequestParameter> globalRequestParameterList() {
        List<RequestParameter> requestParameterList = new ArrayList<>();
        RequestParameter sessionId = new RequestParameterBuilder()
                .name(Const.RequestHeader.SESSION_ID)
                .in(ParameterType.HEADER)
                .required(Boolean.TRUE)
                .build();
        RequestParameter miracle = new RequestParameterBuilder()
                .name(Const.RequestHeader.MIRACLE)
                .in(ParameterType.HEADER)
                .required(Boolean.TRUE)
                .build();

        requestParameterList.add(sessionId);
        requestParameterList.add(miracle);
        return requestParameterList;
    }

    private List<RequestParameter> userPathRequestParameterList() {
        List<RequestParameter> requestParameterList = new ArrayList<>();
        RequestParameter userId = new RequestParameterBuilder()
                .name(Const.RequestHeader.USER_ID)
                .in(ParameterType.HEADER)
                .required(Boolean.TRUE)
                .build();

        requestParameterList.add(userId);
        return requestParameterList;
    }

    private Predicate<RequestHandler> withAnnotation(Class<? extends Annotation> annotation) {
        Class<? extends Annotation> other = annotation.equals(DefaultPathDocket.class) ? UserPathDocket.class : DefaultPathDocket.class;
        return RequestHandlerSelectors.withMethodAnnotation(annotation)
                .or(
                        RequestHandlerSelectors.withMethodAnnotation(other).negate()
                                .and(RequestHandlerSelectors.withClassAnnotation(annotation))
                );
    }
}
