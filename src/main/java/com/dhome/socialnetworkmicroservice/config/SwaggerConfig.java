package com.dhome.socialnetworkmicroservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static final String POSTS = "Posts";
    public static final String COMMENTS = "Comments";
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .tags(new Tag(POSTS,"Muestra las funcionalidades para la publicación"),
                        new Tag(COMMENTS,"Muestra las funcionalidades para el comentario"))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Social Network Microservice API",
                "Microservicio de Social Network",
                "1.0",
                "",
                new Contact("Dr3amTeam", "https://github.com/Dr3amTeam/", "admin@dr3amteam.com"),
                "LICENSE",
                "https://www.mit.edu/~amini/LICENSE.md",
                Collections.emptyList()
        );
    }
}