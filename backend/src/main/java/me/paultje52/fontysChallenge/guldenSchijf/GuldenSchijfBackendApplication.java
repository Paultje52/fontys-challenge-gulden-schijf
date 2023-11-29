package me.paultje52.fontysChallenge.guldenSchijf;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@RestController
@PropertySource("classpath:application.properties")
public class GuldenSchijfBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuldenSchijfBackendApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .exposedHeaders("*")
                        .allowedOrigins("${cors.origin}")
                        .allowCredentials(true);
            }
        };
    }

    @GetMapping("")
    public String getIndex() {
        JSONObject data = new JSONObject();
        data.put("message", "Hello there!");
        return data.toString();
    }

}
