package com.zongzewu.bettercallwu.config;

import com.zongzewu.bettercallwu.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * set mapper of static resources
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
       log.info("Start static resource mapping ");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/font/**").addResourceLocations("classpath:/front/");
    }

    /**
     * Extending the MVC framework's message converter
     * @param converters
     */
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("extends msg converter");
        //Create a message converter object
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //set object converter, use Jackson to convert java obj to json.
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //Append the above message converter to the converter container collection of the MVC framework
        converters.add(0,messageConverter);
    }

}
