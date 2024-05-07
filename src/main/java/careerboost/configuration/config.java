package careerboost.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
//import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("careerboost")
@PropertySource("classpath:/application.properties")
public class config implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(infoAPI());
    }
    public Info infoAPI() {
        return new Info().title("SpringDoc-Demo")
                .description("Careerboost")
                .contact(contactAPI());
    }
    public Contact contactAPI() {
        Contact contact = new Contact().name("Equipe ASI II")
                .email("chahnez.sardouk@esprit.tn")
                .url("https://www.linkedin.com/in/**********/");
        return contact;
    }
    @Bean
    public GroupedOpenApi CommentaireApi() {
        return GroupedOpenApi.builder()
                .group("Only Commentaire  Management API")
                .pathsToMatch("/commentaire/**")
                .pathsToExclude("**")
                .build();}
        @Bean
        public GroupedOpenApi ReactApi() {
            return GroupedOpenApi.builder()
                    .group("Only React Management API")
                    .pathsToMatch("/react/**")
                    .pathsToExclude("**")
                    .build();}
            @Bean
            public GroupedOpenApi PostApi() {
                return GroupedOpenApi.builder()
                        .group("Only Post Management API")
                        .pathsToMatch("/post/**")
                        .pathsToExclude("**")
                        .build();}
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

}
