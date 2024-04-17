package tn.esprit.spring.spring1.Configuration;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;


import io.swagger.v3.oas.models.OpenAPI;
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
                .description("TP étude de cas")
                .contact(contactAPI());
    }
    public Contact contactAPI() {
        Contact contact = new Contact().name("SpringBoot")
                .email("ahmed.zmerli@esprit.tn");
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
                .pathsToMatch("/Skieur/**")
                .pathsToExclude("**")
                .build();
    }
    @Bean
    public GroupedOpenApi CoursApi() {
        return GroupedOpenApi.builder()
                .group("Only Cours Management API")
                .pathsToMatch("/Cours/**")
                .pathsToExclude("**")
                .build();
    }

    @Bean
    public GroupedOpenApi MoniteurApi() {
        return GroupedOpenApi.builder()
                .group("Only Moniteur Management API")
                .pathsToMatch("/Moniteur/**")
                .pathsToExclude("**")
                .build();
    }

}
