package com.s5.javaback.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;


import java.util.Collections;
import java.util.List;


@Configuration
@EnableWebMvc
public class SpringFoxConfig {

    @Bean
    public Docket swaggerConfiguration(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.any())
                .build().apiInfo(apiCustomData())
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(List.of(apiKey()));




    }
    private ApiInfo apiCustomData(){
        return new ApiInfo(
                "Casa de eventos API Application",
                "Casa de Eventos Documentacion",
                "1.0",
                "No-Country",
                new Contact("No-Country", "https://www.nocountry.tech/","Sin Email"),
                "License No-Country",
                "https://www.nocountry.tech/",
                Collections.emptyList()
        );
    }

    private SecurityContext securityContext(){
        return SecurityContext.builder()
                .securityReferences(defaultAuth()).build();
    }
    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global",
                "accessEverything");
        AuthorizationScope[] authorizationScopes
                = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("Bearer", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer", "Authorization", "header");
    }

}
