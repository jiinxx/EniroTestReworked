package se.urmo.eniro.app.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.AsyncClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.AsyncRestTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

public class AsyncRestTemplateFactory {
    private static final Duration CONNECT_TIMEOUT = Duration.ofMinutes(2);
    private static final Duration READ_TIMEOUT = Duration.ofMinutes(2);

    public static AsyncRestTemplate createDefaultRestTemplate() {
        AsyncRestTemplate restTemplate = new AsyncRestTemplate();
        restTemplate.setMessageConverters(messageConverters());
        restTemplate.setAsyncRequestFactory(createRequestFactory());
        return restTemplate;
    }

    private static AsyncClientHttpRequestFactory createRequestFactory() {
        final HttpComponentsAsyncClientHttpRequestFactory requestFactory = new HttpComponentsAsyncClientHttpRequestFactory();
        requestFactory.setConnectTimeout((int) CONNECT_TIMEOUT.toMillis());
        requestFactory.setReadTimeout((int) READ_TIMEOUT.toMillis());
        return requestFactory;
    }

    private static List<HttpMessageConverter<?>> messageConverters() {
        final List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new StringHttpMessageConverter());
        messageConverters.add(jsonConverter());
        return messageConverters;
    }

    private static MappingJackson2HttpMessageConverter jsonConverter() {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper());
        return converter;
    }

    private static ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(FAIL_ON_IGNORED_PROPERTIES, false);
        objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }
}
