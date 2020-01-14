package spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import spring.api.endpoints.interceptors.AdminInterceptor;
import spring.api.endpoints.interceptors.JwtInterceptor;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    JwtInterceptor getJwtInterceptor() {
        return new JwtInterceptor();
    }

    @Bean
    AdminInterceptor getAdminInterceptor() {
        return new AdminInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getJwtInterceptor())
                .excludePathPatterns("/registrations", "/registrations/*",
                        "/authentications", "/authentications/*");
        registry.addInterceptor(getAdminInterceptor())
                .addPathPatterns("/users/*");
    }
}
