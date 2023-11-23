package com.miracle.userservice.config;

import com.fasterxml.classmate.TypeResolver;
import com.miracle.userservice.controller.response.ErrorApiResponse;
import com.miracle.userservice.controller.response.SuccessApiResponse;
import com.miracle.userservice.dto.response.ResumeResponseDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@EnableOpenApi
@Configuration
public class SwaggerConfig {

    private static final String BASE_PACKAGE = "com.miracle.userservice.controller";
    private static final String PATTERN = "/v1/**";
    private static final String TITLE = "Miracle User Service API";
    private static final String DESCRIPTION = "유저와 연관된 요청들을 처리하는 API";
    private static final String VERSION = "1.0";

    @Bean
    public Docket docket(TypeResolver typeResolver) {
        return new Docket(DocumentationType.OAS_30)
                .globalRequestParameters(requestParameterList())
                .additionalModels(
                        typeResolver.resolve(SuccessApiResponse.class),
                        typeResolver.resolve(SuccessApiResponse.class, Boolean.class),
                        typeResolver.resolve(SuccessApiResponse.class, ResumeResponseDto.class),
                        typeResolver.resolve(ErrorApiResponse.class)
                )
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.ant(PATTERN))
                .build();
    }

    private List<RequestParameter> requestParameterList() {
        List<RequestParameter> requestParameterList = new ArrayList<>();
        RequestParameter sessionId = new RequestParameterBuilder()
                .name("sessionId")
                .in(ParameterType.HEADER)
                .required(Boolean.TRUE)
                .build();
        RequestParameter miracle = new RequestParameterBuilder()
                .name("miracle")
                .in(ParameterType.HEADER)
                .required(Boolean.TRUE)
                .build();
        requestParameterList.add(sessionId);
        requestParameterList.add(miracle);

        return requestParameterList;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .version(VERSION)
                .build();
    }
}
