package com.djh.example;

import com.djh.example.comparison.ComparisonClient;
import com.djh.example.postcode.PostcodeClient;
import com.djh.example.render.TemplateRenderer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

/**
 * @author David Hancock
 */
@Configuration
public class AppConfig {

    @Bean
    public ITemplateResolver stringTemplateResolver() {
        StringTemplateResolver stringTemplateResolver = new StringTemplateResolver();
        stringTemplateResolver.setOrder(Integer.MAX_VALUE);
        return stringTemplateResolver;
    }

    @Bean
    public TemplateRenderer templateRenderer(@Value("${ssr.componentDir}") String componentDir,
                                             ResourceLoader resourceLoader,
                                             ObjectMapper objectMapper) {
        return new TemplateRenderer(componentDir, resourceLoader, objectMapper);
    }

    @Bean
    public ComparisonClient comparisonClient() {
        return new ComparisonClient();
    }

    @Bean
    public PostcodeClient postcodeService() {
        return new PostcodeClient();
    }

}
