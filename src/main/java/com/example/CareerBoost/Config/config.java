package com.example.CareerBoost.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
<<<<<<< HEAD
//import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.GroupedOpenApi;
=======
import org.springdoc.core.models.GroupedOpenApi;
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("com.example.CareerBoost")
@PropertySource("classpath:/application.properties")
public class config implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pdfs/**")
<<<<<<< HEAD
                .addResourceLocations("C:/Users/kbaie/IdeaProjects/CareerBoost-master - Copie/src/main/resources/pdfs/");
    }
  /* @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/pdfs/**")
               .addResourceLocations("classpath:/pdfs/");
   }*/

=======
                .addResourceLocations("C:/Users/kbaie/IdeaProjects/CareerBoost/src/main/resources/pdfs/");
    }
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
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
    public GroupedOpenApi CertificatApi() {
        return GroupedOpenApi.builder()
                .group("Only Certificat Management API")
                .pathsToMatch("/certificat/**")
                .pathsToExclude("**")
                .build();}
    @Bean
    public GroupedOpenApi ModuleFormationApi() {
        return GroupedOpenApi.builder()
                .group("Only ModuleFormation Management API")
                .pathsToMatch("/moduleFormation/**")
                .pathsToExclude("**")
                .build();}
    @Bean
    public GroupedOpenApi FormationApi() {
        return GroupedOpenApi.builder()
                .group("Only Formation Management API")
                .pathsToMatch("/formation/**")
                .pathsToExclude("**")
                .build();}
<<<<<<< HEAD

=======
>>>>>>> 9f19f141b8cba6f5c5dbb32e8f434427b77d0e15
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
