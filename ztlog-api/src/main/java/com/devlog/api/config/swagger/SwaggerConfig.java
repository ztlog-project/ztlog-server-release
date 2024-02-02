package com.devlog.api.config.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(swaggerInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.devlog.api"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(true)
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(securityScheme()));
    }

    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{new AuthorizationScope("all","all scope")};
    }

    private SecurityScheme securityScheme() {
        GrantType grant = new ResourceOwnerPasswordCredentialsGrant("/front/oauth/token");
        return new OAuthBuilder().name("OAuth2")
                .grantTypes(Arrays.asList(grant))
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(new SecurityReference("OAuth2", scopes())))
                .forPaths(PathSelectors.any())
                .build();
    }

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder().title("HPoint Front API Document")
                .description("ZTLog Front API Document")
                .license("zoetrope56")
                .licenseUrl("https://ztlog.io//")
                .version("1")
                .build();
    }
}