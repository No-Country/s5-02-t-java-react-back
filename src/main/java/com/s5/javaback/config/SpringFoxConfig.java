package com.s5.javaback.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


import java.util.Collections;


@Configuration
public class SpringFoxConfig {

    @Bean
    public Docket swaggerConfiguration(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(apiCustomData());
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

}
