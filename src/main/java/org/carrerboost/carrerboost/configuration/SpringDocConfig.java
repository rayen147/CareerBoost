package org.carrerboost.carrerboost.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(infoAPI());
    }
    public Info infoAPI() {
        return new Info().title("SpringDoc-Demo")
                .description("TP étude de cas SKI")
                .contact(contactAPI());
    }
    public Contact contactAPI() {
        Contact contact = new Contact().name("Equipe ASI II")
                .email("chahnez.sardouk@esprit.tn")
                .url("https://www.linkedin.com/in/**********/");
        return contact;
    }


    @Bean
    public GroupedOpenApi PisteApi() {
        return GroupedOpenApi.builder()
                .group("Only Piste Management API")
                .pathsToMatch("/piste/**")
                .pathsToExclude("**")
                .build();
    }

    @Bean
    public GroupedOpenApi SkieurApi() {
        return GroupedOpenApi.builder()
                .group("Only Skieur Management API")
                .pathsToMatch("/skieur/**")
                .pathsToExclude("**")
                .build();
    }
    @Bean
    public GroupedOpenApi MoniteurAPI (){
        return  GroupedOpenApi.builder()
                .group("only moniteur management API")
                .pathsToMatch("/moniteur/**")
                .pathsToExclude("**")
                .build() ;
    }

    @Bean
    public GroupedOpenApi CoursAPI (){
        return  GroupedOpenApi.builder()
                .group ("only cours management API")
                .pathsToMatch("/cours/**")
                .pathsToExclude("**")
                .build() ;

    }


    @Bean
    public GroupedOpenApi InscriptionAPI (){
        return  GroupedOpenApi.builder()
                .group ("only inscription management API")
                .pathsToMatch("/inscription/**")
                .pathsToExclude("**")
                .build() ;

    }

}
