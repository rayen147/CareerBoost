/* package com.example.techcloud.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(infoAPI());
    }
    public Info infoAPI() {
        return new Info().title("SpringDoc")
                .description("TechCloud")
                .contact(contactAPI());
    }
    public Contact contactAPI() {
        Contact contact = new Contact().name("SpringBoot")
                .email("ahmed.zmerli@esprit.tn");
        return contact;
    }
    @Bean
    public GroupedOpenApi EvenementApi() {
        return GroupedOpenApi.builder()
                .group("Only Event Management API")
                .pathsToMatch("/evenements/**")
                .pathsToExclude("**")
                .build();
    }
    @Bean
    public GroupedOpenApi CommentaireApi() {
        return GroupedOpenApi.builder()
                .group("Only Comment Management API")
                .pathsToMatch("/commentaires/**")
                .pathsToExclude("**")
                .build();
    }
}
*/