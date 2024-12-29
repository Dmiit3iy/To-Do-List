package org.dmit3ii.todolist.client;


import org.dmit3ii.todolist.model.Holiday;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiClient {
    @Value("${url.api.path}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    @Cacheable(value = "holidaysCache", key = "#year"+"-"+"#countryCode")
    public List<Holiday> getAllHolidays(int year, String countryCode) {
        ResponseEntity<List<Holiday>> response =restTemplate.exchange(apiUrl + "/{year}/{countryCode}", HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {}, year, countryCode);
        if (response == null || response.getBody() == null) {
            throw new IllegalStateException("Ошибка получения списка праздников с внешнего API");
        }
        return response.getBody();
    }
}
