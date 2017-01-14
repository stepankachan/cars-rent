package com.courses.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @author Stepan.Kachan
 */
@EnableWebMvc
@Configuration
@ComponentScan({"com.courses.*" })
@Import(value = { ApplicationConfig.class})
public class LoginApplicationConfig extends WebMvcConfigurerAdapter {
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ResourceHandlerRegistration resourceRegistration = registry
                .addResourceHandler("/resources/**");
        resourceRegistration.addResourceLocations("/WEB-INF/**");
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/**");
        registry.addResourceHandler("/fonts/roboto/**").addResourceLocations("/WEB-INF/fonts/roboto/**");
        registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/**");
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/**");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}