package com.sppo.locationtracker.config;

import com.sppo.locationtracker.model.response.LocationTrackerResponse;
import com.sppo.locationtracker.service.LocationTrackerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Fellipe Toledo
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
