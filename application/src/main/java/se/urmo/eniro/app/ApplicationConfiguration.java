package se.urmo.eniro.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import se.urmo.eniro.app.client.AsyncRestTemplateFactory;
import se.urmo.eniro.app.client.DevSearchClient;
import se.urmo.eniro.app.client.EniroAsyncSearchClient;
import se.urmo.eniro.app.service.SearchService;
import se.urmo.eniro.app.service.SearchServiceProd;

@Configuration
public class ApplicationConfiguration {
    @Bean
    @Profile("dev")
    SearchService searchServiceDev() {
        return new SearchServiceProd(new DevSearchClient());
    }

    @Bean
    @Profile("!dev")
    SearchService searchServiceProd() {
        return new SearchServiceProd(createSearchClient());
    }

    @Bean
    EniroAsyncSearchClient createSearchClient() {
        return new EniroAsyncSearchClient(AsyncRestTemplateFactory.createDefaultRestTemplate());
    }
}
