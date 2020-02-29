package io.digitalstate.camunda.mainms;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private MultiTaskIdHandlerInterceptor multiTaskIdHandlerInterceptor;

    public InterceptorConfig(MultiTaskIdHandlerInterceptor multiTaskIdHandlerInterceptor) {
        this.multiTaskIdHandlerInterceptor = multiTaskIdHandlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("Cats");
        registry.addInterceptor(multiTaskIdHandlerInterceptor).addPathPatterns("/**");
    }
}
